package org.itstep.myClassWork.september02.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatServerSocket implements Runnable
{

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    /**
     * При создании соедиеннеия на стороне сокета
     * я сформирую поток обработки для каждого клиента
     *
     * @param socket - сокет клиента
     */
    public ChatServerSocket(Socket socket) {
        this.socket = socket;
        try {
            // Получу потоки
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        System.out.println("Connect From: " + socket.getLocalAddress() + "\t Thread: " + Thread.currentThread().getName());

        try {
            while (true) {
                try {
                    String msg = (String) inputStream.readObject();
                    System.out.println(Thread.currentThread().getName() + ": " + msg);
                    sendMessage("U write: " + msg);
                    // Если пришло сообщение на выход - выхожу из цикла
                    if (msg.equals("exit")) {
                        break;
                    }
                } catch (Exception e) {
                    Thread.sleep(1000);
                    System.out.println("in ChatServerSocket.run: " +  e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Отправить сообщение
     * @param msg
     */
    public void sendMessage (String msg) {
        try {
            outputStream.writeObject(msg);
            outputStream.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
