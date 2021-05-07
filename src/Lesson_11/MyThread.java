package Lesson_11;

import java.util.Random;

public class MyThread extends Thread {
    @Override
    public void run() {
        Random random = new Random();
        int time = random.nextInt(5000);
        try {
            Thread.sleep(time);
            System.out.println(this.getName() + " completed in " + time + " ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
