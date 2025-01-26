package views;

import models.Restaurant;
import models.Entity.*;
import java.util.ArrayList;
import utils.Prettifier;

public class UpgradeMenuView {
    
    public void showUpgradeMenu(Restaurant restaurant) {
        Prettifier.cls();
        showRestaurantStatus(restaurant);
        System.out.println("\nUpgrade Menu:");
        System.out.printf("1. Increase Restaurant's Seat (Rp. %d)\n", 
            100 * restaurant.getSeats());
        System.out.println("2. Hire New Employee");
        System.out.println("3. Upgrade Waiter (Rp. 150)");
        System.out.println("4. Upgrade Cook (Rp. 150)");
        System.out.println("5. Back to Pause Menu");
    }
    
    private void showRestaurantStatus(Restaurant restaurant) {
        System.out.println("Restaurant Status");
        System.out.println("================");
        System.out.printf("Money: Rp. %d\n", restaurant.getMoney());
        System.out.printf("Score: %d\n", restaurant.getScore());
        System.out.printf("Seats: %d\n", restaurant.getSeats());
    }
    
    public void showHireMenu(Restaurant restaurant) {
        System.out.println("\nHire Menu:");
        System.out.printf("1. Hire Waiter (Rp. %d)\n", 
            150 * restaurant.getWaiters().size());
        System.out.printf("2. Hire Chef (Rp. %d)\n", 
            200 * restaurant.getChefs().size());
        System.out.println("3. Back");
    }
    
    public void showWaiterList(ArrayList<Waiter> waiters) {
        System.out.println("\nWaiter List:");
        System.out.println("============");
        for (int i = 0; i < waiters.size(); i++) {
            Waiter w = waiters.get(i);
            System.out.printf("%d. %s (Speed: %d)\n", 
                i + 1, w.getInitial(), w.getSpeed());
        }
        System.out.println("0. Back");
    }
    
    public void showChefList(ArrayList<Chef> chefs) {
        System.out.println("\nChef List:");
        System.out.println("==========");
        for (int i = 0; i < chefs.size(); i++) {
            Chef c = chefs.get(i);
            System.out.printf("%d. %s (Speed: %d, Skill: %d)\n", 
                i + 1, c.getInitial(), c.getSpeed(), c.getSkill());
        }
        System.out.println("0. Back");
    }
    
    public void showChefUpgradeOptions() {
        System.out.println("\nUpgrade Options:");
        System.out.println("1. Speed");
        System.out.println("2. Skill");
    }
    
    // Error messages
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
