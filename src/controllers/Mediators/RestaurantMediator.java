package controllers.Mediators;

import models.Restaurant;
import models.Entity.*;
import models.Factory.CustomerFactory;
import java.util.*;

public class RestaurantMediator {
    private Restaurant restaurant;
    private boolean isPaused;
    private Random random;
    
    public RestaurantMediator() {
        this.random = new Random();
    }
    
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void updateEntities() {
        if (isPaused || restaurant == null) return;
        trySpawnCustomer();
    }
    
    private void trySpawnCustomer() {
        if (random.nextInt(100) < 25 && 
            restaurant.getCustomers().size() < restaurant.getSeats()) {
            Customer customer = CustomerFactory.getInstance().createCustomer();
            restaurant.addCustomer(customer);
        }
    }

    public void pauseAllOperations() {
        isPaused = true;
        for (Chef chef : restaurant.getChefs()) {
            chef.pause();
        }
        for (Waiter waiter : restaurant.getWaiters()) {
            waiter.pause();
        }
        for (Customer customer : restaurant.getCustomers()) {
            customer.pause();
        }
    }

    public void resumeAllOperations() {
        isPaused = false;
        for (Chef chef : restaurant.getChefs()) {
            chef.resume();
        }
        for (Waiter waiter : restaurant.getWaiters()) {
            waiter.resume();
        }
        for (Customer customer : restaurant.getCustomers()) {
            customer.resume();
        }
    }

    public void shutdown() {
        for (Chef chef : restaurant.getChefs()) {
            chef.stop();
        }
        for (Waiter waiter : restaurant.getWaiters()) {
            waiter.stop();
        }
        for (Customer customer : restaurant.getCustomers()) {
            customer.stop();
        }
    }
}
