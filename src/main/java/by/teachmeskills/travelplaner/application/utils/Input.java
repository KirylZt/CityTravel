package by.teachmeskills.travelplaner.application.utils;

import java.util.Scanner;

public class Input {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int getInt(){
        if (SCANNER.hasNextInt()){
            int result = SCANNER.nextInt();
            SCANNER.nextLine();
            return result;
        }
        System.out.print("Это не целое число!!! Повторите ввод: ");
        SCANNER.nextLine();
        return getInt();
    }

    public static int getInt(String message){
        System.out.println(message);
        return getInt();
    }

    public static double getDouble(){
        if (SCANNER.hasNextDouble()){
            double result = SCANNER.nextDouble();
            SCANNER.nextLine();
            return result;
        }
        System.out.print("Введено некорректное значение!!! Повторите ввод: ");
        SCANNER.nextLine();
        return getDouble();
    }

    public static double getDouble(String message){
        System.out.println(message);
        return getDouble();
    }

    public static boolean getBoolean(){
        if (SCANNER.hasNextBoolean()){
            boolean result = SCANNER.nextBoolean();
            SCANNER.nextLine();
            return result;
        }
        System.out.print("Введено некорректное значение!!! Повторите ввод: ");
        SCANNER.nextLine();
        return getBoolean();
    }

    public static boolean getBoolean(String message){
        System.out.println(message);
        return getBoolean();
    }

    public static String getString(){
        return SCANNER.nextLine();
    }

    public static String getString(String message){
        System.out.println(message);
        return getString();
    }
}
