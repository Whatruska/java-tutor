package offtop;
public class WrongQueue {

    public static void main(String[] args) {
        new WrongQueue().go();
    }

    private void go() {
        MyQ myQ = new MyQ();

        new Producer(myQ);
        new Consumer(myQ);
    }

    class Producer implements Runnable {

        public MyQ queue;

        public Producer(MyQ queue) {
            this.queue = queue;
            new Thread(this, "Producer").start();
        }

        @Override
        public void run() {
            int i = 0;

            while (true) {
                if (queue.shouldSend) {
                    queue.put(i++);
                }
            }
        }
    }

    class Consumer implements Runnable {

        public MyQ queue;

        public Consumer(MyQ queue) {
            this.queue = queue;
            new Thread(this, "Consumer").start();
        }

        @Override
        public void run() {
            while (true) {
                if (!queue.shouldSend) {
                    queue.get();
                }
            }
        }
    }

    class MyQ {
        volatile int n;
        volatile boolean shouldSend = true;

         synchronized void put(int n) {
            this.n = n;
            System.out.println("Send: " + n);
            shouldSend = false;
        }

         synchronized int get() {
            System.out.println("Get: " + n);
            shouldSend = true;
            return n;
        }
    }
}
