package org.itstep.myClassWork.september08;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.itstep.myClassWork.september06.models.User;

import java.util.UUID;

public class HomeWork08 {

    private static final String queueName = "app.events";

    public static void main(String[] args){
        MyRabbitMQ rabbitMQ_site_createUser = new MyRabbitMQ("site.user.register");
        MyRabbitMQ rabbitMQ_site_updateUser = new MyRabbitMQ("site.user.update");
        MyRabbitMQ rabbitMQ_crm_createCustomer = new MyRabbitMQ("crm.user.create");
        MyRabbitMQ rabbitMQ_crm_updateCustomer = new MyRabbitMQ("crm.customer.update");

        DeliverCallback dc = (consumerTag, delivery) -> {
            Object data = DTOObject.toObject(delivery.getBody());
            System.out.println(data);
        };

        rabbitMQ_site_createUser.useConsume( dc);
        rabbitMQ_site_updateUser.useConsume( dc);
        rabbitMQ_crm_createCustomer.useConsume( dc);
        rabbitMQ_crm_updateCustomer.useConsume( dc);


        new Thread(rabbitMQ_site_createUser).start();
        new Thread(rabbitMQ_site_updateUser).start();
        new Thread(rabbitMQ_crm_createCustomer).start();
        new Thread(rabbitMQ_crm_updateCustomer).start();

    }



}
