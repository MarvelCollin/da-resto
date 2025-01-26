package interfaces;

import models.Restaurant;

public interface IRestaurantState {
    void update(Restaurant restaurant);
    void onEnter(Restaurant restaurant);
    void onExit(Restaurant restaurant);
    String getStateName();
}
