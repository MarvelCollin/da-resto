package controllers;

import utils.Validator;
import utils.Switch;
import views.MainMenuView;
import views.HighscoreView;

import java.util.List;

import controllers.Managers.HighscoreManager;
import controllers.Managers.HighscoreManager.RestaurantScore;

public class MainController {
    public final MainMenuView mainMenuView = new MainMenuView();
    private final HighscoreView highscoreView = new HighscoreView();
    
    public MainController() {
        start();
    }
    
    public void start() {
        while(true) {
            mainMenuView.showTitle();
            mainMenuView.showMainMenu();
            
            int choice = Validator.getValidIntInput(
                () -> System.out.print("Enter your choice (1-3): "), 
                1, 3
            );
            
            Switch.execute(choice, 
                () -> new GameController().start(),
                () -> showHighscores(),
                () -> exitMenu()
            );
        }
    }
    
    private void showHighscores() {
        List<RestaurantScore> scores = HighscoreManager.getInstance().getHighscores();
        highscoreView.showHighscores(scores);
    }
    
    public void exitMenu() {
        System.out.println("Thank you for playing!");
        System.exit(0);
    }
}
