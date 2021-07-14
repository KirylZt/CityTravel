package by.teachmeskills.travelplaner.application.components.actions;

import by.teachmeskills.travelplaner.application.utils.Input;
import by.teachmeskills.travelplaner.entity.City;

public class UpdateCityAction extends BaseAction implements Action{
    @Override
    public void apply() {
        City city = createCity();
        int id = Input.getInt("Введите id редактируемого города: ");
        city.setId(id);
        travelController.updateCity(city);
    }
}
