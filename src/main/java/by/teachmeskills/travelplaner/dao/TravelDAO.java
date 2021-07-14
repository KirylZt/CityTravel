package by.teachmeskills.travelplaner.dao;

import by.teachmeskills.travelplaner.entity.City;
import by.teachmeskills.travelplaner.entity.Transport;
import by.teachmeskills.travelplaner.exceptions.CityRequireException;
import by.teachmeskills.travelplaner.exceptions.NoTransportException;
import by.teachmeskills.travelplaner.exceptions.SameLocationException;

import java.util.List;

public interface TravelDAO {

    List<City> getCities();

    List<Transport> getTransports();

    String fasterTravel(int id_from, int id_for, int passengers, int load) throws SameLocationException, CityRequireException, NoTransportException;

    String cheapestTravel(int id_from, int id_for, int passengers, int load) throws SameLocationException, CityRequireException, NoTransportException;

    void addCity(City city);

    void deleteCity(int id);

    void updateCity(City city);

    void addTransport(Transport transport);

    void deleteTransport(int id);

    void updateTransport(Transport transport);


}
