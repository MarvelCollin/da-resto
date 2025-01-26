package models.States.CustomerState;

import models.Entity.Customer;
import models.States.BaseState;

public class CustomerEat extends BaseState {
    private int eatTime = 6;
    private int chefSkill;

    public CustomerEat(Customer customer, int chefSkill) {
        super(customer);
        this.chefSkill = chefSkill;
    }

    @Override
    public void update() {
        if (--eatTime <= 0) {
            // TODO: Implement score and money increase: chefSkill * 30
            // TODO: Remove customer from restaurant
        }
    }

    @Override
    public String getStateName() {
        return String.format("%s - eat", entity.getInitial());
    }
}
