package by.teachmeskills.travelplaner.application.components.actions;

import by.teachmeskills.travelplaner.exceptions.StopApplicationException;

public class StopApplicationAction implements Action{
    @Override
    public void apply() {
        throw new StopApplicationException();
    }
}
