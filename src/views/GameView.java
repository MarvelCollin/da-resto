package views;

import models.Restaurant;
import utils.StringUtils;

public class GameView {
    public void startGame() {
        this.start();
    }

    public void start() {
    }

    public void displayGame(Restaurant restaurant){ 
        System.out.println("=====================");
        System.out.printf("    %s%n", restaurant.getName());
        System.out.println("=====================");
        
        System.out.println(StringUtils.createTable(
            restaurant.getCustomers(),
            restaurant.getWaiters(),
            restaurant.getChefs()
        ));
    }

    public void displayStatus() {
    }
}
