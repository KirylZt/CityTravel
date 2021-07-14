package by.teachmeskills.travelplaner.application.components.actions;

public class ShowTransportAction extends BaseAction implements Action{
    @Override
    public void apply() {
        System.out.println("==============");
        travelController.getTransports().forEach(System.out::println);
        System.out.println("==============");
    }
}
