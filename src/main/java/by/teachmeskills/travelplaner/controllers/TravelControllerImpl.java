package by.teachmeskills.travelplaner.controllers;

import by.teachmeskills.travelplaner.dao.TravelDAO;
import by.teachmeskills.travelplaner.dao.TravelMysqlDAO;
import by.teachmeskills.travelplaner.entity.City;
import by.teachmeskills.travelplaner.entity.Transport;
import by.teachmeskills.travelplaner.exceptions.CityRequireException;
import by.teachmeskills.travelplaner.exceptions.NoTransportException;
import by.teachmeskills.travelplaner.exceptions.SameLocationException;

import java.util.List;

public class TravelControllerImpl implements TravelController{
    private TravelDAO dao = new TravelMysqlDAO();

    @Override
    public List<City> getCities() {
        return dao.getCities();
    }

    @Override
    public List<Transport> getTransports() {
        return dao.getTransports();
    }

    @Override
    public String fasterTravel(int id_from, int id_for, int passengers, int load) throws CityRequireException, NoTransportException, SameLocationException {
        return dao.fasterTravel(id_from,id_for,passengers,load);
    }

    @Override
    public String cheapestTravel(int id_from, int id_for, int passengers, int load) throws SameLocationException, CityRequireException, NoTransportException {
        return dao.cheapestTravel(id_from,id_for,passengers,load);
    }

    @Override
    public void addCity(City city) {
        dao.addCity(city);
    }

    @Override
    public void deleteCity(int id) {
        dao.deleteCity(id);
    }

    @Override
    public void updateCity(City city) {
        dao.updateCity(city);
    }

    @Override
    public void addTransport(Transport transport) {
        dao.addTransport(transport);
    }

    @Override
    public void deleteTransport(int id) {
        dao.deleteTransport(id);
    }

    @Override
    public void updateTransport(Transport transport) {
        dao.updateTransport(transport);
    }
}
