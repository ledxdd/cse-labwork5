package server.network;

import common.Request;
import common.Response;
import server.commands.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class TCPServer {
    private static final Logger logger = LoggerFactory.getLogger(TCPServer.class);
    private final int PORT;
    private final RequestHandler requestHandler;
    private Selector selector;

    public TCPServer(int port, RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
        this.PORT = port;
    }

    public void start() {
        try {
            selector = Selector.open();
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            serverChannel.configureBlocking(false);
            serverChannel.bind(new InetSocketAddress(PORT));

            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            logger.info("Сервер запущен на порту {}", PORT);

            while (true) {
                handleServerConsole(consoleReader);

                if (selector.select(200) == 0) {
                    continue;
                }

                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();

                    if (!key.isValid()) {
                        continue;
                    }

                    if (key.isAcceptable()) {
                        acceptConnection(key);
                    } else if (key.isReadable()) {
                        readRequest(key);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void acceptConnection(SelectionKey key) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = ssc.accept();

        if (clientChannel == null) {
            return;
        }

        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ, new ClientContext());
        logger.info("Получено новое подключение: {}", clientChannel.getRemoteAddress());
    }

    private void readRequest(SelectionKey key) {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        ClientContext context = (ClientContext) key.attachment();

        try {
            if (context.payloadBuffer == null) {
                int read = clientChannel.read(context.sizeBuffer);
                if (read == -1) {
                    closeClient(key, clientChannel);
                    return;
                }

                if (context.sizeBuffer.hasRemaining()) {
                    return;
                }

                context.sizeBuffer.flip();
                int objectSize = context.sizeBuffer.getInt();
                context.payloadBuffer = ByteBuffer.allocate(objectSize);
            }

            int readPayload = clientChannel.read(context.payloadBuffer);
            if (readPayload == -1) {
                closeClient(key, clientChannel);
                return;
            }
            if (context.payloadBuffer.hasRemaining()) {
                return;
            }

            context.payloadBuffer.flip();
            byte[] data = new byte[context.payloadBuffer.remaining()];
            context.payloadBuffer.get(data);

            try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
                Request request = (Request) ois.readObject();
                logger.info("Получен запрос '{}' от клиента {}", request.getCommandName(), clientChannel.getRemoteAddress());
                Response response = requestHandler.handleRequest(request);
                sendResponse(clientChannel, response);
            } finally {
                closeClient(key, clientChannel);
            }

        } catch (Exception e) {
            logger.error("Ошибка при чтении/обработке запроса", e);
            closeClient(key, clientChannel);
        }
    }

    private void sendResponse(SocketChannel clientChannel, Response response) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(response);
        oos.flush();

        byte[] payload = baos.toByteArray();
        ByteBuffer responseBuffer = ByteBuffer.allocate(4 + payload.length);
        responseBuffer.putInt(payload.length);
        responseBuffer.put(payload);
        responseBuffer.flip();

        while (responseBuffer.hasRemaining()) {
            clientChannel.write(responseBuffer);
        }

        logger.info("Ответ отправлен клиенту {}", clientChannel.getRemoteAddress());
    }

    private void closeClient(SelectionKey key, SocketChannel clientChannel) {
        try {
            key.cancel();
            clientChannel.close();
        } catch (IOException ignored) {
            logger.warn("Не удалось закрыть клиентское соединение корректно");
        }
    }

    private void handleServerConsole(BufferedReader consoleReader) throws IOException {
        if (!consoleReader.ready()) {
            return;
        }

        String command = consoleReader.readLine();

        if (command == null) {
            return;
        }

        if ("save".equalsIgnoreCase(command.trim())) {
            Response response = requestHandler.handleRequest(new Request("__server_save__", null));
            logger.info("Выполнена серверная команда save: {}", response.getMessage());
        }
    }

    private static class ClientContext {
        private final ByteBuffer sizeBuffer = ByteBuffer.allocate(4);
        private ByteBuffer payloadBuffer;
    }
}
