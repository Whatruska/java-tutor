package Control.classes;

import Control.annotations.Bean;
import Control.annotations.FieldName;

@Bean(fileName = "Figures")
public class Circle {
    private Double x;
    private Double y;

    @FieldName(name = "r")
    private Double radius;
    private String color;

    public Circle() {
    }

    public Circle(Double x, Double y, Double radius, String color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
