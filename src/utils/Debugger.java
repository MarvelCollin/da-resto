package utils;

public class Debugger {
    
    public static boolean generalDebug = false;

    public static boolean chefStateDebug = false;
    public static boolean waiterStateDebug = false;
    public static boolean customerStateDebug = false;
    
    
    public static boolean chefActionDebug = false;
    public static boolean waiterActionDebug = false;
    public static boolean customerActionDebug = false;
    
    
    public static boolean mediatorDebug = false;
    public static boolean restaurantDebug = false;

    public static void chefDebug(String message) {
        if (chefStateDebug) {
            System.out.println("[Chef Debug] " + message);
        }
    }
    
    public static void waiterDebug(String message) {
        if (waiterStateDebug) {
            System.out.println("[Waiter Debug] " + message);
        }
    }
    
    public static void customerDebug(String message) {
        if (customerStateDebug) {
            System.out.println("[Customer Debug] " + message);
        }
    }

    public static void chefActionDebug(String message) {
        if (chefActionDebug) {
            System.out.println("[Chef Action Debug] " + message);
        }
    }

    public static void waiterActionDebug(String message) {
        if (waiterActionDebug) {
            System.out.println("[Waiter Action Debug] " + message);
        }
    }

    public static void customerActionDebug(String message) {
        if (customerActionDebug) {
            System.out.println("[Customer Action Debug] " + message);
        }
    }

    public static void mediatorDebug(String message) {
        if (mediatorDebug) {
            System.out.println("[Mediator Debug] " + message);
        }
    }

    public static void restaurantDebug(String message) {
        if (restaurantDebug) {
            System.out.println("[Restaurant Debug] " + message);
        }
    }

    public static void restaurantInitializationDebug(String message) {
        if (restaurantDebug) {
            System.out.println("[Restaurant Initialization Debug] " + message);
        }
    }

    public static void generalDebug(String message) {
        if (generalDebug) {
            System.out.println("[General Debug] " + message);
        }
    }
}
