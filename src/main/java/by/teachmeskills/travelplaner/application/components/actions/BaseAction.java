package by.teachmeskills.travelplaner.application.components.actions;

import by.teachmeskills.travelplaner.application.utils.Input;
import by.teachmeskills.travelplaner.controllers.TravelController;
import by.teachmeskills.travelplaner.controllers.TravelControllerImpl;
import by.teachmeskills.travelplaner.entity.City;
import by.teachmeskills.travelplaner.entity.Transport;
import by.teachmeskills.travelplaner.entity.TransportType;

public abstract class BaseAction {
    protected TravelController travelController = new TravelControllerImpl();

    protected City createCity(){
        String name = Input.getString("Введите название города: ");
        double weight = Input.getDouble("Введите широту города: ");
        double length = Input.getDouble("Введите доготу города: ");
        boolean airport = Input.getBoolean("В городе есть аэропорт? ");
        boolean shipyard = Input.getBoolean("В городе есть морской порт? ");
        return new City(name,weight,length,airport,shipyard);
    }

    protected Transport createTransport(){
        String name = Input.getString("Введите название транспорта: ");
        int speed = Input.getInt("Введите рабочую скорость транспорта: ");
        int passengers = Input.getInt("Введите максимальное количество пасажиров: ");
        double load = Input.getDouble("Введите максимальный перевозимый груз в тоннах: ");
        int price = Input.getInt("Введите стоимость перевозки в $ за км: ");
        TransportType transportType = createTransportType();
        return new Transport(name,speed,passengers,load,transportType,price);
    }

    protected TransportType createTransportType(){
        int type = Input.getInt("Введите тип транспорта. 1 - наземный, 2 - воздушный, 3 - Морской");
        switch (type){
            case 1: return new TransportType(1,"Наземный");
            case 2: return new TransportType(2,"Воздушный");
            case 3: return new TransportType(3,"Морской");
            default: System.out.println("Некорректный ввод, попробуйте ещё раз");
            return createTransportType();
        }
    }
}
