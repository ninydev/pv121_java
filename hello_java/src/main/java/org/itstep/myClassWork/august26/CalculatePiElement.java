package org.itstep.myClassWork.august26;

import java.util.Random;

/**
 * В задачу этого класса входит посчитать часть ряда, начиная с элемента from до элемента to
 */
public class CalculatePiElement implements Runnable
{
    static Random rnd = new Random(); // для случайных чисел

    private final int from;
    private final int to;

    public double result = 0;

    public double getResult() {
        return result;
    }

    public CalculatePiElement(int from, int to){
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        System.out.println("Start Pi \t" + this.hashCode() + " from: " + from + " to " + to);
        try {
            for (int i = 0; i < rnd.nextInt(5); i++) {
                Thread.sleep(rnd.nextInt(300) + 300);
            }
        } catch (InterruptedException e) {
            System.out.println(" Can't pause this thread: " + e.getMessage() );
        }
        System.out.println("Finish Pi \t" + this.hashCode() + " from: " + from + " to " + to);
    }
}
