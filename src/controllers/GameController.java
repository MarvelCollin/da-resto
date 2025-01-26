package controllers;

import models.Restaurant;
import utils.Validator;

public class GameController {
    private Restaurant restaurant;
    
    public GameController() {
        start();
    }

    public void start() {
        String name = Validator.getValidStringInput(
            this::showNameInput,
            3, 15
        );
        
        restaurant = new Restaurant(name);
        startPauseMenu();
    }

    public void showNameInput() {
        System.out.print("Enter Restaurant Name [3 - 15 Characters]: ");
    }
    
    private void startPauseMenu() {
        System.out.println("\nRestaurant " + restaurant.getName() + " created!");
        System.out.println("Seats: " + restaurant.getSeats());
        System.out.println("Waiters: " + restaurant.getWaiters());
        System.out.println("Chefs: " + restaurant.getChefs());
        System.out.println("Money: " + restaurant.getMoney());
        System.out.println("Score: " + restaurant.getScore());
    }
}
