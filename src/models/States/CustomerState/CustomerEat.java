package models.States.CustomerState;

import models.Entity.Customer;
import models.States.BaseState;
import models.Restaurant;

public class CustomerEat extends BaseState {
    private int eatTime = 6;  // Fixed 6 seconds
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
            // Calculate reward based on chef's skill
            int reward = chefSkill * 30;
            
            // Add to restaurant score and money
            restaurant.setScore(restaurant.getScore() + reward);
            restaurant.setMoney(restaurant.getMoney() + reward);
            
            System.out.println("Customer " + entity.getInitial() + " finished eating!");
            System.out.println("Earned money and score: " + reward + " (Chef skill: " + chefSkill + ")");
            System.out.println("Restaurant total - Money: " + restaurant.getMoney() + ", Score: " + restaurant.getScore());
            
            ((Customer)entity).getMediator().customerFinished((Customer)entity);
        }
    }

    @Override
    public String getStateName() {
        return String.format("eat(%ds)", eatTime);
    }
}
