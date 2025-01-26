package models.States.CustomerState;

import models.Entity.Customer;
import models.States.BaseState;
import utils.Debugger;
import models.Restaurant;

public class CustomerEat extends BaseState {
    private int eatTime = 6;  
    private int chefSkill;
    private Restaurant restaurant;

    public CustomerEat(Customer customer, int chefSkill) {
        super(customer);
        this.chefSkill = chefSkill;
        this.restaurant = Restaurant.getActiveRestaurant();
    }

    @Override
    public void update() {
        if (--eatTime <= 0) {
            
            int reward = chefSkill * 30;
            
            
            restaurant.setScore(restaurant.getScore() + reward);
            restaurant.setMoney(restaurant.getMoney() + reward);
            
            Debugger.customerDebug("Customer " + entity.getInitial() + " finished eating!");
            Debugger.customerDebug("Earned money and score: " + reward + " (Chef skill: " + chefSkill + ")");
            Debugger.customerDebug("Restaurant total - Money: " + restaurant.getMoney() + ", Score: " + restaurant.getScore());
            
            ((Customer)entity).getMediator().customerFinished((Customer)entity);
        }
    }

    @Override
    public String getStateName() {
        return String.format("eat(%ds)", eatTime);
    }
}
