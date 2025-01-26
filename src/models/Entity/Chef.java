package models.Entity;

import models.States.ChefState.ChefIdle;

public class Chef extends Entity {
    private int speed;
    private int skill;
    
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

    @Override
    public String toString() {
        return String.format("%s, %s", getInitial(), state.getStateName());
    }
}
