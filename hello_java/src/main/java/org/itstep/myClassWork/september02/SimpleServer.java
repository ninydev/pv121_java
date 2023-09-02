package org.itstep.myClassWork.september02;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

    public static void main(String[] args) {

        try {
            // Начинаю слушать порт 33123
            ServerSocket serverSocket = new ServerSocket(33123);

            while (true) {
                Socket clientSocket = null;
                while (clientSocket == null) {
                    System.out.println(" Start wait client");
                    clientSocket = serverSocket.accept();
                    System.out.println("Connect From: " + clientSocket.getLocalAddress());

                    InputStream in = clientSocket.getInputStream();

                    byte[] buffer = new byte[32];
                    buffer = in.readAllBytes();

                    System.out.println("readAllBytes: " + buffer);


                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
