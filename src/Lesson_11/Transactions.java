package Lesson_11;

import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.Random;

public class Transactions extends Thread {
    private static int[] balancePull = {0, 10000, -15000, 5000, 3000};
    private int from;
    private int to;
    private int amount;

    public Transactions(int from, int to, int balance) {
        this.from = from;
        this.to = to;
        this.amount = balance;
    }

    public void perform () throws InterruptedException {
        Thread.sleep(1000);
        Random random = new Random();
        int time = random.nextInt(500);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        balancePull[from] -= amount;
        balancePull[to] += amount;
        System.out.println(from + " -> " + to + " : " + amount);
        System.out.println(Arrays.toString(balancePull));
    }

    @SneakyThrows
    @Override
    public void run() {
        perform();
    }
}
