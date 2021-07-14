package by.teachmeskills.travelplaner.application.components.actions;

import by.teachmeskills.travelplaner.application.utils.Input;

public class DeleteCityAction extends BaseAction implements Action{
    @Override
    public void apply() {
        int id = Input.getInt("Введите id города для удаления: ");
        travelController.deleteCity(id);
    }
}
