package Lesson_1.comp;

public class Cat implements Comparable<Cat>{
    private int weight;
    private String name;

    public Cat(int weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void feed(int amount) throws Exception {
        if (amount >= 0) {
            this.weight += amount;
        } else {
            throw new Exception("Нефиг у кота корм отбирать!");
        }
    }

    public void meow() throws Exception {
        if (this.weight > 0) {
            this.weight--;
        } else {
            throw new Exception("Отстань, нет сил мяукать!");
        }
    }

    @Override
    public int compareTo(Cat o) {
        if (this.weight > o.getWeight()) {
            return 1;
        } else if (this.weight == o.getWeight()) {
            return 0;
        } else {
            return -1;
        }
    }
}
