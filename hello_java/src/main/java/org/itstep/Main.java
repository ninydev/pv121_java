package org.itstep;


import org.itstep.myClassWork.september01.September01Mongo;

public class Main {



    public static void main(String[] args) {

        September01Mongo work = new September01Mongo();
        work.run();

        Thread t = Thread.currentThread();
        System.out.println("\n ----- \nFinish Application in thread: " + t.getName());
    }
}