package org.itstep.myClassWork.august25;

public class August25Threads implements Runnable
{
    @Override
    public void run() {
        System.out.println(" Threads work");
        firstInit();
        // mySelf();
    }

    public void firstInit(){
        // Я создаю єкземпляр класса и готовлю его к запуску
        // все данные я передал - все готово к запуску
        MyFirstThread t = new MyFirstThread("HelloThread");

        // Я просто запускаю метод в этом же потоке
        // То есть - мой код не будет запущен отдельно от главного потока
        // t.run();

        // Что бы запустить подготовленный мной поток
        // мне нужно обратиться к другому методу
        t.start();


    }

    public void mySelf () {
        Thread t = Thread.currentThread(); // получаем главный поток
        System.out.println(t); // main

        System.out.println("Thread name is: " + t.getName());
        t.setName("Main");
        System.out.println("Thread name is: " + t.getName());
    }



}
