package set;

import map.list.ListMap;

public class ListSet<K> {
    private ListMap<K, Object> set = new ListMap<>();

    public ListSet() {}

    public ListSet(K key) {
        set.put(key, new Object());
    }

    public boolean contains (K key) {
        return set.contains(key);
    }

    public void put (K key) {
        set.put(key, new Object());
    }

    public void delete (K key) { set.delete(key); }

    @Override
    public String toString() {
        return set.toString();
    }
}
