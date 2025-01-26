package models.States.ChefState;

import models.Entity.Chef;
import models.States.BaseState;

public class ChefCook extends BaseState {
    private int cookingTime;

    public ChefCook(Chef chef, String customerName) {
        super(chef, customerName);
        this.cookingTime = 6 - ((Chef)entity).getSpeed();
        System.out.println("Chef " + chef.getInitial() + " starting to cook for " + cookingTime + " seconds");
    }

    @Override
    public void update() {
        if (--cookingTime <= 0) {
            System.out.println("Chef " + entity.getInitial() + " finished cooking");
            entity.setState(new ChefDone((Chef)entity, customerName));
        }
    }

    @Override
    public String getStateName() {
        return String.format("cook(%s)", customerName);
    }
}
