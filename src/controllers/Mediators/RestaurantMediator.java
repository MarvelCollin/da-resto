package controllers.Mediators;

import models.Restaurant;
import models.Entity.*;
import models.Factory.CustomerFactory;
import models.States.ChefState.ChefIdle;
import models.States.WaiterState.WaiterIdle;
import interfaces.ICustomerObserver;

import java.util.*;

import controllers.Observers.CustomerGenerator;

public class RestaurantMediator implements ICustomerObserver {
    private Restaurant restaurant;
    private boolean isPaused;
    private Random random;
    
    public RestaurantMediator() {
        this.random = new Random();
    }
    
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        CustomerGenerator.getInstance().setRestaurant(restaurant);
        CustomerGenerator.getInstance().addObserver(this);
        new Thread(CustomerGenerator.getInstance()).start();
    }

    @Override
    public void onCustomerGenerated() {
        Customer customer = CustomerFactory.getInstance().createCustomer();
        restaurant.addCustomer(customer);
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

    public void assignCustomerToWaiter(Customer customer) {
        for (Waiter waiter : restaurant.getWaiters()) {
            if (waiter.getState() instanceof WaiterIdle) {
                waiter.handleOrder(customer);
                return;
            }
        }
    }

    public void assignOrderToChef(Waiter waiter, Customer customer) {
        for (Chef chef : restaurant.getChefs()) {
            if (chef.getState() instanceof ChefIdle) {
                chef.handleOrder(customer);
                return;
            }
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
