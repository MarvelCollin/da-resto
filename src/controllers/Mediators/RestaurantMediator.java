package controllers.Mediators;

import models.Restaurant;
import models.Entity.*;
import models.Factory.CustomerFactory;
import models.States.ChefState.ChefIdle;
import models.States.WaiterState.WaiterIdle;
import interfaces.ICustomerObserver;

import java.util.*;
import java.util.HashSet;
import java.util.Set;

import controllers.Observers.CustomerGenerator;

public class RestaurantMediator implements ICustomerObserver {
    private Restaurant restaurant;
    private boolean isPaused;
    private Random random;
    private Set<Customer> customersBeingServed;
    private Map<Customer, Chef> customerChefAssignments = new HashMap<>();
    
    public RestaurantMediator() {
        this.random = new Random();
        this.customersBeingServed = new HashSet<>();
    }
    
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        
        
        for (Chef chef : restaurant.getChefs()) {
            chef.setMediator(this);
        }
        for (Waiter waiter : restaurant.getWaiters()) {
            waiter.setMediator(this);
        }
        for (Customer customer : restaurant.getCustomers()) {
            customer.setMediator(this);
        }
        
        CustomerGenerator.getInstance().setRestaurant(restaurant);
        CustomerGenerator.getInstance().addObserver(this);
        new Thread(CustomerGenerator.getInstance()).start();
    }

    @Override
    public void onCustomerGenerated() {
        Customer customer = CustomerFactory.getInstance().createCustomer();
        customer.setMediator(this);
        restaurant.addCustomer(customer);
        processNewCustomer(customer);
    }
    
    public void updateEntities() {
        if (isPaused || restaurant == null) return;
        
        
        for (Chef chef : restaurant.getChefs()) {
            chef.update();
        }
        for (Waiter waiter : restaurant.getWaiters()) {
            waiter.update();
        }
        for (Customer customer : restaurant.getCustomers()) {
            customer.update();
        }
        
        trySpawnCustomer();
    }
    
    private void trySpawnCustomer() {
        if (random.nextInt(100) < 25 && 
            restaurant.getCustomers().size() < restaurant.getSeats()) {
            Customer customer = CustomerFactory.getInstance().createCustomer();
            customer.setMediator(this);
            restaurant.addCustomer(customer);
            processNewCustomer(customer);
        }
    }

    private void processNewCustomer(Customer customer) {
        System.out.println("Processing new customer: " + customer.getInitial());
        customer.startOrdering();
        assignCustomerToWaiter(customer);
    }

    public void assignCustomerToWaiter(Customer customer) {
        if (customersBeingServed.contains(customer)) {
            System.out.println("Customer " + customer.getInitial() + " is already being served");
            return;
        }

        System.out.println("Looking for available waiter for " + customer.getInitial());
        for (Waiter waiter : restaurant.getWaiters()) {
            if (waiter.getState() instanceof WaiterIdle) {
                System.out.println("Found idle waiter: " + waiter.getInitial());
                customersBeingServed.add(customer);
                waiter.handleOrder(customer);
                return;
            }
        }
        System.out.println("No idle waiters found");
    }

    public void customerFinished(Customer customer) {
        System.out.println("Customer " + customer.getInitial() + " finished and is leaving");
        Chef assignedChef = customerChefAssignments.remove(customer);
        if (assignedChef != null) {
            assignedChef.finishOrder();
        }
        customersBeingServed.remove(customer);
        restaurant.removeCustomer(customer);
    }

    public void assignOrderToChef(Waiter waiter, Customer customer) {
        if (customer == null || customerChefAssignments.containsKey(customer)) {
            System.out.println("Customer already has a chef assigned");
            return;
        }
        
        System.out.println("Looking for available chef for customer " + customer.getInitial());
        for (Chef chef : restaurant.getChefs()) {
            if (chef.getState() instanceof ChefIdle && chef.getCurrentCustomer() == null) {
                System.out.println("Found idle chef: " + chef.getInitial());
                customerChefAssignments.put(customer, chef);
                waiter.setAssignedChef(chef);  
                chef.handleOrder(customer, waiter);
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
    
    public boolean isPaused() {
        return isPaused;
    }
}
