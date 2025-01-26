package models.States.ChefState;

import models.Entity.Chef;
import models.States.BaseState;

public class ChefDone extends BaseState {
    public ChefDone(Chef chef, String customerName) {
        super(chef, customerName);
    }

    @Override
    public void update() {
        // Wait for waiter to pick up food
    }

    @Override
    public String getStateName() {
        return String.format("%s - done (%s)", entity.getInitial(), customerName);
    }

    @Override
    public void changeState(String customerName) {
        entity.setState(new ChefIdle((Chef)entity));
    }
}
