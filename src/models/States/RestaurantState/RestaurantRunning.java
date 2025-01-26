package models.States.RestaurantState;

import models.Restaurant;
import models.Entity.*;

public class RestaurantRunning extends RestaurantState {
    @Override
    public void update(Restaurant restaurant) {
        // Update all entities through restaurant
        for (Chef chef : restaurant.getChefs()) {
            chef.update();
        }
        
        for (Waiter waiter : restaurant.getWaiters()) {
            waiter.update();
        }
        
        for (Customer customer : restaurant.getCustomers()) {
            customer.update();
        }
    }

    @Override
    public String getStateName() {
        return "Running";
    }
}
