import list.CustomList;
import map.list.ListMap;
import set.ListSet;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ListMap<String, Integer> scores = new ListMap<>();

        scores.put("Петя", 20);
        scores.put("Аня", 10);
        scores.put("Петя", 15);
        scores.delete("Аня");

        System.out.println(scores);

        ListSet<Integer> set = new ListSet<>(5);
        set.put(3);
        set.put(4);
        set.delete(5);
        System.out.println(set);
    }
}
