package org.itstep.myClassWork.september08;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.io.Serializable;

public class MyRabbitMQ implements Runnable
{
    private static final String queueName = "app.events";
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;

    public MyRabbitMQ(){
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("user");
        factory.setPassword("password");

        try {
            connection = factory.newConnection();
            channel = connection.createChannel();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(); // Выйти из ПО
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    DeliverCallback deliverCallback;

    public void useConsume(DeliverCallback cb) {
        deliverCallback = cb;
    }

    public void publish (String o) {
        try {
            channel.basicPublish("", queueName, null, o.getBytes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(); // Выйти из ПО
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
                Thread.sleep(100);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(); // Выйти из ПО
        }
    }
}
