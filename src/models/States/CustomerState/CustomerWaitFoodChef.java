package models.States.CustomerState;

import models.Entity.Customer;
import models.States.BaseState;

public class CustomerWaitFoodChef extends BaseState {
    private int toleranceTimer = 0;

    public CustomerWaitFoodChef(Customer customer, String chefName) {
        super(customer, chefName);
    }

    @Override
    public void update() {
        if (++toleranceTimer >= 4) {
            ((Customer)entity).reduceTolerance();
            toleranceTimer = 0;
        }
    }

    @Override
    public String getStateName() {
        return String.format("%s - wait food (%s)", entity.getInitial(), customerName);
    }
}
