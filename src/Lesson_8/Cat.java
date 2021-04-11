package Lesson_8;

public class Cat extends Animal {
    private String name;

    public Cat(String name) {
        this.name = name;
        legCount = 4;
    }

    private Cat() {
        this("Сберкот");
    }

    @Override
    void eat(int feedAmount) {
        System.out.println("Cat " + this.name + " ate " + feedAmount + " amount of food");
    }

    @Override
    public void move() {
        System.out.println(this.name + " is walking");
    }

    private String meow (String meowSound, int count) {
        return this.name + " is meowing: " + meowSound;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "legCount=" + legCount +
                ", name='" + name + '\'' +
                '}';
    }
}
