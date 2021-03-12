package thirdLesson.cats;

public class CatBuilder {
    private Cat buildCat;

    public CatBuilder() {
        buildCat = new Cat();
    }

    public CatBuilder name(String name) {
        this.buildCat.setName(name);
        return this;
    }

    public CatBuilder age(int age) {
        this.buildCat.setAge(age);
        return this;
    }

    public CatBuilder motherCat(Cat motherCat) {
        this.buildCat.setMotherCat(motherCat);
        return this;
    }

    public CatBuilder fatherCat(Cat fatherCat) {
        this.buildCat.setFatherCat(fatherCat);
        return this;
    }

    public CatBuilder color(String color) {
        this.buildCat.setColor(color);
        return this;
    }

    public Cat build() {
        return buildCat;
    }
}
