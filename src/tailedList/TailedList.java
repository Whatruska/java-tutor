package tailedList;

import list.CustomList;
import list.ListItem;

public class TailedList<T> {
    private CustomList<T> list = new CustomList<>();
    private ListItem<T> tail;

    public TailedList() {}

    public TailedList(T value) {
        list.add(value);
        tail = list.getHead();
    }

    public void append (T value) {
        list.add(value);
        recountTail();
    }

    public void insert(T value, int index) {
        list.insert(index, value);
        recountTail();
    }

    public void delete(int index) {
        list.delete(index);
        recountTail();
    }

    public T getValue(int index) {
        return list.get(index).getValue();
    }

    public int size () {
        return list.size();
    }

    public ListItem<T> getTail() {
        return tail;
    }

    public ListItem<T> getHead() {
        return list.getHead();
    }

    public void recountTail () {
        tail = list.get(list.size() - 1);
    }

    public CustomList<T> getList() {
        return list;
    }
}
