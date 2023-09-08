package org.itstep.myClassWork.september08;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

public class SimpleConsumer {

    private static final String queueName = "app.events";
    public static void main(String[] args){
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

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String data = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(data);
            };

            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
