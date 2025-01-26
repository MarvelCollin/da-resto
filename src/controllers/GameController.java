package controllers;

import controllers.Facades.GameFacade;
import utils.Validator;
import views.GameView;

public class GameController {
    private GameFacade gameFacade;
    private GameView gameView;
    
    public GameController() {
        this.gameFacade = GameFacade.getInstance();
        this.gameView = new GameView();
        start();
    }

    public void start() {
        String name = Validator.getValidStringInput(
            this::showNameInput,
            3, 15
        );
        
        gameFacade.startNewGame(name);
        gameView.displayGame(gameFacade.getRestaurant());
    }

    public void showNameInput() {
        System.out.print("Enter Restaurant Name [3 - 15 Characters]: ");
    }
    
}
