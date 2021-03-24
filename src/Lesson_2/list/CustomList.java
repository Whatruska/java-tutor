package Lesson_2.list;

public class CustomList<T> {
    private ListItem<T> head;
    private int size = 0;

    public CustomList() {}

    public CustomList(T firstValue) {
        head = new ListItem<T>(firstValue);
        size = 1;
    }

    public T getValue(int index) {
        return get(index).getValue();
    }

    public ListItem<T> get(int index) {
        ListItem<T> elem = head;
        for (int i = 0; i < index; i++) {
            elem = elem.getNext();
        }
        return elem;
    }

    public void insert(int index, T value) {
        ListItem<T> prevElem = get(index);
        ListItem<T> newElem = new ListItem<>(value);
        newElem.setNext(prevElem.getNext());
        prevElem.setNext(newElem);
        size++;
    }

    public void add(T value) {
        if (size == 0) {
            head = new ListItem<>(value);
        } else  {
            ListItem<T> elem = get(size - 1);
            elem.setNext(
                    new ListItem<>(value)
            );
        }
        size++;
    }

    public void delete(int index){
        if (index <= -1){
            deleteHead();
        } else if (index < size() - 1){
            ListItem<T> element = get(index);
            ListItem<T> del = element.getNext();
            ListItem<T> next = del.getNext();

            del.setNext(null);
            element.setNext(next);
        } else {
            get(index - 1).setNext(null);
        }
        size--;
    }

    public void deleteHead(){
        ListItem<T> nextHead  = head.getNext();
        head.setNext(null);
        this.head = nextHead;
    }

    public int size() {
        return size;
    }

    public void recountSize() {
        int currSize = 0;
        ListItem<T> item = head;
        while (item != null) {
            item = item.getNext();
            currSize++;
        }
        size = currSize;
    }

    public ListItem<T> getHead() {
        return head;
    }

    public void setHead(ListItem<T> head) {
        this.head = head;
        recountSize();
    }

    @Override
    public String toString() {
        String str = "";
        ListItem<T> elem = head;
        for (int i = 0; i < size; i++) {
            str += elem.getValue() + "\n";
            elem = elem.getNext();
        }
        return str;
    }
}
