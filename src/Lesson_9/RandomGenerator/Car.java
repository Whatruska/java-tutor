package Lesson_9.RandomGenerator;

public class Car {

    @RandomValue (min = 60, max = 300)
    private double maxSpeed;

    private double maxFuel;

    @RandomValue (min = 1905, max = 2021)
    private int ageOfProduction;

    private String model;
    private String stateNumber;

    public Car() {
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(double maxFuel) {
        this.maxFuel = maxFuel;
    }

    public int getAgeOfProduction() {
        return ageOfProduction;
    }

    public void setAgeOfProduction(int ageOfProduction) {
        this.ageOfProduction = ageOfProduction;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(String stateNumber) {
        this.stateNumber = stateNumber;
    }

    @Override
    public String toString() {
        return "Car{" +
                "maxSpeed=" + maxSpeed +
                ", maxFuel=" + maxFuel +
                ", ageOfProduction=" + ageOfProduction +
                ", model='" + model + '\'' +
                ", stateNumber='" + stateNumber + '\'' +
                '}';
    }
}
