package models.States.ChefState;

import models.Entity.Chef;
import models.States.BaseState;

public class ChefDone extends BaseState {
    public ChefDone(Chef chef, String customerName) {
        super(chef, customerName);
    }

    @Override
    public void update() {
    }

    @Override
    public String getStateName() {
        return "done (" + customerName + ")";
    }

    @Override
    public void changeState(String customerName) {
        chef.setState(new ChefIdle(chef));
    }
}
