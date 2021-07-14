package by.teachmeskills.travelplaner.application;

import by.teachmeskills.travelplaner.application.components.Button;
import by.teachmeskills.travelplaner.application.components.actions.*;

import java.util.HashMap;
import java.util.Map;

public class Config {
    public static final Map<Integer, Button> BUTTONS = new HashMap<>();

    static{
        BUTTONS.put(1, new Button("Получить список доступных городов", new ShowCitiesAction()));
        BUTTONS.put(2, new Button("Получить список доступного транспорта",new ShowTransportAction()));
        BUTTONS.put(3, new Button("Поиск быстрого маршрута",new FasterTravelAction()));
        BUTTONS.put(4, new Button("Поиск дешевого маршрута",new CheapestTravelAction()));
        BUTTONS.put(5, new Button("Добавить новый город",new AddCityAction()));
        BUTTONS.put(6, new Button("Удалить город",new DeleteCityAction()));
        BUTTONS.put(7, new Button("Отредактировать город",new UpdateCityAction()));
        BUTTONS.put(8, new Button("Добавить новый транспорт",new AddTransportAction()));
        BUTTONS.put(9, new Button("Удалить транспорт",new DeleteTransportAction()));
        BUTTONS.put(10, new Button("Отредактировать транспорт",new UpdateTransportAction()));
        BUTTONS.put(11, new Button("Выход",new StopApplicationAction()));
    }
}
