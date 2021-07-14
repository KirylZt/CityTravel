package by.teachmeskills.travelplaner.exceptions;

public class CityRequireException extends Exception{
    int id;

    public CityRequireException(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format("Город с id = %s не найден.",id);
    }
}
