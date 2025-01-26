package models.States.RestaurantState;

import models.Restaurant;
import models.Entity.*;
import java.util.List;

public class RestaurantRunning extends RestaurantState {
    @Override
    public void update(Restaurant restaurant) {
        
        List<Chef> chefs = List.copyOf(restaurant.getChefs());
        List<Waiter> waiters = List.copyOf(restaurant.getWaiters());
        List<Customer> customers = List.copyOf(restaurant.getCustomers());
        
        
        for (Chef chef : chefs) {
            chef.update();
        }
        
        for (Waiter waiter : waiters) {
            waiter.update();
        }
        
        for (Customer customer : customers) {
            customer.update();
        }
    }

    @Override
    public String getStateName() {
        return "Running";
    }
}
