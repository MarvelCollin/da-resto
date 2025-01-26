package models;

import java.util.ArrayList;
import models.Entity.*;
import models.Factory.RestaurantFactory;
import utils.StringUtils;

public class Restaurant {
    
    public static Restaurant createRestaurant(String name) {
        return RestaurantFactory.getInstance().createRestaurant(name);
    }
    
    private String name;
    private ArrayList<Chef> chefs;
    private ArrayList<Waiter> waiters;
    private ArrayList<Customer> customers;
    
    Restaurant(String name) {
        this.name = name;
        this.chefs = new ArrayList<>();
        this.waiters = new ArrayList<>();
        this.customers = new ArrayList<>();
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
}
