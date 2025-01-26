package controllers.Facades;

import controllers.Mediators.RestaurantMediator;
import models.Restaurant;
import models.Factory.RestaurantFactory;
import utils.Constants;

public class GameFacade {
    private static GameFacade instance;
    private Restaurant restaurant;
    private RestaurantMediator mediator;
    
    private GameFacade() {
        mediator = new RestaurantMediator();
    }
    
    public static GameFacade getInstance() {
        if (instance == null) {
            instance = new GameFacade();
        }
        return instance;
    }
    
    public void startNewGame(String name) {
        restaurant = RestaurantFactory.getInstance().createRestaurant(name);
        mediator.setRestaurant(restaurant);
    }
    
    public void pauseGame() {
        mediator.pauseAllOperations();
    }
    
    public void resumeGame() {
        mediator.resumeAllOperations();
    }
    
    public Restaurant getRestaurant() {
        return restaurant;
    }
    
}
