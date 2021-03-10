package queue;

import list.ListItem;
import tailedList.TailedList;

public class Queue<T> {
    private TailedList<T> tailedList = new TailedList<>();

    public Queue() {}

    public Queue(T value) {
        tailedList.append(value);
    }

    //Посмотреть
    public T peekFirst () {
        return tailedList.getHead().getValue();
    }

    //Удалить
    public T popFirst () {
        T value = tailedList.getHead().getValue();
        tailedList.delete(-1);
        return value;
    }

    public T peekLast () {
        return tailedList.getTail().getValue();
    }

    public void add(T value) {
        tailedList.append(value);
    }

    public T get (int index) {
        return tailedList.getValue(index);
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
