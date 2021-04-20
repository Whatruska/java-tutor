package Lesson_9.RandomGenerator;

public class Loader {
    public static void main(String[] args) {
        Car car = new Car();
        car.setModel("Kia Rio");
        car.setAgeOfProduction(2002);
        car.setMaxSpeed(150);
        car.setMaxFuel(153.7);
        car.setStateNumber("O777OO116");

        for (int i = 0; i < 100; i++) {
            RandomNumberGenerator.process(car);

            System.out.println(car);
        }
    }
}
