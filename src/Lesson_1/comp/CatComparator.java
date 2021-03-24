package Lesson_1.comp;

import java.util.Comparator;

public class CatComparator implements Comparator<Cat> {
    @Override
    public int compare(Cat o1, Cat o2) {
        if (o1.getWeight() > o2.getWeight()) {
            return 1;
        } else if (o1.getWeight() == o2.getWeight()) {
            return 0;
        } else {
            return -1;
        }
    }
}
