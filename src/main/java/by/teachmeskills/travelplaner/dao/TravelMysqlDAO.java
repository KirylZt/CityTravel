package by.teachmeskills.travelplaner.dao;

import by.teachmeskills.travelplaner.connections.MysqlConnections;
import by.teachmeskills.travelplaner.entity.City;
import by.teachmeskills.travelplaner.entity.Transport;
import by.teachmeskills.travelplaner.entity.TransportType;
import by.teachmeskills.travelplaner.exceptions.CityRequireException;
import by.teachmeskills.travelplaner.exceptions.NoTransportException;
import by.teachmeskills.travelplaner.exceptions.SameLocationException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TravelMysqlDAO implements TravelDAO{

    @Override
    public List<City> getCities() {
        List<City> cities = new ArrayList<>();
        try(Connection connection = MysqlConnections.getConnection()){
            String sql = "SELECT * FROM cities";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                cities.add(
                        new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("weight"),
                        resultSet.getDouble("length"),
                        resultSet.getBoolean("airport"),
                        resultSet.getBoolean("shipyard")
                        ));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return cities;
    }

    @Override
    public List<Transport> getTransports() {
        List<Transport> transports = new ArrayList<>();
        try(Connection connection = MysqlConnections.getConnection()){
            String sql = "SELECT p.*, c.name as 'type_name' FROM transport p LEFT JOIN types c on p.type_id = c.id";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                transports.add(
                        new Transport(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("speed"),
                                resultSet.getInt("passengers"),
                                resultSet.getDouble("load"),
                                new TransportType(
                                        resultSet.getInt("type_id"),
                                        resultSet.getString("type_name")
                                ),
                                resultSet.getInt("price")
                        ));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return transports;
    }

    @Override
    public String fasterTravel(int id_from, int id_for, int passengers, int load) throws SameLocationException, CityRequireException, NoTransportException {
        City city_from = getCity(id_from);
        City city_for = getCity(id_for);
        if (city_from.equals(city_for)) {
            throw new SameLocationException();
        }
        List<Transport> transports = getCorrectTransport(city_from,city_for,passengers,load); //Возращает непустой список подходящего транспорта
        Transport transport = new Transport();
        for (Transport t: transports) {
            if (t.getSpeed()>transport.getSpeed()){
                transport = t;
            }
        }
        double time = distance(city_from,city_for)/transport.getSpeed();
        return String.format("Самый быстрый транспорт для доставки %d человек и %dт груза из %s в %s - это %s. Время в пути %.1f часа. Стоимость %.0f$.",passengers,load,city_from.getName(),city_for.getName(),transport.getName(),time,time*transport.getPrice());
    }

    private double distance(City cityFrom, City cityFor){
        final int R = 6371;
        double fa = Math.toRadians(cityFrom.getWeight());
        double fb = Math.toRadians(cityFor.getWeight());
        double la = Math.toRadians(cityFrom.getLength());
        double lb = Math.toRadians(cityFor.getLength());
        double d = Math.acos(Math.sin(fa)*Math.sin(fb)+Math.cos(fa)*Math.cos(fb)*Math.cos(la-lb));
        return R*d;
    }

    private List<Transport> getCorrectTransport(City cityFrom, City cityFor, int passengers, int load) throws NoTransportException {
        List<Transport> transports = new ArrayList<>();
        try(Connection connection = MysqlConnections.getConnection()){
            String sql = String.format("SELECT p.*, c.name as 'type_name' FROM transport p LEFT JOIN types c on p.type_id = c.id  WHERE `load`>=%d AND passengers>=%d AND type_id<>2 AND type_id<>3",load,passengers);
            if (cityFor.isAirport() && cityFrom.isAirport()) {
                sql = String.format("SELECT p.*, c.name as 'type_name' FROM transport p LEFT JOIN types c on p.type_id = c.id  WHERE `load`>=%d AND passengers>=%d AND type_id<>3",load,passengers);
            }
            if (cityFor.isShipyard() && cityFrom.isShipyard()) {
                sql = String.format("SELECT p.*, c.name as 'type_name' FROM transport p LEFT JOIN types c on p.type_id = c.id  WHERE `load`>=%d AND passengers>=%d AND type_id<>2",load,passengers);
            }
            if (cityFor.isAirport() && cityFrom.isAirport() && cityFor.isShipyard() && cityFrom.isShipyard()) {
                sql = String.format("SELECT p.*, c.name as 'type_name' FROM transport p LEFT JOIN types c on p.type_id = c.id  WHERE `load`>=%d AND passengers>=%d",load,passengers);
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                transports.add(
                        new Transport(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("speed"),
                                resultSet.getInt("passengers"),
                                resultSet.getDouble("load"),
                                new TransportType(
                                        resultSet.getInt("type_id"),
                                        resultSet.getString("type_name")
                                ),
                                resultSet.getInt("price")
                        ));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        if(transports.isEmpty()){
            throw new NoTransportException();
        }
        return transports;
    }

    private City getCity(int id) throws CityRequireException {
        City result = null;
        try(Connection connection = MysqlConnections.getConnection()){
            String sql = "SELECT * FROM cities WHERE id="+id;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                result = new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("weight"),
                        resultSet.getDouble("length"),
                        resultSet.getBoolean("airport"),
                        resultSet.getBoolean("shipyard")
                );
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        if (result == null){
            throw new CityRequireException(id);
        }
        return result;
    }


    @Override
    public void addCity(City city) {
        try(Connection connection = MysqlConnections.getConnection()) {
            String sql = "INSERT INTO cities (name, weight, length, airport, shipyard) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,city.getName());
            statement.setDouble(2,city.getWeight());
            statement.setDouble(3,city.getLength());
            statement.setBoolean(4,city.isAirport());
            statement.setBoolean(5,city.isShipyard());
            statement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void deleteCity(int id) {
        try(Connection connection = MysqlConnections.getConnection()) {
            String sql = "DELETE FROM cities WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void updateCity(City city) {
        try(Connection connection = MysqlConnections.getConnection()) {
            String sql = "UPDATE cities SET name = ?, weight = ?, length = ?, airport = ?, shipyard = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,city.getName());
            statement.setDouble(2,city.getWeight());
            statement.setDouble(3,city.getLength());
            statement.setBoolean(4,city.isAirport());
            statement.setBoolean(5,city.isShipyard());
            statement.setInt(6,city.getId());
            statement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public String cheapestTravel(int id_from, int id_for, int passengers, int load) throws SameLocationException, CityRequireException, NoTransportException {
        City city_from = getCity(id_from);
        City city_for = getCity(id_for);
        if (city_from.equals(city_for)) {
            throw new SameLocationException();
        }
        List<Transport> transports = getCorrectTransport(city_from,city_for,passengers,load); //Возращает непустой список подходящего транспорта
        Transport transport = transports.get(0);
        for (Transport t: transports) {
            if (t.getPrice()<transport.getPrice()){
                transport = t;
            }
        }
        double time = distance(city_from,city_for)/transport.getSpeed();
        return String.format("Самый дешёвый транспорт для доставки %d человек и %dт груза из %s в %s - это %s. Время в пути %.1f часа. Стоимость %.0f$.",passengers,load,city_from.getName(),city_for.getName(),transport.getName(),time,time*transport.getPrice());
    }

    @Override
    public void addTransport(Transport transport) {
        try(Connection connection = MysqlConnections.getConnection()) {
            String sql = "INSERT INTO transport (`name`, `speed`, `passengers`, `load`, `price`, `type_id`) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,transport.getName());
            statement.setInt(2,transport.getSpeed());
            statement.setInt(3,transport.getPassengers());
            statement.setDouble(4,transport.getLoad());
            statement.setInt(5,transport.getPrice());
            statement.setInt(6,transport.getTransportType().getId());
            statement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void deleteTransport(int id) {
        try(Connection connection = MysqlConnections.getConnection()) {
            String sql = "DELETE FROM transport WHERE `id` = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void updateTransport(Transport transport) {
        try(Connection connection = MysqlConnections.getConnection()) {
            String sql = "UPDATE transport SET `name` = ?, `speed` = ?, `passengers` = ?, `load` = ?, `price` = ?, `type_id` = ? WHERE `id` = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,transport.getName());
            statement.setInt(2,transport.getSpeed());
            statement.setInt(3,transport.getPassengers());
            statement.setDouble(4,transport.getLoad());
            statement.setInt(5,transport.getPrice());
            statement.setInt(6,transport.getTransportType().getId());
            statement.setInt(7,transport.getId());
            statement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
