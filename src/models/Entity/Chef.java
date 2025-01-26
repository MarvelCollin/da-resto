package models.Entity;

import models.States.ChefState.*;
import interfaces.IState;

public class Chef extends Entity {
    private int speed;
    private int skill;
    private IState state;
    
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
}
