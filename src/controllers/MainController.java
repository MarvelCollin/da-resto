package controllers;

import utils.Validator;
import utils.Switch;
import views.MainMenuView;

public class MainController {
    public final MainMenuView mainMenuView = new MainMenuView();
    
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
				() -> new HighscoreController().start(),
                () -> exitMenu()
            );
        }
    }
	
	public void exitMenu() {
		System.out.println("Thank you for playing!");
		System.exit(0);
	}
}
