package cse_labwork5.src.server.network;

import cse_labwork5.src.common.Request;
import cse_labwork5.src.common.Response;
import cse_labwork5.src.server.commands.CommandExecutor;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class TCPServer {
    private final int PORT;
    private final CommandExecutor commandExecutor;
    private Selector selector;

    public TCPServer(int port, CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
        this.PORT = port;
    }

    public void start() {
        try {
            selector = Selector.open();
            ServerSocketChannel serverChannel = ServerSocketChannel.open();

            serverChannel.configureBlocking(false);
            serverChannel.bind(new InetSocketAddress(PORT));

            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("Сервер запущен на порту: " + PORT);

            while (true) {
                if (selector.select() == 0) {
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
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("Клиент подключен: " + clientChannel.getRemoteAddress());
    }

    private void readRequest(SelectionKey key) {
        try (SocketChannel clientChannel = (SocketChannel) key.channel()) {
            try {
                ByteBuffer sizeBuffer = ByteBuffer.allocate(4);
                int read = clientChannel.read(sizeBuffer);
                if (read < 4) return;

                sizeBuffer.flip();
                int objectSize = sizeBuffer.getInt();

                ByteBuffer objectBuffer = ByteBuffer.allocate(objectSize);
                int totalRead = 0;
                while (totalRead < objectSize) {
                    int currentRead = clientChannel.read(objectBuffer);
                    if (currentRead == -1) break;
                    totalRead += currentRead;
                }

                objectBuffer.flip();
                byte[] data = new byte[objectBuffer.remaining()];
                objectBuffer.get(data);

                try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
                    Request request = (Request) ois.readObject();
                    Response response = commandExecutor.handleRequest(request);
                    sendResponse(clientChannel, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException ignored) {
        } finally {
            key.cancel();
        }
    }

    private void sendResponse(SocketChannel clientChannel, Response response) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(response);
        oos.flush();

        ByteBuffer responseBuffer = ByteBuffer.wrap(baos.toByteArray());

        while (responseBuffer.hasRemaining()) {
            clientChannel.write(responseBuffer);
        }
    }
}
