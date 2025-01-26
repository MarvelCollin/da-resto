package controllers.Facades;

import controllers.Mediators.RestaurantMediator;
import controllers.Observers.CustomerGenerator;
import models.Restaurant;
import models.Entity.*;
import models.Factory.ChefFactory;
import models.Factory.WaiterFactory;
import utils.Constants;

public class GameFacade {
    private static volatile GameFacade instance;
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
        restaurant = Restaurant.createRestaurant(name);
        mediator.setRestaurant(restaurant);
        initializeGame();
    }
    
    public void pauseGame() {
        mediator.pauseAllOperations();
    }
    
    public void resumeGame() {
        mediator.resumeAllOperations();
    }
    
    public boolean isPaused() {
        return mediator.isPaused();
    }
    
    public void updateGame() {
        if (restaurant != null) {
            restaurant.update();
            mediator.updateEntities();
        }
    }
    
    public Restaurant getRestaurant() {
        return restaurant;
    }
    
    private void initializeGame() {
        
        for (int i = 0; i < Constants.DEFAULT_WAITERS; i++) {
            hireWaiter();
            hireChef();
        }
    }
    
    public void hireWaiter() {
        if (restaurant != null) {
            Waiter waiter = WaiterFactory.getInstance().createWaiter();
            waiter.setMediator(mediator);
            restaurant.addWaiter(waiter);
        }
    }
    
    public void hireChef() {
        if (restaurant != null) {
            Chef chef = ChefFactory.getInstance().createChef();
            chef.setMediator(mediator);
            restaurant.addChef(chef);
        }
    }
    
    public void shutdown() {
        if (mediator != null) {
            mediator.shutdown();
        }
        CustomerGenerator.getInstance().stop();
    }
}
