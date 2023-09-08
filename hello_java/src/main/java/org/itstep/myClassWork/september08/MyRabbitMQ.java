package org.itstep.myClassWork.september08;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;

public class MyRabbitMQ implements Runnable
{
    private String queueName = "app.events";
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;

    public MyRabbitMQ() {
        this("app.events");
    }

    public MyRabbitMQ(String queueName)
    {
        this.queueName = queueName;

        factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("user");
        factory.setPassword("password");

        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(queueName, false, false, false, null);
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

    public void publish (Object o) {
        try {
            channel.basicPublish("", queueName, null,
                    DTOObject.toBytes(o));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(); // Выйти из ПО
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                if(deliverCallback != null) {
                    channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
                    });
                }
                Thread.sleep(100);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(); // Выйти из ПО
        }
    }
}
