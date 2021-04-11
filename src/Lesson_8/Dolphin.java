package Lesson_8;

public class Dolphin extends Animal {
    private String sound;

    public Dolphin(String sound) {
        this.sound = sound;
    }

    @Override
    void eat(int feedAmount) {
        System.out.println("Dolphin ate " + feedAmount + " amount of food");
    }

    @Override
    public void move() {
        swim();
    }

    public void swim () {
        System.out.println("Dolphin is swimming " + this.sound + "!");
    }
}
