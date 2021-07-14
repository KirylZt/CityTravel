package by.teachmeskills.travelplaner.application.components.actions;

import by.teachmeskills.travelplaner.application.utils.Input;

public class DeleteTransportAction extends BaseAction implements Action{
    @Override
    public void apply() {
        int id = Input.getInt("Введите id транспорта для удаления: ");
        travelController.deleteTransport(id);
    }
}
