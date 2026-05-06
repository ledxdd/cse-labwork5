package client.network;

import common.Request;
import common.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

public class TCPClient {
    private final String host; // localhost
    private final int PORT; // 12345
    private static final int RECONNECTION_TIMEOUT = 2000; //20мс

    public TCPClient(String host, int port) {
        this.host = host;
        this.PORT = port;
    }

    public Response sendAndRecieve(Request request) {
        int attempts = 0;
        while (attempts < 10) {
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(host, PORT), 5000);
                socket.setSoTimeout(5000);

                byte[] data;
                try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream(); ObjectOutputStream objectStream = new ObjectOutputStream(byteArray)) {
                    objectStream.writeObject(request);
                    objectStream.flush();
                    data = byteArray.toByteArray();
                }

                OutputStream os = socket.getOutputStream();

                ByteBuffer lengthBuffer = ByteBuffer.allocate(4).putInt(data.length);
                os.write(lengthBuffer.array());

                os.write(data);
                os.flush();

                InputStream is = socket.getInputStream();
                byte[] lengthBytes = is.readNBytes(4);

                if (lengthBytes.length < 4) {
                    throw new IOException("Ответ сервера обрезан.");
                }

                int responseLen = ByteBuffer.wrap(lengthBytes).getInt();
                byte[] responseData = is.readNBytes(responseLen);

                if (responseData.length < responseLen) {
                    throw new IOException("Ответ сервера получен не полностью.");
                }

                try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(responseData))) {
                    return (Response) in.readObject();
                }

            } catch (Exception e) {
                attempts++;
                System.err.println("Попытка #" + attempts + " не удалась: " + e.getMessage());

                try {
                    Thread.sleep(RECONNECTION_TIMEOUT);
                } catch (InterruptedException ignored) {}
            }
        }

        return new Response("pupuupu");
    }
}
