package by.teachmeskills.travelplaner.application.components.actions;

import by.teachmeskills.travelplaner.entity.Transport;

public class AddTransportAction extends BaseAction implements Action{
    @Override
    public void apply() {
        Transport transport = createTransport();
        travelController.addTransport(transport);
    }
}
