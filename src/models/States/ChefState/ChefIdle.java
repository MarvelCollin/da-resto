package models.States.ChefState;

import models.Entity.Chef;
import models.States.BaseState;
import utils.Debugger;

public class ChefIdle extends BaseState {
    public ChefIdle(Chef chef) {
        super(chef);
    }

    @Override
    public void update() {
        
    }

    @Override
    public String getStateName() {
        return "idle";
    }

    @Override
    public void changeState(String customerName) {
        Chef chef = (Chef)entity;
        if (chef.getCurrentCustomer() == null) {  
            Debugger.chefDebug("Chef " + chef.getInitial() + " starting to cook for " + customerName);
            chef.setState(new ChefCook((Chef)entity, customerName));
        } else {
            Debugger.chefDebug("Chef " + chef.getInitial() + " is already cooking for " + chef.getCurrentCustomer().getInitial());
        }
    }
}
