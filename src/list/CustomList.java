package list;

public class CustomList<T> {
    private ListItem<T> head;
    private int size = 0;

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
        ListItem<T> elem = get(size - 1);
        elem.setNext(
                new ListItem<>(value)
        );
        size++;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String str = "{";
        ListItem<T> elem = head;
        for (int i = 0; i < size; i++) {
            str += elem.getValue() + " ; ";
            elem = elem.getNext();
        }
        str += "}";
        return str;
    }
}
