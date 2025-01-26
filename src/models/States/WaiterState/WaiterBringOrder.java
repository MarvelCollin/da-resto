package models.States.WaiterState;

import models.Entity.Waiter;
import models.States.BaseState;

public class WaiterBringOrder extends BaseState {
    private int bringTime = 1;
    private boolean toIdleChef;

    public WaiterBringOrder(Waiter waiter, String chefName) {
        super(waiter, chefName);
    }

    @Override
    public void update() {
        if (--bringTime <= 0) {
            if (toIdleChef) {
                entity.setState(new WaiterIdle((Waiter)entity));
            } else {
                entity.setState(new WaiterServingFood((Waiter)entity, customerName));
            }
        }
    }

    @Override
    public String getStateName() {
        return String.format("%s - bring order (%s)", entity.getInitial(), customerName);
    }

    public void setToIdleChef(boolean toIdleChef) {
        this.toIdleChef = toIdleChef;
    }
}
