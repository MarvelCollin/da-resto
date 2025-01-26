package models.Factory;

import models.Entity.Chef;
import utils.InitialsManager;

public class ChefFactory {
    private static ChefFactory instance;
    
    private ChefFactory() {}
    
    public static ChefFactory getInstance() {
        if(instance == null) {
            instance = new ChefFactory();
        }
        return instance;
    }
    
    public Chef createChef() {
        String initial = InitialsManager.getInstance().getUniqueInitial();
        return new Chef(initial);
    }
}
