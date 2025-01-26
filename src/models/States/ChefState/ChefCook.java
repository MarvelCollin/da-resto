package models.States.ChefState;

import models.Entity.Chef;
import models.States.BaseState;

public class ChefCook extends BaseState {
    private int cookingTime;

    public ChefCook(Chef chef, String customerName) {
        super(chef, customerName);
        this.cookingTime = 6 - ((Chef)entity).getSpeed();
    }

    @Override
    public void update() {
        if (--cookingTime <= 0) {
            entity.setState(new ChefDone((Chef)entity, customerName));
        }
    }

    @Override
    public String getStateName() {
        return String.format("%s - cook (%s)", entity.getInitial(), customerName);
    }
}
