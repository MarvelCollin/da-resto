package models.States;

import interfaces.IState;
import models.Entity.Chef;

public abstract class BaseState implements IState {
    protected Chef chef;
    protected String customerName;
    
    public BaseState(Chef chef, String customerName) {
        this.chef = chef;
        this.customerName = customerName;
    }
    
    public BaseState(Chef chef) {
        this(chef, null);
    }

    @Override
    public void changeState(String customerName) {
    }
}
