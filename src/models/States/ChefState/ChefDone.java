package models.States.ChefState;

import models.Entity.Chef;
import models.States.BaseState;
import utils.Debugger;

public class ChefDone extends BaseState {
    public ChefDone(Chef chef, String customerName) {
        super(chef, customerName);
        Debugger.chefDebug("Chef " + chef.getInitial() + " finished cooking for " + customerName);
    }

    @Override
    public void update() {
        
    }

    @Override
    public String getStateName() {
        return String.format("done(%s)", customerName);
    }

    
    @Override
    public void changeState(String customerName) {
        ((Chef)entity).finishOrder();
        entity.setState(new ChefIdle((Chef)entity));
    }
}
