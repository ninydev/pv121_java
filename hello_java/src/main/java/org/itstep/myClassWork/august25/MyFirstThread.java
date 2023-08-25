package org.itstep.myClassWork.august25;

public class MyFirstThread extends Thread
{
    /**
     * Данные, необходимые для работы потока
     * описываются в классе, который будет управлять потоком
     */
    private final String name;

    /**
     * В конструкторе описываются те данные, без которых существование
     * потока не имеет смысла.
     * Так же - тут описывается иннициализация значений переменных по умолчанию
     * @param name
     */
    public MyFirstThread(String name) {
        this.name = name;
        // Данный код будет работать в родительском потоке
        // в моем случае - main
        // Таким образом я сменю имя главного потока
        // Thread.currentThread().setName(name);
    }

    /**
     * Первая функция, которая является точкой входа в поток
     * Именно с нее начинается сам процесс работы потока
     */
    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() + " start work");
        System.out.println(Thread.currentThread());

        // В этой же точке - я уже живу в своем потоке - отдельно от главного
        Thread.currentThread().setName(this.name);

        // Любые операции с потоками в Java оборачиваются в try|catch
        // таким образом - мы обязаны обработать исключения внутри потока
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(" Can't pause this thread: " + e.getMessage() );
        }

        System.out.println("Thread " + Thread.currentThread().getName() + " stop work");

    }
}
