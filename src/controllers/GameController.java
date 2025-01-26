package controllers;

import controllers.Facades.GameFacade;
import controllers.Managers.HighscoreManager;
import utils.Validator;
import views.GameView;
import utils.Switch;
import views.PauseMenuView;
import views.HighscoreView;
import java.util.Scanner;
import interfaces.IMenuAction;
import models.Restaurant;

public class GameController {
    private final GameFacade gameFacade;
    private final GameView gameView;
    private final PauseMenuView pauseMenuView;
    private final HighscoreView highscoreView;
    private final Restaurant restaurant;
    private final UpgradeController upgradeController;
    
    public GameController(Restaurant restaurant) {
        this.restaurant = restaurant;
        gameFacade = GameFacade.getInstance();
        gameView = new GameView();
        pauseMenuView = new PauseMenuView();
        highscoreView = new HighscoreView();
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
        int finalScore = restaurant.getScore();
        String restaurantName = restaurant.getName();
        
        HighscoreManager highscoreManager = HighscoreManager.getInstance();
        
        
        highscoreManager.addScore(restaurantName, finalScore);
        boolean isTopTen = highscoreManager.isInTopTen(finalScore);
        
        System.out.println("\n═════════════════════════════");
        System.out.println("     RESTAURANT CLOSED");
        System.out.println("═════════════════════════════");
        System.out.println("Restaurant: " + restaurantName);
        System.out.println("Final Score: " + finalScore);
        System.out.println("═════════════════════════════");
        
        if (isTopTen) {
            System.out.println("\n>>> Congratulations! You made it to the top 10! <<<\n");
        }
        
        
        highscoreView.showHighscores(highscoreManager.getHighscores());
        
        
        System.out.println("\nPress Enter to continue...");
        new Scanner(System.in).nextLine();
        
        gameFacade.shutdown();
        System.exit(0);
    }

    public void showNameInput() {
        System.out.print("Enter Restaurant Name [3 - 15 Characters]: ");
    }
    
}
