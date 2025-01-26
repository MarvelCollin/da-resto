package models.States;

import interfaces.IState;
import models.Entity.Entity;

public abstract class BaseState implements IState {
    protected Entity entity;
    protected String customerName;
    
    public BaseState(Entity entity, String customerName) {
        this.entity = entity;
        this.customerName = customerName;
    }
    
    public BaseState(Entity entity) {
        this(entity, null);
    }

    @Override
    public void changeState(String customerName) {
    }
}
