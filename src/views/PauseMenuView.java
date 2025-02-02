package views;

import models.Restaurant;
import utils.Prettifier;

public class PauseMenuView {
    public void showPauseMenu(Restaurant restaurant) {
        Prettifier.cls();
        System.out.println("═════════════════════════════");
        System.out.println("PAUSE MENU");
        System.out.println("Restaurant: " + restaurant.getName());
        System.out.println("Money: Rp. " + restaurant.getMoney());
        System.out.println("Score: " + restaurant.getScore());
        System.out.println("Seats: " + restaurant.getSeats());
        System.out.println("═════════════════════════════");
        System.out.println("1. Continue Business");
        System.out.println("2. Upgrade Restaurant");
        System.out.println("3. Close Business");
        System.out.println("═════════════════════════════");
    }
}
