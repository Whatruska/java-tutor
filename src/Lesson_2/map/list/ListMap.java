package Lesson_2.map.list;

import Lesson_2.list.CustomList;
import Lesson_2.list.ListItem;

public class ListMap<K, V> {
    private CustomList<ListMapItem<K, V>> map = new CustomList<>();

    public ListMap() {
    }

    public ListMap(K key, V value) {
        map.add(new ListMapItem<>(key, value));
    }

    public boolean contains(K key) {
        ListItem<ListMapItem<K, V>> item = map.getHead();
        while (item != null) {
            if (item.getValue().getKey().equals(key)) {
                return true;
            }
            item = item.getNext();
        }
        return false;
    }

    private ListItem<ListMapItem<K, V>> getItem(K key) {
        ListItem<ListMapItem<K, V>> item = map.getHead();
        while (item != null) {
            if (item.getValue().getKey().equals(key)) {
                return item;
            }
            item = item.getNext();
        }
        return null;
    }

    public V get(K key) {
        ListItem<ListMapItem<K, V>> item = getItem(key);
        if (item == null) {
            return null;
        } else {
            return item.getValue().getValue();
        }
    }

    public void put(K key, V value) {
        if (contains(key)) {
            ListItem<ListMapItem<K, V>> item = getItem(key);
            item.getValue().setValue(value);
        } else {
            map.add(new ListMapItem<>(key, value));
        }
    }

    public void delete(K key) {
        if (contains(key)) {
            int index = -1;
            ListItem<ListMapItem<K, V>> item = map.getHead();
            while (item != null) {
                if (item.getValue().getKey().equals(key)) {
                    map.delete(index);
                    break;
                }
                item = item.getNext();
                index++;
            }
        }
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
