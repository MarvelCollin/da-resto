package models.Entity;

import models.States.ChefState.ChefIdle;
import models.States.ChefState.ChefCook;

public class Chef extends Entity {
    private int speed;
    private int skill;
    private Customer currentCustomer;
    
    public Chef(String initial) {
        super(initial);
        this.speed = 1;
        this.skill = 1;
        setState(new ChefIdle(this));
    }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }
    
    public int getSkill() { return skill; }
    public void setSkill(int skill) { this.skill = skill; }
    
    public void handleOrder(Customer customer) {
        state.changeState(customer.getInitial());
    }

    public void handleOrder(Customer customer, Waiter waiter) {
        if (currentCustomer != null) {
            System.out.println("Chef " + getInitial() + " already has customer " + currentCustomer.getInitial());
            return;
        }
        System.out.println("Chef " + getInitial() + " received order from waiter " + waiter.getInitial());
        currentCustomer = customer;
        setState(new ChefCook(this, customer.getInitial()));
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void finishOrder() {
        currentCustomer = null;
    }

    @Override
    public String toString() {
        return String.format("%s",  state.getStateName());
    }
}
