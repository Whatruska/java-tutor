package Lesson_11;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

//        Random random = new Random();
//        int n = random.nextInt(20) + 1;
//
//        for (int i = 0; i < n; i++) {
//            int from = 0;
//            int to = random.nextInt(4);
//            while (from == to) to = random.nextInt(4);
//            int amount = random.nextInt(20000);
//            Transactions transaction = new Transactions(from, to, amount);
//            transaction.start();
//        }
        NumberPrinter printer = new NumberPrinter();
        NumberPrinter printer2 = new NumberPrinter();
        printer.start();
        printer2.start();
    }
}
