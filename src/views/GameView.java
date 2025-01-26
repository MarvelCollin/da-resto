package views;

import models.Restaurant;
import models.Entity.*;
import utils.Prettifier;
import utils.StringUtils;

public class GameView {
    public void startGame() {
        this.start();
    }

    public void start() {
    }

    public void displayGame(Restaurant restaurant) {
        Prettifier.cls();
        displayRestaurantInfo(restaurant);
        displayEntities(restaurant);
    }

    private void displayRestaurantInfo(Restaurant restaurant) {
        System.out.println("═════════════════════════════");
        System.out.println("Restaurant: " + restaurant.getName());
        System.out.println("Money: Rp. " + restaurant.getMoney());
        System.out.println("Score: " + restaurant.getScore());
        System.out.println("Seats: " + restaurant.getSeats());
        System.out.println("═════════════════════════════\n");
    }

    private void displayEntities(Restaurant restaurant) {
        System.out.println(StringUtils.createTable(
            restaurant.getCustomers(),
            restaurant.getWaiters(),
            restaurant.getChefs()
        ));
    }

    public void displayStatus() {
    }
}
