package Lesson_11;

public class Loader {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new MyThread();
        thread1.setName("1");
        thread1.start();
        thread1.join();

        Thread thread2 = new MyThread();
        thread2.setName("2");
        thread2.start();
        thread2.join();

        Thread thread3 = new MyThread();
        thread3.setName("3");
        thread3.start();
        thread3.join();

        System.out.println("Main");
    }
}
