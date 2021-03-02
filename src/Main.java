import list.CustomList;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(4);

        CustomList<Double> list = new CustomList<>(4.0);
        list.add(5.0);
        list.add(6.0);
        list.insert(1, 15.0);
        System.out.println(list);
    }
}
