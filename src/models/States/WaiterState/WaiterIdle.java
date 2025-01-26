package models.States.WaiterState;

import models.Entity.Waiter;
import models.States.BaseState;

public class WaiterIdle extends BaseState {
    public WaiterIdle(Waiter waiter) {
        super(waiter);
    }

    @Override
    public void update() {
        // Do nothing in idle state
    }

    @Override
    public String getStateName() {
        return String.format("%s - idle", entity.getInitial());
    }

    @Override
    public void changeState(String customerName) {
        entity.setState(new WaiterTakeOrder((Waiter)entity, customerName));
    }
}
