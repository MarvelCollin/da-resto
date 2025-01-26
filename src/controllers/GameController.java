package controllers;

import models.Restaurant;
import utils.Validator;
import views.GameView;

public class GameController {
    private Restaurant restaurant;
    private GameView gameView;
    
    public GameController() {
        gameView = new GameView();
        start();
    }

    public void start() {
        String name = Validator.getValidStringInput(
            this::showNameInput,
            3, 15
        );
        
        restaurant = Restaurant.createRestaurant(name);
        gameView.displayGame(restaurant);
    }

    public void showNameInput() {
        System.out.print("Enter Restaurant Name [3 - 15 Characters]: ");
    }
    
}
