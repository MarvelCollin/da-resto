package models.States.WaiterState;

import models.Entity.Waiter;
import models.States.BaseState;

public class WaiterServingFood extends BaseState {
    private int servingTime = 1;

    public WaiterServingFood(Waiter waiter, String customerName) {
        super(waiter, customerName);
    }

    @Override
    public void update() {
        if (--servingTime <= 0) {
            entity.setState(new WaiterIdle((Waiter)entity));
        }
    }

    @Override
    public String getStateName() {
        return String.format("%s - serving food (%s)", entity.getInitial(), customerName);
    }
}
