package Lesson_9.bot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    //Основная команда запуска метода
    String name();

    //Описание метода
    String description() default "";

    //Альтернативные команды запуска
    String[] alias() default {};
}
