package org.itstep.myClassWork;

import org.itstep.entities.academy.Group;
import org.itstep.entities.academy.Student;

public class August18Academy implements Runnable
{
    @Override
    public void run() {
        System.out.println(" Academy work ");

        createEntities();
    }


    private void createEntities() {
        Group g = new Group("pv121");

        Student julia = new Student("Julia", g);
        Student marina = new Student("Marina", g);

        g.getStudents().add(julia);
        g.getStudents().add(marina);

        System.out.println(g);


    }

}
