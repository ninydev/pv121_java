package org.itstep.myClassWork.september02;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

public class SimpleClient {

    public static void main(String[] args) {

        try {
            // Открыть пассивынй сокет
            Socket connect = new Socket("localhost", 33123);

            // Получить поток для передачи данных
            OutputStream out =  connect.getOutputStream();

            // Потсроить текущую дату
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date d = new Date();

            // Передать текущую дату
            out.write(df.format(d).getBytes());

            connect.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
