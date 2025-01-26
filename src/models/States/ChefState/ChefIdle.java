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
        return "idle";
    }

    @Override
    public void changeState(String customerName) {
        Chef chef = (Chef)entity;
        if (chef.getCurrentCustomer() == null) {  // Only take order if not already cooking
            System.out.println("Chef " + chef.getInitial() + " starting to cook for " + customerName);
            chef.setState(new ChefCook((Chef)entity, customerName));
        } else {
            System.out.println("Chef " + chef.getInitial() + " is already cooking for " + chef.getCurrentCustomer().getInitial());
        }
    }
}
