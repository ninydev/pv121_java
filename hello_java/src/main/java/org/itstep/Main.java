package org.itstep;

import org.itstep.myClassWork.august26.August26Threads;
import org.itstep.myClassWork.august29.August29Files;
import org.itstep.myClassWork.august30.August30MySql;

public class Main {



    public static void main(String[] args) {

        August30MySql work = new August30MySql();
        work.run();

        Thread t = Thread.currentThread();
        System.out.println("\n ----- \nFinish Application in thread: " + t.getName());
    }
}