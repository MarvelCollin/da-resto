package models.States.WaiterState;

import models.Entity.Waiter;
import models.States.BaseState;
import utils.Debugger;

public class WaiterTakeOrder extends BaseState {
    private int orderTime;

    public WaiterTakeOrder(Waiter waiter, String customerName) {
        super(waiter, customerName);
        this.orderTime = 6 - ((Waiter)entity).getSpeed();
        Debugger.waiterDebug("Waiter " + waiter.getInitial() + " will take " + orderTime + " seconds to take order");
    }

    @Override
    public void update() {
        if (--orderTime <= 0) {
            
            entity.setState(new WaiterWaitCook((Waiter)entity, customerName));
        }
    }

    @Override
    public String getStateName() {
        return String.format("take order(%s)", customerName);
    }
}