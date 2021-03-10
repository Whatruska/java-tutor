import dequeue.Dequeue;
import list.CustomList;
import map.list.ListMap;
import queue.Queue;
import set.ListSet;
import stack.Stack;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        Queue<Integer> queue = new Queue<>(3);
//
//        queue.add(5);
//        queue.add(6);
//
//        System.out.println(queue.peekFirst());
//        System.out.println(queue.popFirst());
//        System.out.println(queue);
//        queue.add(7);
//        System.out.println(queue);
//        System.out.println(queue.peekLast());
//        System.out.println(queue.get(1));
        Dequeue<Integer> dequeue = new Dequeue<Integer>(3);
        dequeue.pushLast(4);
        dequeue.pushFirst(2);
        System.out.println(dequeue);

        dequeue.popFirst();
        System.out.println(dequeue);

        dequeue.popLast();
        System.out.println(dequeue);
    }
}
