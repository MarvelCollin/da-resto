package models.Factory;

import models.Restaurant;
import utils.Constants;

public class RestaurantFactory {
    private static RestaurantFactory instance;
    
    private RestaurantFactory() {}
    
    public static RestaurantFactory getInstance() {
        if(instance == null) {
            instance = new RestaurantFactory();
        }
        return instance;
    }
    
    public Restaurant createRestaurant(String name) {
        // Only create restaurant, don't add entities here
        return new Restaurant(name);
    }
}
