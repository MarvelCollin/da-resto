package models.States.WaiterState;

import models.Entity.Waiter;
import models.Entity.Chef;
import models.States.BaseState;
import utils.Debugger;

public class WaiterBringOrder extends BaseState {
    private int bringTime = 1;

    public WaiterBringOrder(Waiter waiter, String customerName) {
        super(waiter, customerName);
        
        Chef chef = waiter.getAssignedChef();
        if (chef != null) {
            Debugger.waiterDebug("Waiter " + waiter.getInitial() + " picking up food from chef " + chef.getInitial());
            chef.getState().changeState(null); 
        }
    }

    @Override
    public void update() {
        if (--bringTime <= 0) {
            Debugger.waiterDebug("Waiter " + entity.getInitial() + " delivering food to " + customerName);
            entity.setState(new WaiterServingFood((Waiter)entity, customerName));
        }
    }

    @Override
    public String getStateName() {
        return String.format("bring_order(%s)", customerName);
    }
}
