package models.States.ChefState;

import models.Entity.Chef;
import models.States.BaseState;

public class ChefIdle extends BaseState {
    public ChefIdle(Chef chef) {
        super(chef);
    }

    @Override
    public void update() {
        // Wait for waiter to bring order
    }

    @Override
    public String getStateName() {
        return String.format("%s - idle", entity.getInitial());
    }

    @Override
    public void changeState(String customerName) {
        entity.setState(new ChefCook((Chef)entity, customerName));
    }
}
