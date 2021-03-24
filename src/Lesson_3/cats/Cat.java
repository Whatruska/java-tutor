package Lesson_3.cats;

public class Cat {
    private String name;
    private int age;
    Cat fatherCat;
    Cat motherCat;
    String color;

    public Cat() {
    }

    public Cat(String name, int age, Cat fatherCat, Cat motherCat, String color) {
        this.name = name;
        this.age = age;
        this.fatherCat = fatherCat;
        this.motherCat = motherCat;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Cat getFatherCat() {
        return fatherCat;
    }

    public void setFatherCat(Cat fatherCat) {
        this.fatherCat = fatherCat;
    }

    public Cat getMotherCat() {
        return motherCat;
    }

    public void setMotherCat(Cat motherCat) {
        this.motherCat = motherCat;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", fatherCat=" + fatherCat +
                ", motherCat=" + motherCat +
                ", color='" + color + '\'' +
                '}';
    }
}
