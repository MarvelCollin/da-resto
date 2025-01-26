package models.States.WaiterState;

import models.Entity.Waiter;
import models.Entity.Chef;
import models.States.BaseState;
import models.States.ChefState.ChefDone;

public class WaiterWaitCook extends BaseState {
    private boolean orderAssigned = false;

    public WaiterWaitCook(Waiter waiter, String customerName) {
        super(waiter, customerName);
        System.out.println("Waiter " + waiter.getInitial() + " looking for chef");
        tryAssignToChef(waiter);
    }

    private void tryAssignToChef(Waiter waiter) {
        if (!orderAssigned && waiter.getCurrentCustomer() != null) {
            waiter.getMediator().assignOrderToChef(waiter, waiter.getCurrentCustomer());
        }
    }

    @Override
    public void update() {
        Waiter waiter = (Waiter)entity;
        Chef assignedChef = waiter.getAssignedChef();
        
        if (assignedChef != null && assignedChef.getState() instanceof ChefDone) {
            System.out.println("Waiter " + waiter.getInitial() + " found chef " + assignedChef.getInitial() + " is done cooking");
            entity.setState(new WaiterBringOrder(waiter, waiter.getCurrentCustomer().getInitial()));
        }
    }

    @Override
    public String getStateName() {
        return String.format("wait_cook(%s)", customerName);
    }
}
