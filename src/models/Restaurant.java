package models;

import java.util.ArrayList;

import models.Entity.Chef;
import models.Entity.Customer;
import models.Entity.Waiter;
import utils.Constants;

public class Restaurant {
    private ArrayList<Chef> chefs;
    private ArrayList<Waiter> waiters;
    private ArrayList<Customer> customers;

    public Restaurant() {
        chefs = new ArrayList<Chef>();
        waiters = new ArrayList<Waiter>();
        customers = new ArrayList<Customer>();
    }

    public void addChef(Chef chef) {
        chefs.add(chef);
    }

    public void addWaiter(Waiter waiter) {
        waiters.add(waiter);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeChef(Chef chef) {
        chefs.remove(chef);
    }

    public void removeWaiter(Waiter waiter) {
        waiters.remove(waiter);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    public ArrayList<Chef> getChefs() {
        return chefs;
    }

    public ArrayList<Waiter> getWaiters() {
        return waiters;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public String getChefInitials() {
        String initials = "";
        for (Chef chef : chefs) {
            initials += chef.getInitial();
        }
        return initials;
    }

    public String getWaiterInitials() {
        String initials = "";
        for (Waiter waiter : waiters) {
            initials += waiter.getInitial();
        }
        return initials;
    }

    public String getCustomerInitials() {
        String initials = "";
        for (Customer customer : customers) {
            initials += customer.getInitial();
        }
        return initials;
    }
    
}
