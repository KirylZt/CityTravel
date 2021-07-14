package by.teachmeskills.travelplaner.application.components.actions;

import by.teachmeskills.travelplaner.application.utils.Input;
import by.teachmeskills.travelplaner.entity.Transport;

public class UpdateTransportAction extends BaseAction implements Action{
    @Override
    public void apply() {
        Transport transport = createTransport();
        int id = Input.getInt("Введите id транспорта для замены: ");
        transport.setId(id);
        travelController.updateTransport(transport);
    }
}
