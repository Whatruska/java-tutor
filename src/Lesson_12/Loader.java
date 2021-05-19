package Lesson_12;

import java.util.Random;

public class Loader {
    public static void main(String[] args) {
        OperationService operationService = new OperationService();
        Random random = new Random();
        int consumerNum = random.nextInt(10) + 1;
        for (int i = 0; i < consumerNum; i++) {
            Consumer consumer = new Consumer(operationService, consumerNum);
            consumer.start();
        }
    }
}
