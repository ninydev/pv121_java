package org.itstep.myClassWork.august18;

import org.itstep.entities.academy.Group;
import org.itstep.entities.academy.Student;
import org.itstep.exceptions.validations.NameLengthValidationMax;
import org.itstep.exceptions.validations.NameLengthValidationMin;

public class August18Academy implements Runnable
{
    @Override
    public void run() {
        System.out.println(" Academy work ");

        createEntities();
        try {
            testGroup();
        } catch (Exception e) { // Это плохой опдход - поскольку исключительная ситуация не будет обработана так, как вы ожидаете
            System.out.println(e.getMessage());
        }
    }

    Group g = new Group("pv121");

    private void testGroup() throws NameLengthValidationMin, NameLengthValidationMax {
        System.out.println("\n\n В этой точке уже нет пемеменных julia и marina");
        System.out.println(g);

        g.getStudents().get(0).setName("Пытаюсь дать новое имя");

    }

    private void createEntities() {

        try {
            Student julia = new Student("J", g);
            Student marina = new Student("Marina", g);

            g.getStudents().add(julia.clone());
            g.getStudents().add(marina);

            System.out.println("Вывод изначальной группы");
            System.out.println(g);

            julia.setName("Юлия");
            marina.setName("Марина");

            System.out.println("\n\n Я изменил студентов из главной функции");
            System.out.println(g);

            g.getStudents().get(0).setName("Юлия Владимировна");
            g.getStudents().get(1).setName("Юлия Вадимовна");

            System.out.println("\n\n Я изменил студентов в группе");
            System.out.println(g);
        } catch (NameLengthValidationMin exMin) {
            System.out.println("Min Внутри моего процесса произошла исключительная ситуация: " + exMin.getMessage());
        } catch (NameLengthValidationMax exMax) {
            System.out.println("Max Внутри моего процесса произошла исключительная ситуация: " + exMax.getMessage());
        } catch (Exception ex) {
            System.out.println("Для самых ленивых - просто любое исключение обрабатываю тут: " + ex.getMessage());
        }

    }

}
