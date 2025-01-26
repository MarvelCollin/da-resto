package models.Entity;

import models.States.CustomerState.CustomerOrder;

public class Customer extends Entity {
    private int tolerance;
    
    public Customer(String initial) {
        super(initial);
        this.tolerance = 10;
    }
    
    public void startOrdering() {
        setState(new CustomerOrder(this));
    }
    
    public int getTolerance() { return tolerance; }
    
    public void reduceTolerance() {
        tolerance--;
        if(tolerance <= 0) {
            leaveRestaurant();
        }
    }
    
    private void leaveRestaurant() {
    }
    
    @Override
    public String toString() {
        return String.format("(%d)%s", tolerance, state.getStateName());
    }
}
