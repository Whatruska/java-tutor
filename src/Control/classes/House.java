package Control.classes;

import Control.annotations.Bean;
import Control.annotations.FieldName;

@Bean(fileName = "Buildings")
public class House {

    private String number;

    private Double square;

    @FieldName(name = "city")
    private String cityName;

    public House() {

    }

    public House(String number, Double square) {
        this.number = number;
        this.square = square;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getSquare() {
        return square;
    }

    public void setSquare(Double square) {
        this.square = square;
    }
}
