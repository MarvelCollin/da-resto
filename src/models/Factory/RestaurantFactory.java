package models.Factory;

import models.Restaurant;
import models.Entity.*;
import utils.Constants;
import interfaces.ICreator;
import interfaces.IHandler;

public class RestaurantFactory {
    private static RestaurantFactory instance;
    private final ChefFactory chefFactory;
    private final WaiterFactory waiterFactory;
    
    private RestaurantFactory() {
        chefFactory = ChefFactory.getInstance();
        waiterFactory = WaiterFactory.getInstance();
    }
    
    public static RestaurantFactory getInstance() {
        if(instance == null) {
            instance = new RestaurantFactory();
        }
        return instance;
    }
    
    private <T extends Entity> void createMultipleEntities(int count, ICreator<T> creator, IHandler<T> adder) {
        for(int i = 0; i < count; i++) {
            adder.handle(creator.create());
        }
    }
    
    public Restaurant createRestaurant(String name) {
        Restaurant restaurant = new Restaurant(name);
        
        createMultipleEntities(
            Constants.DEFAULT_CHEFS,
            () -> chefFactory.createChef(),
            restaurant::addChef
        );
        
        createMultipleEntities(
            Constants.DEFAULT_WAITERS,
            () -> waiterFactory.createWaiter(),
            restaurant::addWaiter
        );
        
        return restaurant;
    }
}
