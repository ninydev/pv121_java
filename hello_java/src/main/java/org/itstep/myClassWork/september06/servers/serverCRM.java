package org.itstep.myClassWork.september06.servers;

import org.itstep.myClassWork.september06.apps.CRM;
import org.itstep.myClassWork.september06.models.Customer;
import org.itstep.myClassWork.september06.models.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

                    ObjectInputStream inputStream = new ObjectInputStream(siteSocket.getInputStream());
                    try {
                        Request request = (Request) inputStream.readObject();
                        System.out.println(request);

                        switch (request.getCommand()) {
                            case userRegister:
                                Customer newCustomer = crm.createCustomerFromUser((User) request.getBody());
                                ObjectOutputStream outputStream = new ObjectOutputStream(siteSocket.getOutputStream());
                                outputStream.writeObject(newCustomer);
                                siteSocket.close();

                                break;
                        }



                    } catch (Exception e) {

                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
