package by.teachmeskills.travelplaner.entity;

import java.util.Objects;

public class City {
    private int id;
    private String name;
    private double weight;
    private double length;
    private boolean airport;
    private boolean shipyard;

    public City(int id, String name, double width, double length, boolean airport, boolean shipyard) {
        this.id = id;
        this.name = name;
        this.weight = width;
        this.length = length;
        this.airport = airport;
        this.shipyard = shipyard;
    }

    public City(String name, double weight, double length, boolean airport, boolean shipyard) {
        this.name = name;
        this.weight = weight;
        this.length = length;
        this.airport = airport;
        this.shipyard = shipyard;
    }

    public City() {
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public boolean isAirport() {
        return airport;
    }

    public void setAirport(boolean airport) {
        this.airport = airport;
    }

    public boolean isShipyard() {
        return shipyard;
    }

    public void setShipyard(boolean shipyard) {
        this.shipyard = shipyard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", width=" + weight +
                ", length=" + length +
                ", airport=" + airport +
                ", shipyard=" + shipyard +
                '}';
    }
}
