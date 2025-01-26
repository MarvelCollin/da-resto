package models.States.WaiterState;

import models.Entity.Waiter;
import models.States.BaseState;

public class WaiterTakeOrder extends BaseState {
    private int orderTime;

    public WaiterTakeOrder(Waiter waiter, String customerName) {
        super(waiter, customerName);
        this.orderTime = 6 - ((Waiter)entity).getSpeed();
    }

    @Override
    public void update() {
        if (--orderTime <= 0) {
            entity.setState(new WaiterWaitCook((Waiter)entity));
        }
    }

    @Override
    public String getStateName() {
        return String.format("%s - take order (%s)", entity.getInitial(), customerName);
    }
}
