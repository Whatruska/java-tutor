package Lesson_9.RandomGenerator;

import java.lang.reflect.Field;
import java.util.Random;

public class RandomNumberGenerator {
    public static void process (Object o) {
        Class clas = o.getClass();
        Field[] fields = clas.getDeclaredFields();
        for (Field field: fields) {
            RandomValue annotation = field.getAnnotation(RandomValue.class);
            boolean hasOurAnnotation = annotation != null;
            if (hasOurAnnotation) {
                field.setAccessible(true);
                Class fieldType = field.getType();
                double min = annotation.min();
                double max = annotation.max();
                Random random = new Random();
                Object newValue = null;
                if (fieldType == int.class || fieldType == Integer.class) {
                    //генерим int
                    newValue = (int) (min + random.nextInt((int)(max - min)));
                } else if (fieldType == double.class || fieldType == float.class || fieldType == Float.class || fieldType == Double.class) {
                    //генерим вещ число
                    newValue = min + (random.nextFloat() * (max - min));
                    //1 min + (max - min) => max
                    //nextFloat [0.0; 1.0]
                    // 0 -> min
                    // 1 -> max
                }
                try {
                    field.set(o, newValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                field.setAccessible(false);
            }
        }
    }
}
