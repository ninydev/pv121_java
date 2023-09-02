package org.itstep;


import org.itstep.myClassWork.september01.September01Mongo;
import org.itstep.myClassWork.september02.September02NetWork;

public class Main {



    public static void main(String[] args) {

        September02NetWork work = new September02NetWork();
        work.run();

        Thread t = Thread.currentThread();
        System.out.println("\n ----- \nFinish Application in thread: " + t.getName());
    }
}