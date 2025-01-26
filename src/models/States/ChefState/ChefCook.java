package models.States.ChefState;

import models.Entity.Chef;
import models.States.BaseState;

public class ChefCook extends BaseState {
    private int cookingTime;

    public ChefCook(Chef chef, String customerName) {
        super(chef, customerName);
        this.cookingTime = 6 - chef.getSpeed();
    }

    @Override
    public void update() {
        if (--cookingTime <= 0) {
            chef.setState(new ChefDone(chef, customerName));
        }
    }

    @Override
    public String getStateName() {
        return "cook (" + customerName + ")";
    }
}
