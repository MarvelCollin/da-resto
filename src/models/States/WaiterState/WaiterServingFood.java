package models.States.WaiterState;

import models.Entity.Waiter;
import models.Entity.Customer;
import models.States.BaseState;
import models.States.CustomerState.CustomerEat;

public class WaiterServingFood extends BaseState {
    private int servingTime = 1;

    public WaiterServingFood(Waiter waiter, String customerName) {
        super(waiter, customerName);
        System.out.println("Waiter " + waiter.getInitial() + " serving food to " + customerName);
    }

    @Override
    public void update() {
        if (--servingTime <= 0) {
            Waiter waiter = (Waiter)entity;
            Customer customer = waiter.getCurrentCustomer();
            if (customer != null) {
                // Start customer eating
                customer.setState(new CustomerEat(customer, waiter.getAssignedChef().getSkill()));
                // Clear waiter's assignments
                waiter.setCurrentCustomer(null);
                waiter.setAssignedChef(null);
            }
            entity.setState(new WaiterIdle(waiter));
        }
    }

    @Override
    public String getStateName() {
        return String.format("serving(%s)", customerName);
    }
}
