package controllers.Facades;

import controllers.Mediators.RestaurantMediator;
import models.Restaurant;
import models.Entity.Chef;
import models.Entity.Waiter;
import models.Factory.ChefFactory;
import models.Factory.RestaurantFactory;
import models.Factory.WaiterFactory;

public class GameFacade {
    private static volatile GameFacade instance;
    private Restaurant restaurant;
    private RestaurantMediator mediator;
    
    private GameFacade() {
        mediator = new RestaurantMediator();
    }
    
    public static GameFacade getInstance() {
        if (instance == null) {
            synchronized (GameFacade.class) {
                if (instance == null) {
                    instance = new GameFacade();
                }
            }
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
    
    public void hireWaiter() {
        Waiter waiter = WaiterFactory.getInstance().createWaiter();
        restaurant.addWaiter(waiter);
    }
    
    public void hireChef() {
        Chef chef = ChefFactory.getInstance().createChef();
        restaurant.addChef(chef);
    }
    
}
