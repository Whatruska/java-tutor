package list;

public class ListItem<T> {
    private T value;
    private ListItem<T> next;

    public ListItem(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ListItem<T> getNext() {
        return next;
    }

    public void setNext(ListItem<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "list.ListItem{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}
