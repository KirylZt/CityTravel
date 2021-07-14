package by.teachmeskills.travelplaner.exceptions;

public class NoTransportException extends Exception{
    @Override
    public String getMessage() {
        return "Нет подходящего транспорта";
    }
}
