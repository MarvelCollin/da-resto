package views;

import models.Restaurant;
import models.Entity.*;
import utils.Prettifier;
import utils.Debugger;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UpgradeMenuView {
    private Restaurant restaurant;

    public UpgradeMenuView(Restaurant restaurant) {
        this.restaurant = restaurant;
        Debugger.restaurantInitializationDebug("Restaurant object initialized in UpgradeMenuView constructor.");
    }

    public void showUpgradeMenu() {
        if (restaurant == null) {
            Debugger.restaurantInitializationDebug("Restaurant object is null in showUpgradeMenu.");
            throw new NullPointerException("Restaurant object is not initialized.");
        }
        Prettifier.cls();
        System.out.println("═════════════════════════════");
        System.out.println("UPGRADE MENU");
        System.out.println("Restaurant: " + restaurant.getName());
        System.out.println("Money: Rp. " + restaurant.getMoney());
        System.out.println("Score: " + restaurant.getScore());
        System.out.println("Seats: " + restaurant.getSeats());
        System.out.println("═════════════════════════════");
        System.out.println("1. Increase Restaurant's Seat (Rp. " + (100 * restaurant.getSeats()) + ")");
        System.out.println("2. Hire New Employee");
        System.out.println("3. Upgrade Waiter (Rp. 150)");
        System.out.println("4. Upgrade Chef (Rp. 150)");
        System.out.println("5. Back to Pause Menu");
        System.out.println("═════════════════════════════");
    }

    public void showHireMenu(Restaurant restaurant) {
        Prettifier.cls();
        System.out.println("═════════════════════════════");
        System.out.println("HIRE MENU");
        System.out.println("1. Hire Waiter (Rp. " + (150 * restaurant.getWaiters().size()) + ")");
        System.out.println("2. Hire Chef (Rp. " + (200 * restaurant.getChefs().size()) + ")");
        System.out.println("3. Back");
        System.out.println("═════════════════════════════");
    }

    public void showWaiterList(CopyOnWriteArrayList<Waiter> waiters) {
        Prettifier.cls();
        System.out.println("═════════════════════════════");
        System.out.println("WAITER LIST");
        System.out.println("═════════════════════════════");
        for (int i = 0; i < waiters.size(); i++) {
            Waiter w = waiters.get(i);
            System.out.printf("%d. %s (Speed: %d)\n", i + 1, w.getInitial(), w.getSpeed());
        }
        System.out.println("0. Back");
        System.out.println("═════════════════════════════");
    }

    public void showChefList(CopyOnWriteArrayList<Chef> chefs) {
        Prettifier.cls();
        System.out.println("═════════════════════════════");
        System.out.println("CHEF LIST");
        System.out.println("═════════════════════════════");
        for (int i = 0; i < chefs.size(); i++) {
            Chef c = chefs.get(i);
            System.out.printf("%d. %s (Speed: %d, Skill: %d)\n", 
                i + 1, c.getInitial(), c.getSpeed(), c.getSkill());
        }
        System.out.println("0. Back");
        System.out.println("═════════════════════════════");
    }

    public void showChefUpgradeOptions() {
        System.out.println("═════════════════════════════");
        System.out.println("1. Upgrade Speed");
        System.out.println("2. Upgrade Skill");
        System.out.println("═════════════════════════════");
    }

    public void showMaxSeatsError() {
        System.out.println("Maximum seats (13) reached!");
    }

    public void showMaxEmployeesError() {
        System.out.println("Maximum employees (7) reached!");
    }

    public void showMaxStatError() {
        System.out.println("Maximum stat level (5) reached!");
    }

    public void showInsufficientFundsError() {
        System.out.println("Insufficient funds!");
    }
}
