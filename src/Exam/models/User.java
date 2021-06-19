package Exam.models;

public class User {
    private String id;
    private String name;
    private String year;
    private String city;

    public User() {
    }

    public User(String id, String name, String year, String city) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
