package models.Factory;

import models.Entity.Waiter;
import utils.InitialsManager;

public class WaiterFactory {
    private static WaiterFactory instance;
    
    private WaiterFactory() {}
    
    public static WaiterFactory getInstance() {
        if(instance == null) {
            instance = new WaiterFactory();
        }
        return instance;
    }
    
    public Waiter createWaiter() {
        String initial = InitialsManager.getInstance().getUniqueInitial();
        return new Waiter(initial);
    }
}
