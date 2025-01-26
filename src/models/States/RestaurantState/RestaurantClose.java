package models.States.RestaurantState;

import models.Restaurant;
import utils.Debugger;

public class RestaurantClose extends RestaurantState {
    @Override
    public void onEnter(Restaurant restaurant) {
        Debugger.restaurantDebug("Restaurant entered Close state");
        restaurant.getMediator().shutdown();
    }

    @Override
    public void update(Restaurant restaurant) {
        
    }

    @Override
    public String getStateName() {
        return "Closed";
    }
}
