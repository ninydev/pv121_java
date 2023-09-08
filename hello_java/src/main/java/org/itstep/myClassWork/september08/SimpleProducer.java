package org.itstep.myClassWork.september08;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.itstep.myClassWork.september06.models.User;

import java.util.UUID;

public class SimpleProducer {

    private static final String queueName = "app.events";

    public static void main(String[] args){
        MyRabbitMQ rabbitMQ = new MyRabbitMQ();
//        for (int i = 0; i < 3; i++) {
//            rabbitMQ.publish("Hello Object: " + i);
//            try {
//                Thread.sleep(100);
//            } catch (Exception e) {}
//        }

        User u = new User();
        u.setName("Vasya");
        u.setUser_id(UUID.randomUUID());

        rabbitMQ.publish(u);


        rabbitMQ.disconnect();
    }




    public static void mainInline(String[] args){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("user");
        factory.setPassword("password");
        try (
                Connection connection = factory.newConnection();
                Channel channel = connection.createChannel();)
        {
            System.out.println("Connect to RabbitMQ");
            channel.queueDeclare(queueName, false, false, false, null);

            // До этой позиции процесс соединеня, открытия канала и подключения
            // к очереди - одинаковый

            // Создадим публикацию о неком событии
            String data = "Hello World";
            channel.basicPublish("", queueName, null, data.getBytes());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
