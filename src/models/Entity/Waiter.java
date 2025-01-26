package models.Entity;

import models.States.WaiterState.WaiterIdle;
import models.States.WaiterState.WaiterTakeOrder;

public class Waiter extends Entity {
    private int speed;
    private Customer currentCustomer;
    private Chef assignedChef;
    
    public Waiter(String initial) {
        super(initial);
        this.speed = 1;
        setState(new WaiterIdle(this));
    }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }

    public void handleOrder(Customer customer) {
        if (state instanceof WaiterIdle) {
            this.currentCustomer = customer;
            setState(new WaiterTakeOrder(this, customer.getInitial()));
        }
    }

    public void setAssignedChef(Chef chef) {
        this.assignedChef = chef;
    }

    public Chef getAssignedChef() {
        return assignedChef;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer customer) {
        this.currentCustomer = customer;
    }

    @Override
    public String toString() {
        return String.format("%s", state.getStateName());
    }
}
