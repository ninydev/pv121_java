package org.itstep;

import org.itstep.myClassWork.august26.August26Threads;

public class Main {



    public static void main(String[] args) {

        August26Threads work = new August26Threads();
        work.run();

        Thread t = Thread.currentThread();
        System.out.println("\n ----- \nFinish Application in thread: " + t.getName());
    }
}