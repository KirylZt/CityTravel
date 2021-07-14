package by.teachmeskills.travelplaner.entity;

import java.util.Objects;

public class Transport {
    private int id;
    private String name;
    private int speed;
    private int passengers;
    private double load;
    private TransportType transportType;
    private int price;

    public Transport(int id, String name, int speed, int passengers, double load, TransportType transportType, int price) {
        this.id = id;
        this.name = name;
        this.speed = speed;
        this.passengers = passengers;
        this.load = load;
        this.transportType = transportType;
        this.price = price;
    }

    public Transport(String name, int speed, int passengers, double load, TransportType transportType, int price) {
        this.name = name;
        this.speed = speed;
        this.passengers = passengers;
        this.load = load;
        this.transportType = transportType;
        this.price = price;
    }

    public Transport(int id) {
        this.id = id;
    }

    public Transport() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public double getLoad() {
        return load;
    }

    public void setLoad(double load) {
        this.load = load;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transport transport = (Transport) o;
        return id == transport.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Transport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", speed=" + speed +
                ", passengers=" + passengers +
                ", load=" + load +
                ", transportType=" + transportType +
                ", price=" + price +
                '}';
    }
}
