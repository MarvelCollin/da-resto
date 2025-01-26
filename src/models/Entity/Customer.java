package models.Entity;

import models.States.CustomerState.CustomerOrder;
import interfaces.IState;

public class Customer extends Entity {
    private int tolerance;
    
    public Customer(String initial) {
        super(initial);
        this.tolerance = 10; // Default tolerance
        this.state = new CustomerOrder(this);
    }
    
    public int getTolerance() { return tolerance; }
    
    public void reduceTolerance() {
        tolerance--;
        if(tolerance <= 0) {
            // TODO: Implement restaurant score reduction
            leaveRestaurant();
        }
    }
    
    private void leaveRestaurant() {
        // TODO: Implement customer leaving logic
    }
    
    @Override
    public String toString() {
        return String.format("%s(%d), %s", getInitial(), tolerance, state.getStateName());
    }
}
