package interfaces;

import controllers.Mediators.RestaurantMediator;

public interface IRestaurant {
    void setMediator(RestaurantMediator mediator);
    void pause();
    void resume();
}


