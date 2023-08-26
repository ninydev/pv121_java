package org.itstep.myClassWork.august26;

import java.util.Random;
import java.util.concurrent.*;

public class August26Threads implements Runnable {
    @Override
    public void run() {
        System.out.println(" Threads work");
        calcPi();
        // anonim();
    }

    private void calcPiSemaphore(){
        Semaphore s = new Semaphore(1);
        CalculatePiElementSemaphore el1 = new CalculatePiElementSemaphore(0, 100, s);
        CalculatePiElementSemaphore el2 = new CalculatePiElementSemaphore(101, 200, s);

        Thread t1 = new Thread(el1);
        t1.start();
        Thread t2 = new Thread(el2);
        t2.start();

        try {
            // join позволяет главному потоку main дождаться завершения потоков
            t1.join();
            t2.join();
            // Мы просто ждем - поток будет заблокирован
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    private void calcPi() {
        CalculatePiElement el1 = new CalculatePiElement(0, 100);
        CalculatePiElement el2 = new CalculatePiElement(101, 200);

        ExecutorService executor = Executors.newFixedThreadPool(1); // Создаем пул из двух потоков

        Future<?> future1 = executor.submit(el1);
        Future<?> future2 = executor.submit(el2);

        executor.shutdown();

        // Проверяем, завершились ли обе задачи
        do {
//            if((new Random()).nextInt(100)> 95) {
//                future1.cancel(true);
//            }
//            // System.out.print(".");
//            if (future1.isDone()) {
//                System.out.println(" 1 isDone. Status: " + future1.state());
//            }
//            if (future2.isDone()) {
//                System.out.println(" 2 isDone. Status: " + future2.state());
//            }
            // Таким образом я не блокирую поток main - и могу управлять всеми порожденными потоками
            // прерывать их - ставить на паузу и тд и тп
            System.out.print(".");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        } while (!future1.isDone() || !future2.isDone());

        // Дожидаемся завершения всех потоков в пуле - это аналог join но сразу для всех в пуле потоков
//        try {
//            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        /*

        Thread t1 = new Thread(el1);
        t1.start();
        Thread t2 = new Thread(el2);
        t2.start();

        try {
            // join позволяет главному потоку main дождаться завершения потоков
            t1.join();
            t2.join();
            // Мы просто ждем - поток будет заблокирован
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

         */


    }

    private void startAsCallable() {

        // Создадим реализацию интерфейса, которая будет возвращать значение из потока
        Callable<String> c = new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(" Can't pause this thread: " + e.getMessage() );
                }
                Thread t = Thread.currentThread();
                return "Hello Callable: " + t;
            }
        };

        // Но тогда такой тип запуска не применим для такой операции
//        Thread t = new Thread(String.valueOf(c));
//        t.start();

        // Создаем ExecutorService с одним потоком
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Запускаем Callable и получаем Future объект
        Future<String> future = executor.submit(c);

        try {

            int counter = 0;

            // Таким образом я жду - пока из дочернего потока не прийдет сигнал - что вычисления закончены
            while (!future.isDone()) {
                System.out.print(".");
                if(counter++ > 5) {
                    future.cancel(true);
                }
                Thread.sleep(100);
            }

            // Ждем, пока Callable завершится и получаем результат
            String result = future.get();

            // Выводим результат
            Thread t = Thread.currentThread();
            System.out.println("\n\n" + t + " Result: " + result);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Завершаем ExecutorService
        executor.shutdown();



    }

    private void startAsRunnable(){
        //Thread t = // Если нам необходимо помнить или управлять потоком - то я могу сохранить ссылку на него  в переменной

        ( // Если мне нужно только 1 раз обратиться к какому то методу - я оберну созданный класс скобками
                new Thread// Создаем экземпляр класса поток
                        (
                        // В качестве аргумента мы передаем анонимный класс, который реализует интерфейс Runnable
                            new Runnable() {
                                // Тут создается анонимный класс, который реализует интерфейс Runnable
                                // Но это не мешает нам описать в нем и другие поля и методы
                                private final String someVar = "Var";
                                private void runMe() {
                                    System.out.println("Hello Runnable: " + this.someVar);

                                }
                                @Override
                                public void run() {
                                    this.runMe();
                                }
                            }
                        )
                // Вот тут экземпляр класса уже создан
        ) // и в этой точке - я буду обращаться уже к вновь созданому объекту, не создавая лишнюю переменную
                .start();

        Runnable r = () -> {
            System.out.println("Hello Runnable from -> ");
        };
        System.out.print("31 : ");
        r.run();
        System.out.println("Class of Runnable r: " + r.getClass());

        Thread t = new Thread(r);
        t.start();
    }

    private  void anonim() {
        Object o = new Object(){
            private final String var = "Some Var";
            @Override
            public String toString() {
                return this.var;
            }

            // При таком объявлении я не могу обратиться к этому методу извне экземпляра
            public void doMe(){}
        };

        // o.doMe(); // Поскольку класс анонимный мы не знаем - есть ли такой метод

        System.out.println(o);
    }
}
