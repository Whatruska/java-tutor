package Exam.models;

public class Item {
    private String id;
    private String name;
    private String producer;
    private String producerCity;

    public Item() {
    }

    public Item(String id, String name, String producer, String producerCity) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.producerCity = producerCity;
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

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getProducerCity() {
        return producerCity;
    }

    public void setProducerCity(String producerCity) {
        this.producerCity = producerCity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                ", producerCity='" + producerCity + '\'' +
                '}';
    }
}
