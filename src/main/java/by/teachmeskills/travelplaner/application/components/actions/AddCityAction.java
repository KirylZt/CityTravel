package by.teachmeskills.travelplaner.application.components.actions;

import by.teachmeskills.travelplaner.entity.City;

public class AddCityAction extends BaseAction implements Action{
    @Override
    public void apply() {
        City city = createCity();
        travelController.addCity(city);
    }
}
