package by.teachmeskills.travelplaner.application.components.actions;

public class ShowCitiesAction extends BaseAction implements Action{
    @Override
    public void apply() {
        System.out.println("==============");
        travelController.getCities().forEach(System.out::println);
        System.out.println("==============");
    }
}
