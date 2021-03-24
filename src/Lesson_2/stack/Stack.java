package Lesson_2.stack;

import Lesson_2.list.CustomList;
import Lesson_2.list.ListItem;

public class Stack<T> {
    private CustomList<T> stack = new CustomList<>();

    public Stack() {
    }

    public Stack(T value) {
        stack.add(value);
    }

    public void add(T value) {
        stack.add(value);
    }

    public T remove() {
        ListItem<T> lastItem = stack.get(stack.size() - 1);
        stack.delete(stack.size() - 1);
        return lastItem.getValue();
    }
}
