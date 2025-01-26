package models.States.RestaurantState;

import models.Restaurant;
import utils.Debugger;

public class RestaurantPaused extends RestaurantState {
    @Override
    public void onEnter(Restaurant restaurant) {
        Debugger.restaurantDebug("Restaurant entered Paused state");
        restaurant.getMediator().pauseAllOperations();
    }

    @Override
    public void update(Restaurant restaurant) {
        
    }

    @Override
    public String getStateName() {
        return "Paused";
    }
}
