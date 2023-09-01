package org.itstep;

import org.itstep.myClassWork.august26.August26Threads;
import org.itstep.myClassWork.august29.August29Files;
import org.itstep.myClassWork.august30.August30MySql;
import org.itstep.myClassWork.september01.September01Redis;

public class Main {



    public static void main(String[] args) {

        September01Redis work = new September01Redis();
        work.run();

        Thread t = Thread.currentThread();
        System.out.println("\n ----- \nFinish Application in thread: " + t.getName());
    }
}