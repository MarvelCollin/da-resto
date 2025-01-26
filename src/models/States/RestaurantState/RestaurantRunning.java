package models.States.RestaurantState;

import models.Restaurant;

public class RestaurantRunning extends RestaurantState {
    @Override
    public void update(Restaurant restaurant) {
        // Update restaurant logic while running
    }

    @Override
    public String getStateName() {
        return "Running";
    }
}
