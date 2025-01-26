package models.States.WaiterState;

import models.Entity.Waiter;
import models.States.BaseState;

public class WaiterWaitCook extends BaseState {
    public WaiterWaitCook(Waiter waiter) {
        super(waiter);
    }

    @Override
    public void update() {
        // Wait for idle or done chef
    }

    @Override
    public String getStateName() {
        return String.format("%s - wait cook", entity.getInitial());
    }

    @Override
    public void changeState(String chefName) {
        entity.setState(new WaiterBringOrder((Waiter)entity, chefName));
    }
}
