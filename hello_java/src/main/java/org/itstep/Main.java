package org.itstep;

import org.itstep.myClassWork.august19.August19Streams;
import org.itstep.myClassWork.august19.posts.August19Posts;
import org.itstep.myClassWork.august25.August25Threads;

public class Main {



    public static void main(String[] args) {

        August25Threads work = new August25Threads();
        work.run();

        Thread t = Thread.currentThread();
        System.out.println("\n ----- \nFinish Application in thread: " + t.getName());
    }
}