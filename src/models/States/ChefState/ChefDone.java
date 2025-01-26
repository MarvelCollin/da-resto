package models.States.ChefState;

import models.Entity.Chef;
import models.States.BaseState;

public class ChefDone extends BaseState {
    public ChefDone(Chef chef, String customerName) {
        super(chef, customerName);
        System.out.println("Chef " + chef.getInitial() + " finished cooking for " + customerName);
    }

    @Override
    public void update() {
        // Wait for waiter to pick up food
    }

    @Override
    public String getStateName() {
        return String.format("done(%s)", customerName);
    }

    // This will be called by waiter after picking up food
    @Override
    public void changeState(String customerName) {
        ((Chef)entity).finishOrder();
        entity.setState(new ChefIdle((Chef)entity));
    }
}
