package Lesson_8;

import java.lang.reflect.*;

public class Loader {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<Cat> catClass = Cat.class;
        Constructor<Cat> publicConstructor = catClass.getConstructor(String.class);
        Cat cat = publicConstructor.newInstance("Васька");

        Field nameField = catClass.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(cat, "вАс хАкНуЛи");
        nameField.setAccessible(false);

        Method meowMethod = catClass.getDeclaredMethod("meow", String.class, int.class);
        meowMethod.setAccessible(true);
        String log = (String) meowMethod.invoke(cat, "афыаф", 2);
        System.out.println(log);
        meowMethod.setAccessible(false);

        System.out.println(Modifier.isAbstract(Animal.class.getModifiers()));

        System.out.println(cat);

        Constructor<Cat> privateConstructor = catClass.getDeclaredConstructor();
        privateConstructor.setAccessible(true);
        cat = publicConstructor.newInstance("Васька");
        privateConstructor.setAccessible(false);

        System.out.println(cat);
    }
}
