package ir.librarymanagement.util;

import java.util.List;

import static ir.librarymanagement.util.Color.*;

public class Printer {
    public static void printMenu(String[] menu , String message) {
        System.out.println(CYAN+"* * * "+CYAN+ message +RESET+CYAN+" * * *"+RESET);
        System.out.println(PURPLE+"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*"+RESET);
        for (int i = 0; i < menu.length; i++) {
            System.out.printf(CYAN+"%d. %s \n"+RESET , i+1 , CYAN + menu[i] + RESET);
        }
    }
    public static void printDescription(String description) {
        System.out.println(GREEN+description+RESET);
    }
    public static void printError(String error){
        System.out.println(RED+error+RESET);
    }
    public static void printLine1(String line) {
        System.out.println(PURPLE+line+RESET);
    }
    public static void printLine (int numberOfLine){
        for (int i = 0; i <= numberOfLine; i++) {
            System.out.print(PURPLE+"*-"+RESET);
        }
        System.out.println();
    }
    public static <T> void printList(List<T> list) {
        for (T t :list){
            System.out.println(BLUE+t+RESET);
        }
    }
}
