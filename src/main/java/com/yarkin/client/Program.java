package com.yarkin.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Program {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 3000);

        try(InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream()) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            String message = reader.readLine();

            outputStream.write(message.getBytes());
            outputStream.flush();

            byte[] data = new byte[32 * 1024];
            int readBytes = inputStream.read(data);
            String response = new String(data, 0, readBytes);
            System.out.println("SERVER >>> " + response);
        }
    }
}
