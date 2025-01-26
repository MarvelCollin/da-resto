package controllers.Mediators;

import models.Restaurant;
import models.Entity.*;

public class RestaurantMediator {
    private Restaurant restaurant;
    private boolean isPaused = false;
    
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    
    public void pauseAllOperations() {
        isPaused = true;
        // Notify all entities to pause
    }
    
    public void resumeAllOperations() {
        isPaused = false;
        // Notify all entities to resume
    }
    
    public void customerNeedsOrder(Customer customer) {
        for (Waiter waiter : restaurant.getWaiters()) {
            if (waiter.isIdle()) {
                waiter.takeOrder(customer);
                return;
            }
        }
    }
    
    public void waiterNeedsChef(Waiter waiter, Customer customer) {
        for (Chef chef : restaurant.getChefs()) {
            if (chef.isIdle()) {
                chef.cookOrder(customer);
                waiter.setWaitingForChef(chef);
                return;
            }
        }
    }
    
    public void chefFinishedCooking(Chef chef, Customer customer) {
        for (Waiter waiter : restaurant.getWaiters()) {
            if (waiter.isWaitingForChef(chef)) {
                waiter.serveOrder(customer);
                return;
            }
        }
    }
    
    public void customerLeft(Customer customer) {
        restaurant.removeCustomer(customer);
    }
    
    public void hireWaiter() {
        // Logic to create and add new waiter
    }
    
    public void hireChef() {
        // Logic to create and add new chef
    }
}
