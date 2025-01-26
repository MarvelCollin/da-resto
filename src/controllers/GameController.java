package controllers;

import controllers.Facades.GameFacade;
import utils.Validator;
import views.GameView;

public class GameController {
    private final GameFacade gameFacade;
    private final GameView gameView;
    
    public GameController() {
        gameFacade = GameFacade.getInstance();
        gameView = new GameView();
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
