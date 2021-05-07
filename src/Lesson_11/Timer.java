package Lesson_11;

public class Timer {
    private static final int MAX_TIME = 5000;

    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();
        runner.start();
        Thread.sleep(MAX_TIME);
        if (runner.isAlive()) {
            runner.interrupt();
            System.out.println("Runner failed");
        } else {
            System.out.println("Success");
        }
    }
}
