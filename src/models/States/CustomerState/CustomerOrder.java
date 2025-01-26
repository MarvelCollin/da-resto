package models.States.CustomerState;

import models.Entity.Customer;
import models.States.BaseState;
import controllers.Mediators.RestaurantMediator;

public class CustomerOrder extends BaseState {
    private int toleranceTimer = 0;
    
    public CustomerOrder(Customer customer) {
        super(customer);
        if (customer.getMediator() != null) {
            System.out.println("CustomerOrder: Requesting waiter for " + customer.getInitial()); // Debug
            customer.getMediator().assignCustomerToWaiter(customer);
        } else {
            System.out.println("CustomerOrder: No mediator for " + customer.getInitial()); // Debug
        }
    }

    @Override
    public void update() {
        if (++toleranceTimer >= 2) {  
            ((Customer)entity).reduceTolerance();
            toleranceTimer = 0;
            
            // Retry getting a waiter periodically
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
