package org.itstep.myClassWork.september09.apps;

import com.rabbitmq.client.DeliverCallback;
import org.itstep.myClassWork.september09.models.Customer;
import org.itstep.myClassWork.september09.models.User;
import org.itstep.myClassWork.september09.services.MyRabbitMQ;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class CRM {

    private ArrayList<Customer> customers = new ArrayList<>();


    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public CRM(){
        // Я мониторю события, связанные с созданием нового пользователя на сайте
        // Это Consumer
        rabbitMQSiteUserRegister = new MyRabbitMQ("site.user.register");
        rabbitMQSiteUserRegister.useConsume(this.listenerUserRegister);
        new Thread(rabbitMQSiteUserRegister).start();

        // Я сообщаю сайту, что пользователь обновился
        // Это Producer
        rabbitMQCRMCustomerUpdate = new MyRabbitMQ("crm.customer.update");
    }

    private MyRabbitMQ rabbitMQSiteUserRegister;
    private MyRabbitMQ rabbitMQCRMCustomerUpdate;

    DeliverCallback listenerUserRegister = (consumerTag, delivery) -> {
        // Таким образом я получаю тут пользователя
        User u = User.fromBytes(delivery.getBody());
        Customer c = Customer.fromUser(u);

        customers.add(c);
        rabbitMQCRMCustomerUpdate.publish(c);
    };



    public void run() {
        int userChoice;
        do {
            userChoice = menu();

            switch (userChoice){
                case 1: commandAddCustomer();
                    break;
                case 9: commandShowAll();
                    break;
            }

        } while (userChoice != 0);

        rabbitMQCRMCustomerUpdate.disconnect();
        rabbitMQSiteUserRegister.disconnect();
    }


    private void commandAddCustomer(){
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        Customer newCustomer = new Customer();
        newCustomer.setName(name);
        newCustomer.setCustomer_id(UUID.randomUUID());
        newCustomer.setUser_id(null);

        customers.add(newCustomer);
    }

    private void commandShowAll() {
        System.out.println("\n+------------------------------+\n");
        for (Customer c: customers) {
            System.out.println(c);
        }
        System.out.println("\n+------------------------------+\n");
    }


    Scanner scanner = new Scanner(System.in);

    public int menu() {
        System.out.println("Выберете операцию");
        System.out.println("1 Создать пользователя (Customers)");
        System.out.println("9 Показать всех");
        System.out.println("0 Выход");

        int userChoice = -1;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.print("Введите цифру: ");
            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                isValidInput = true;
            } else {
                System.out.println("Вы ввели неверное значение. Пожалуйста, введите целое число.");
                scanner.nextLine(); // Очищаем буфер ввода
            }
        }
        scanner.nextLine();
        return  userChoice;

    }


}
