package org.itstep.myClassWork.september01;

import redis.clients.jedis.Jedis;

import java.io.Console;

public class September01Redis implements Runnable
{
    @Override
    public void run() {
        connectToRedis();
    }

    private void connectToRedis() {
        // Соединяюсь с сервером
        Jedis redis = new Jedis("localhost", 6379);



        // Кладу в сервер информацию
        redis.set("SomeKey", "SomeValue");
        // Актуальной данная информация будет в течении 60 секунд
        redis.expire("SomeKey", 60);

        String val = redis.get("SomeKey");
        System.out.println(" SomeVal = " + val);


    }
}
