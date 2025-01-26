package models;

import interfaces.IRestaurantState;
import models.Entity.*;
import models.Factory.RestaurantFactory;
import models.States.RestaurantState.RestaurantRunning;
import utils.StringUtils;

import java.util.ArrayList;

public class Restaurant {
    private static Restaurant activeRestaurant;
    private int money = 1300;
    private int score = 0;
    private int seats = 4;
    private IRestaurantState state;
    
    public static Restaurant createRestaurant(String name) {
        activeRestaurant = RestaurantFactory.getInstance().createRestaurant(name);
        return activeRestaurant;
    }
    
    public static Restaurant getActiveRestaurant() {
        return activeRestaurant;
    }
    
    private String name;
    private ArrayList<Chef> chefs;
    private ArrayList<Waiter> waiters;
    private ArrayList<Customer> customers;
    
    public Restaurant(String name) {
        this.name = name;
        this.chefs = new ArrayList<>();
        this.waiters = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.state = new RestaurantRunning();
        this.state.onEnter(this);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public ArrayList<Chef> getChefs() { return chefs; }
    public ArrayList<Waiter> getWaiters() { return waiters; }
    public ArrayList<Customer> getCustomers() { return customers; }
    
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
        this.state.onEnter(this);
    }
    
    public void update() {
        if (state != null) {
            state.update(this);
        }
    }
}
