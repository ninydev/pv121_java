package org.itstep.myClassWork.september06.apps;

import org.itstep.myClassWork.september06.models.User;

import java.util.ArrayList;
import java.util.Scanner;

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

        } while (userChoice != 0);
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

        return  userChoice;
    }
}
