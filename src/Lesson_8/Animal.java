package Lesson_8;

public abstract class Animal implements Vertebrate {
    protected Animal () {
        legCount = 4;
    }
    protected int legCount;
    abstract void eat (int feedAmount);
}
