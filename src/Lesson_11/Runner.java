package Lesson_11;

import java.util.Random;
import java.util.stream.Stream;

public class Runner extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            Random random = new Random();
            int time = random.nextInt(5000);
            try {
                Thread.sleep(time);
                System.out.println("Runner completed point " + i + " in " + time + " ms");
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
