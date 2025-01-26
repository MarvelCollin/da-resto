package models.Factory;

import models.Restaurant;
import models.Entity.Chef;
import models.Entity.Waiter;
import models.Entity.Entity;
import utils.Constants;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RestaurantFactory {
    private static RestaurantFactory instance;
    private ChefFactory chefFactory;
    private WaiterFactory waiterFactory;
    
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
    
    private <T extends Entity> void createMultipleEntities(int count, Supplier<T> factory, Consumer<T> addMethod) {
        for(int i = 0; i < count; i++) {
            T entity = factory.get();
            addMethod.accept(entity);
        }
    }
    
    public Restaurant createRestaurant() {
        Restaurant restaurant = new Restaurant();
        
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
