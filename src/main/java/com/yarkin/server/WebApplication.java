package com.yarkin.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WebApplication {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);

        while(true) {
            System.out.println("Started, waiting for connection");

            Socket socket = serverSocket.accept();

            System.out.println("Connected with " + socket.getInetAddress());

            try(OutputStream outputStream = socket.getOutputStream();
                InputStream inputStream = socket.getInputStream()) {
                byte[] data = new byte[32 * 1024];
                int readBytes = inputStream.read(data);
                String request = new String(data, 0, readBytes);
                System.out.println("CLIENT >>> " + request);

                outputStream.write(("echo " + request).getBytes());
                outputStream.flush();
            }

        }
    }
}
