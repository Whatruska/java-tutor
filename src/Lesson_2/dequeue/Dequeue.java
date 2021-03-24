package Lesson_2.dequeue;

import Lesson_2.list.ListItem;
import Lesson_2.tailedList.TailedList;

public class Dequeue<T> {
    private TailedList<T> tailedList = new TailedList<>();

    public Dequeue() {}

    public Dequeue(T value) {
        tailedList.append(value);
    }

    public T popFirst () {
        T value = tailedList.getHead().getValue();
        tailedList.delete(-1);
        return value;
    }

    public void pushFirst(T value) {
        ListItem<T> head = tailedList.getHead();
        ListItem<T> newItem = new ListItem<>(value);
        newItem.setNext(head);
        tailedList.getList().setHead(newItem);
    }

    public T popLast () {
        T value = tailedList.getTail().getValue();
        tailedList.delete(tailedList.size() - 1);
        return value;
    }

    public void pushLast(T value) {
        tailedList.append(value);
    }

    public T get(int index) {
        return tailedList.getValue(index);
    }

    public void delete(int index) {
        tailedList.delete(index);
    }

    @Override
    public String toString() {
        String str = "";
        ListItem<T> item = tailedList.getHead();
        while (item != null) {
            str += item.getValue() + " ";
            item = item.getNext();
        }
        return str;
    }
}
