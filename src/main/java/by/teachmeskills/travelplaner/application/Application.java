package by.teachmeskills.travelplaner.application;

import by.teachmeskills.travelplaner.application.components.Button;
import by.teachmeskills.travelplaner.application.components.actions.StopApplicationAction;
import by.teachmeskills.travelplaner.application.utils.Input;
import by.teachmeskills.travelplaner.exceptions.StopApplicationException;

import java.util.Map;

public class Application {
    public void start(){
        System.out.println("Добро пожаловать!");
        run();
        System.out.println("Всего доброго!");
    }

    public void run(){
        while (true){
            Button button = selectButton();
            try {
                button.click();
            }catch (StopApplicationException e){
                break;
            }
        }
    }

    private Button selectButton(){
        showMenu();
        int key = Input.getInt();
        if(Config.BUTTONS.containsKey(key)){
            return Config.BUTTONS.get(key);
        }
        return selectButton();
    }

    private void showMenu(){
        System.out.println("Выберите действие");
        for (Map.Entry<Integer,Button> item: Config.BUTTONS.entrySet()){
            System.out.println(item.getKey() + " - " + item.getValue().getName());
        }
    }
}
