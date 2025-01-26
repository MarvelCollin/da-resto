package models.States.RestaurantState;

import models.Restaurant;
import models.Entity.*;
import utils.Debugger;
import java.util.List;

public class RestaurantRunning extends RestaurantState {
    @Override
    public void onEnter(Restaurant restaurant) {
        if (restaurant.getMediator() != null) {
            Debugger.restaurantDebug("Restaurant entered Running state");
            restaurant.getMediator().resumeAllOperations();
        } else {
            Debugger.restaurantDebug("Warning: Restaurant mediator not set during Running state enter");
        }
    }

    @Override
    public void update(Restaurant restaurant) {
        if (restaurant.getMediator().isPaused()) {
            restaurant.setState(new RestaurantPaused());
            return;
        }
        
        List<Chef> chefs = List.copyOf(restaurant.getChefs());
        List<Waiter> waiters = List.copyOf(restaurant.getWaiters());
        List<Customer> customers = List.copyOf(restaurant.getCustomers());
        
        for (Chef chef : chefs) chef.update();
        for (Waiter waiter : waiters) waiter.update();
        for (Customer customer : customers) customer.update();
    }

    @Override
    public String getStateName() {
        return "Running";
    }
}
