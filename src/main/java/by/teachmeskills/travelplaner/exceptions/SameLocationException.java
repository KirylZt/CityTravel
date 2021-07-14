package by.teachmeskills.travelplaner.exceptions;

public class SameLocationException extends Exception{

    @Override
    public String getMessage() {
        return String.format("Выбраны два одинаковых города.");
    }
}
