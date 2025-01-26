package models.Entity;

import interfaces.IState;
import interfaces.IRestaurant;
import controllers.Mediators.RestaurantMediator;
import models.States.WaiterState.WaiterIdle;

public class Waiter extends Entity implements IRestaurant {
    private int speed;
    private IState state;
    private RestaurantMediator mediator;
    private boolean idle = true;
    private Chef waitingForChef;
    
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

    @Override
    public void setMediator(RestaurantMediator mediator) {
        this.mediator = mediator;
    }

    public boolean isIdle() {
        return idle;
    }

    public void setWaitingForChef(Chef chef) {
        this.waitingForChef = chef;
    }

    public boolean isWaitingForChef(Chef chef) {
        return this.waitingForChef == chef;
    }

    public void takeOrder(Customer customer) {
        idle = false;
        startTakeOrder(customer.getInitial());
    }

    public void serveOrder(Customer customer) {
        // Serving logic
    }

    @Override
    public void pause() {
        // Pause operations
    }

    @Override
    public void resume() {
        // Resume operations
    }
}
