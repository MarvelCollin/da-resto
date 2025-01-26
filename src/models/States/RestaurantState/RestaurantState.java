package models.States.RestaurantState;

import interfaces.IRestaurantState;
import models.Restaurant;

public abstract class RestaurantState implements IRestaurantState {
    @Override
    public void onEnter(Restaurant restaurant) {}
    
    @Override
    public void onExit(Restaurant restaurant) {}
}
