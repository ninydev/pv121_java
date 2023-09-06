package org.itstep.myClassWork.september06;

import org.itstep.myClassWork.september06.apps.CRM;
import org.itstep.myClassWork.september06.servers.serverCRM;

public class mainCRM {
    public static void main(String[] args){
        CRM work = new CRM();
        serverCRM server = new serverCRM(work);
        Thread t = new Thread(server);
        t.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        work.run();
    }
}
