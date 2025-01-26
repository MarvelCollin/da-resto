package models.Entity;

import models.States.WaiterState.WaiterIdle;

public class Waiter extends Entity {
    private int speed;
    
    public Waiter(String initial) {
        super(initial);
        this.speed = 1;
        setState(new WaiterIdle(this));
    }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }

    public void handleOrder(Customer customer) {
        state.changeState(customer.getInitial());
    }

    @Override
    public String toString() {
        return String.format("%s, %s", getInitial(), state.getStateName());
    }
}
