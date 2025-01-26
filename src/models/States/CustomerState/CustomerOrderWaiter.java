package models.States.CustomerState;

import models.Entity.Customer;
import models.States.BaseState;

public class CustomerOrderWaiter extends BaseState {
    public CustomerOrderWaiter(Customer customer, String waiterName) {
        super(customer, waiterName);
    }

    @Override
    public void update() {
        // No tolerance reduction while being served
    }

    @Override
    public String getStateName() {
        return String.format("%s - order (%s)", entity.getInitial(), customerName);
    }
    
    @Override
    public void changeState(String waiterName) {
        entity.setState(new CustomerWaitFood((Customer)entity));
    }
}
