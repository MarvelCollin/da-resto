package models.Entity;

import interfaces.IState;
import models.States.WaiterState.WaiterIdle;

public class Waiter extends Entity {
    private int speed;
    
    public Waiter(String initial) {
        super(initial);
        this.speed = 1;
        this.state = new WaiterIdle(this);
    }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }

    @Override
    public String toString() {
        return String.format("%s, %s", getInitial(), state.getStateName());
    }

    public void startTakeOrder(String customerName) {
        state.changeState(customerName);
    }
}
