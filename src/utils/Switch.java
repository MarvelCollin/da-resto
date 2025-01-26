package utils;

import interfaces.IMenuAction;

public class Switch {
    public static void execute(int choice, Runnable... functions) {
        if (choice > 0 && choice <= functions.length) {
            functions[choice - 1].run();
        }
    }
    
    public static boolean execute(int choice, IMenuAction... actions) {
        if (choice > 0 && choice <= actions.length) {
            return actions[choice - 1].execute();
        }
        return false;
    }
}
