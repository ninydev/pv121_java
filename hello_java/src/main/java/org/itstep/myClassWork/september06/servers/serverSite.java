package org.itstep.myClassWork.september06.servers;

import org.itstep.myClassWork.september06.apps.CRM;
import org.itstep.myClassWork.september06.apps.Site;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class serverSite implements Runnable
{
    // Ссылка на саму ситему управления
    private Site site;
    public serverSite(Site site){
        this.site = site;
    }

    @Override
    public void run() {
        System.out.println(" Start wait messages from CRM ");
        try {
            ServerSocket serverSocket = new ServerSocket(33124);
            while (true) {
                Socket crmSocket = null;
                while (crmSocket == null) {
                    crmSocket = serverSocket.accept();
                    System.out.println("Connect From: " + crmSocket.getLocalAddress());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
