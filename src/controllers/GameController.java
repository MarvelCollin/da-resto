package controllers;

import controllers.Facades.GameFacade;
import utils.Validator;
import views.GameView;
import java.util.Scanner;

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
        runGame();
    }

    private void runGame() {
        Scanner scanner = new Scanner(System.in);
        Thread gameThread = new Thread(() -> {
            while (true) {
                if (!gameFacade.isPaused()) {
                    gameFacade.updateGame();
                    gameView.displayGame(gameFacade.getRestaurant());
                }
                try {
                    Thread.sleep(1000); 
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        
        gameThread.start();
        
        while (true) {
            if (scanner.nextLine().isEmpty()) {
                gameFacade.pauseGame();
                showPauseMenu();
            }
        }
    }

    private void showPauseMenu() {
        
        gameFacade.resumeGame();
    }

    public void showNameInput() {
        System.out.print("Enter Restaurant Name [3 - 15 Characters]: ");
    }
    
}
