package models.States.CustomerState;

import models.Entity.Customer;
import models.States.BaseState;
import controllers.Mediators.RestaurantMediator;
import utils.Debugger;

public class CustomerOrder extends BaseState {
    private int toleranceTimer = 0;
    
    public CustomerOrder(Customer customer) {
        super(customer);
        if (customer.getMediator() != null) {
            Debugger.customerDebug("CustomerOrder: Requesting waiter for " + customer.getInitial());
            customer.getMediator().assignCustomerToWaiter(customer);
        } else {
            Debugger.customerDebug("CustomerOrder: No mediator for " + customer.getInitial());
        }
    }

    @Override
    public void update() {
        if (++toleranceTimer >= 2) {  
            ((Customer)entity).reduceTolerance();
            toleranceTimer = 0;
            
            
            if (entity.getMediator() != null) {
                entity.getMediator().assignCustomerToWaiter((Customer)entity);
            }
        }
    }

    @Override
    public String getStateName() {
        return "order";
    }
    
    @Override
    public void changeState(String waiterName) {
        entity.setState(new CustomerOrderWaiter((Customer)entity, waiterName));
    }
}
