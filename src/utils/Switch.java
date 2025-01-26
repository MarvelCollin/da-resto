package utils;

public class Switch {
    public static void execute(int choice, Runnable... functions) {
        if (choice > 0 && choice <= functions.length) {
            functions[choice - 1].run();
        }
    }
}
