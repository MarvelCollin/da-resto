package models.States.ChefState;

import models.Entity.Chef;
import models.States.BaseState;

public class ChefIdle extends BaseState {
    public ChefIdle(Chef chef) {
        super(chef);
    }

    @Override
    public void update() {
        // ni tunggu waiternya
    }

    @Override
    public String getStateName() {
        return "idle";
    }

    @Override
    public void changeState(String customerName) {
        chef.setState(new ChefCook(chef, customerName));
    }
}
