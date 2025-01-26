package models.Factory;

import models.Entity.Customer;
import utils.InitialsManager;

public class CustomerFactory {
    private static CustomerFactory instance;
    
    private CustomerFactory() {}
    
    public static CustomerFactory getInstance() {
        if(instance == null) {
            instance = new CustomerFactory();
        }
        return instance;
    }
    
    public Customer createCustomer() {
        String initial = InitialsManager.getInstance().getUniqueInitial();
        return new Customer(initial);
    }
}
