package org.itstep.myClassWork.september06;

import org.itstep.myClassWork.september06.apps.Site;
import org.itstep.myClassWork.september06.servers.serverCRM;
import org.itstep.myClassWork.september06.servers.serverSite;

public class mainSite {
    public static void main(String[] args) {
        Site site = new Site();
        serverSite server = new serverSite(site);
        Thread t = new Thread(server);
        t.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        site.run();
    }
}
