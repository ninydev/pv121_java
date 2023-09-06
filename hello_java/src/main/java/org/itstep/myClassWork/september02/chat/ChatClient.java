package org.itstep.myClassWork.september02.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ChatClient {

    private static ObjectOutputStream outputStream;
    private static ObjectInputStream inputStream;

    public static void main(String[] args) {

        try {
            // Открыть пассивынй сокет
            Socket socket = new Socket("localhost", 33123);

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            ChatMessage msgHello = new ChatMessage("Hello World");
            outputStream.writeObject(msgHello);
            outputStream.flush();

            ChatClientSocket c = new ChatClientSocket(socket);
            Thread t = new Thread(c);
            t.start();

            Scanner scanner = new Scanner(System.in);

            while (true) {

                System.out.print("Введите сообщение: ");
                String inputString = scanner.nextLine();

                ChatMessage msg = new ChatMessage(inputString);
                outputStream.writeObject(msg);
                outputStream.flush();

                if (inputString.equals("exit")) {
                    break;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
