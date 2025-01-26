package models;

import interfaces.IRestaurantState;
import models.Entity.*;
import models.Factory.RestaurantFactory;
import models.States.RestaurantState.RestaurantRunning;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import controllers.Mediators.RestaurantMediator;

public class Restaurant {
    private static Restaurant activeRestaurant;
    private int money = 1300;
    private int score = 0;
    private int seats = 4;
    private IRestaurantState state;
    private RestaurantMediator mediator;
    
    public static Restaurant createRestaurant(String name) {
        activeRestaurant = RestaurantFactory.getInstance().createRestaurant(name);
        return activeRestaurant;
    }
    
    public static Restaurant getActiveRestaurant() {
        return activeRestaurant;
    }
    
    private String name;
    private CopyOnWriteArrayList<Chef> chefs;
    private CopyOnWriteArrayList<Waiter> waiters;
    private CopyOnWriteArrayList<Customer> customers;
    
    public Restaurant(String name) {
        this.name = name;
        this.chefs = new CopyOnWriteArrayList<>();
        this.waiters = new CopyOnWriteArrayList<>();
        this.customers = new CopyOnWriteArrayList<>();
        
    }

    
    public void initialize() {
        if (this.state == null) {
            this.state = new RestaurantRunning();
            this.state.onEnter(this);
        }
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public CopyOnWriteArrayList<Chef> getChefs() { return chefs; }
    public CopyOnWriteArrayList<Waiter> getWaiters() { return waiters; }
    public CopyOnWriteArrayList<Customer> getCustomers() { return customers; }
    
    public void addChef(Chef chef) { chefs.add(chef); }
    public void addWaiter(Waiter waiter) { waiters.add(waiter); }
    public void addCustomer(Customer customer) { customers.add(customer); }

    public void removeChef(Chef chef) { chefs.remove(chef); }
    public void removeWaiter(Waiter waiter) { waiters.remove(waiter); }
    public void removeCustomer(Customer customer) { customers.remove(customer); }

    
    public String getChefInitials() {
        return StringUtils.joinInitials(chefs, Chef::getInitial);
    }
    
    public String getWaiterInitials() {
        return StringUtils.joinInitials(waiters, Waiter::getInitial);
    }
    
    public String getCustomerInitials() {
        return StringUtils.joinInitials(customers, Customer::getInitial);
    }
    
    public int getMoney() { return money; }
    public void setMoney(int money) { this.money = money; }
    
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    
    public int getSeats() { return seats; }
    public void setSeats(int seats) { this.seats = seats; }
    
    public IRestaurantState getState() {
        return state;
    }
    
    public void setState(IRestaurantState newState) {
        if (this.state != null) {
            this.state.onExit(this);
        }
        this.state = newState;
        if (this.state != null) {
            this.state.onEnter(this);
        }
    }
    
    public RestaurantMediator getMediator() {
        return mediator;
    }
    
    public void setMediator(RestaurantMediator mediator) {
        this.mediator = mediator;
    }
    
    public void update() {
        if (state != null) {
            state.update(this);
        }
    }
}
