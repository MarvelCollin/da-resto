package models.Entity;

import models.States.ChefState.*;
import interfaces.IState;
import interfaces.IRestaurant;
import controllers.Mediators.RestaurantMediator;

public class Chef extends Entity implements IRestaurant {
    private int speed;
    private int skill;
    private IState state;
    private RestaurantMediator mediator;
    private boolean idle = true;
    
    public Chef(String initial) {
        super(initial);
        this.speed = 1; 
        this.skill = 1; 
        this.state = new ChefIdle(this);
    }

    public void update() {
        state.update();
    }

    public void setState(IState state) {
        this.state = state;
    }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }
    
    public int getSkill() { return skill; }
    public void setSkill(int skill) { this.skill = skill; }

    @Override
    public String toString() {
        return String.format("%s, %s", getInitial(), state.getStateName());
    }

    public void startCooking(String customerName) {
        state.changeState(customerName);
    }

    @Override
    public void setMediator(RestaurantMediator mediator) {
        this.mediator = mediator;
    }
    
    public boolean isIdle() {
        return idle;
    }
    
    public void cookOrder(Customer customer) {
        idle = false;
        // Cooking logic
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
