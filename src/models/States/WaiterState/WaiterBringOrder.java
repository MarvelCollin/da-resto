package models.States.WaiterState;

import models.Entity.Waiter;
import models.Entity.Chef;
import models.States.BaseState;

public class WaiterBringOrder extends BaseState {
    private int bringTime = 1;

    public WaiterBringOrder(Waiter waiter, String customerName) {
        super(waiter, customerName);
        // Tell chef we picked up the food
        Chef chef = waiter.getAssignedChef();
        if (chef != null) {
            System.out.println("Waiter " + waiter.getInitial() + " picking up food from chef " + chef.getInitial());
            chef.getState().changeState(null); // This will make chef go idle
        }
    }

    @Override
    public void update() {
        if (--bringTime <= 0) {
            System.out.println("Waiter " + entity.getInitial() + " delivering food to " + customerName);
            entity.setState(new WaiterServingFood((Waiter)entity, customerName));
        }
    }

    @Override
    public String getStateName() {
        return String.format("bring_order(%s)", customerName);
    }
}
