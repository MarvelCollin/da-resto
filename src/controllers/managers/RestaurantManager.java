package controllers.Managers;

import models.Restaurant;
import models.Entity.*;

public class RestaurantManager {
    private static RestaurantManager instance;
    private Restaurant activeRestaurant;
    private boolean isPaused;
    
    private RestaurantManager() {
        isPaused = false;
    }
    
    public static RestaurantManager getInstance() {
        if(instance == null) {
            instance = new RestaurantManager();
        }
        return instance;
    }
    
    
    public void setActiveRestaurant(Restaurant restaurant) {
        this.activeRestaurant = restaurant;
    }
    
    public Restaurant getActiveRestaurant() {
        return activeRestaurant;
    }
    
    
    public void pauseGame() {
        isPaused = true;
    }
    
    public void resumeGame() {
        isPaused = false;
    }
    
    public boolean isPaused() {
        return isPaused;
    }
    
    
    public void addScore(int points) {
        if(activeRestaurant != null) {
            
        }
    }
    
    public void reduceScore(int points) {
        if(activeRestaurant != null) {
            
        }
    }
}
