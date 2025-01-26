package models.States.RestaurantState;

import models.Restaurant;

public class RestaurantPaused extends RestaurantState {
    @Override
    public void update(Restaurant restaurant) {
    }

    @Override
    public String getStateName() {
        return "Paused";
    }
}
