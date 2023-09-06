package org.itstep.myClassWork.september06.apps;

import org.itstep.myClassWork.september06.models.User;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Site {

    private ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    public Site(){}

    public void run() {
        int userChoice;
        do {
            userChoice = menu();
            switch (userChoice){
                case 1: commandUserRegister();
                    break;
                case 9: commandShowAll();
                    break;
            }

        } while (userChoice != 0);
    }

    private void commandUserRegister(){
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        User newUser = new User();
        newUser.setName(name);
        newUser.setUser_id(UUID.randomUUID());
        newUser.setCustomer_id(null);

        users.add(newUser);
    }

    private void commandShowAll() {
        System.out.println("\n+------------------------------+\n");
        for (User u: users) {
            System.out.println(u);
        }
        System.out.println("\n+------------------------------+\n");
    }




    Scanner scanner = new Scanner(System.in);
    public int menu() {
        System.out.println("Выберете операцию");
        System.out.println("1 Создать пользователя (User)");
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