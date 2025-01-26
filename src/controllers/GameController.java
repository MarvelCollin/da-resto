package controllers;

import controllers.Facades.GameFacade;
import controllers.Managers.HighscoreManager;
import utils.Validator;
import views.GameView;
import utils.Switch;
import views.PauseMenuView;
import java.util.Scanner;
import interfaces.IMenuAction;
import models.Restaurant;

public class GameController {
    private final GameFacade gameFacade;
    private final GameView gameView;
    private final PauseMenuView pauseMenuView;
    private final Restaurant restaurant;
    private final UpgradeController upgradeController;
    
    public GameController(Restaurant restaurant) {
        this.restaurant = restaurant;
        gameFacade = GameFacade.getInstance();
        gameView = new GameView();
        pauseMenuView = new PauseMenuView();
        upgradeController = new UpgradeController(restaurant);
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
                    System.out.println("\nPress 'Enter' to pause the game");
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
        while (true) {
            pauseMenuView.showPauseMenu(gameFacade.getRestaurant());
            
            int choice = Validator.getValidIntInput(
                () -> System.out.print("Choose menu [1-3]: "),
                1, 3
            );

            boolean shouldBreak = Switch.execute(choice,
                new IMenuAction() {
                    public boolean execute() {
                        gameFacade.resumeGame();
                        return true;
                    }
                },
                new IMenuAction() {
                    public boolean execute() {
                        upgradeController.start();
                        return false;
                    }
                },
                new IMenuAction() {
                    public boolean execute() {
                        closeRestaurant();
                        return true;
                    }
                }
            );
            
            if (shouldBreak) break;
        }
    }

    private void closeRestaurant() {
        HighscoreManager.getInstance().addScore(
            gameFacade.getRestaurant().getName(),
            gameFacade.getRestaurant().getScore()
        );
        System.exit(0);
    }

    public void showNameInput() {
        System.out.print("Enter Restaurant Name [3 - 15 Characters]: ");
    }
    
}
