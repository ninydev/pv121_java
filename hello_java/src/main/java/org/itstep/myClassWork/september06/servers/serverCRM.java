package org.itstep.myClassWork.september06.servers;

import org.itstep.myClassWork.september06.apps.CRM;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class serverCRM implements Runnable
{
    // Ссылка на саму ситему управления
    private CRM crm;
    public serverCRM(CRM crm){
        this.crm = crm;
    }

    @Override
    public void run() {
        System.out.println(" Start wait messages from Site ");
        try {
            ServerSocket serverSocket = new ServerSocket(33123);
            while (true) {
                Socket siteSocket = null;
                while (siteSocket == null) {
                    siteSocket = serverSocket.accept();
                    System.out.println("Connect From: " + siteSocket.getLocalAddress());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
