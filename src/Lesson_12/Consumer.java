package Lesson_12;

import java.util.Random;

public class Consumer extends Thread {
    private OperationService operationService;
    private Random random = new Random();

    public Consumer (OperationService operationService, int consumerCounter) {
        this.operationService = operationService;
        this.setName("Consumer - " + consumerCounter);
    }

    @Override
    public void run() {
        while (true) {
            int value = random.nextInt(10) + 1;
            operationService.performLongAngExpensiveOperation(value);
        }
    }
}
