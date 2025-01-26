package models.States.CustomerState;

import models.Entity.Customer;
import models.States.BaseState;

public class CustomerOrder extends BaseState {
    private int toleranceTimer = 0;
    
    public CustomerOrder(Customer customer) {
        super(customer);
    }

    @Override
    public void update() {
        if (++toleranceTimer >= 2) {  // Every 2 seconds
            ((Customer)entity).reduceTolerance();
            toleranceTimer = 0;
        }
    }

    @Override
    public String getStateName() {
        return String.format("%s - order", entity.getInitial());
    }
    
    @Override
    public void changeState(String waiterName) {
        entity.setState(new CustomerOrderWaiter((Customer)entity, waiterName));
    }
}
