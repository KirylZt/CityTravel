package by.teachmeskills.travelplaner.application.components.actions;

import by.teachmeskills.travelplaner.application.utils.Input;
import by.teachmeskills.travelplaner.exceptions.CityRequireException;
import by.teachmeskills.travelplaner.exceptions.NoTransportException;
import by.teachmeskills.travelplaner.exceptions.SameLocationException;

public class CheapestTravelAction extends BaseAction implements Action{
    @Override
    public void apply() {
        int idFrom = Input.getInt("Введите номер города из которого будет совершёна отправка");
        int idFor = Input.getInt("Введите номер города куда будет совершёна отправка");
        int passengers = Input.getInt("Введите количество пассажиров");
        int load = Input.getInt("Введите перевозимый груз в тоннах");
        try {
            System.out.println("=======================================================");
            System.out.println(travelController.cheapestTravel(idFrom,idFor,passengers,load));
            System.out.println("=======================================================");
        } catch (CityRequireException | NoTransportException | SameLocationException e) {
            System.out.println(e.getMessage());
        }
    }
}
