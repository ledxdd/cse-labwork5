package cse_labwork5.src.client.network;

import cse_labwork5.src.common.Request;
import cse_labwork5.src.common.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

public class TCPClient {
    private final String host;
    private final int PORT;
    private static final int RECONNECTION_TIMEOUT = 2000;
    private static final int MAX_ATTEMPTS = 300;

    public TCPClient(String host, int port) {
        this.host = host;
        this.PORT = port;
    }

    public Response sendAndRecieve(Request request) {
        int attempts = 0;
        while (attempts < MAX_ATTEMPTS) {
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(host, PORT), 5000);
                socket.setSoTimeout(5000);

                byte[] data;
                try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                     ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                    oos.writeObject(request);
                    oos.flush();
                    data = baos.toByteArray();
                }

                OutputStream os = socket.getOutputStream();

                ByteBuffer lengthBuffer = ByteBuffer.allocate(4).putInt(data.length);
                os.write(lengthBuffer.array());

                os.write(data);
                os.flush();

                try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                    return (Response) in.readObject();
                }

            } catch (Exception e) {
                attempts++;
                System.err.println("Попытка #" + attempts + " не удалась: " + e.getMessage());
                if (attempts >= MAX_ATTEMPTS) break;
                try {
                    Thread.sleep(RECONNECTION_TIMEOUT);
                } catch (InterruptedException ignored) {}
            }
        }
        return new Response("Сервер недоступен после " + MAX_ATTEMPTS + " попыток.");
    }
}
