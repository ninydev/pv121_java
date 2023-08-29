package org.itstep;

import org.itstep.myClassWork.august26.August26Threads;
import org.itstep.myClassWork.august29.August29Files;

public class Main {



    public static void main(String[] args) {

        August29Files work = new August29Files();
        work.run();

        Thread t = Thread.currentThread();
        System.out.println("\n ----- \nFinish Application in thread: " + t.getName());
    }
}