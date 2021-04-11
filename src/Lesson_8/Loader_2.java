package Lesson_8;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Loader_2 {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<Dolphin> dolphinClass = Dolphin.class;

        System.out.println("----- METHODS -----");
        Method[] dolphinMethods = dolphinClass.getMethods();
        for (Method m: dolphinMethods) {
            System.out.println(m);
        }

        System.out.println("----- CLASSES -----");
        Class superclass = dolphinClass.getSuperclass();
        do {
            System.out.println(superclass);
            superclass = superclass.getSuperclass();
        } while (superclass != null);

        System.out.println("----- CLASSES BY getClasses -----");
        Class[] interfaces = dolphinClass.getClasses();
        for (Class i: interfaces) {
            System.out.println(i);
        }

//        Class<Animal> animalClass = Animal.class;
//        Animal animal = animalClass.asSubclass(Animal.class).newInstance();
//        System.out.println(animal);

        Class<Cat> catClass = Cat.class;
        Cat cat = catClass.getConstructor(String.class).newInstance("Кот_2");
        Method method = catClass.getDeclaredMethod("move");
        method.setAccessible(false);
        method.invoke(cat);
        AnimalManager.makeCatMove(cat);
    }
}
