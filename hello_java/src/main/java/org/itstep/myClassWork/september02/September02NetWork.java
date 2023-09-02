package org.itstep.myClassWork.september02;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class September02NetWork implements Runnable
{
    @Override
    public void run() {
        getByName();
    }

    private void getByName() {
        try {
            InetAddress address = InetAddress.getByName("storage_minio");
            System.out.println(address.getHostName());
            System.out.println(address.getAddress());
            System.out.println(address.getCanonicalHostName());
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
            // throw new RuntimeException(e);
        }

    }
}
