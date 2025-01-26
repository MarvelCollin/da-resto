package controllers;

import controllers.Facades.GameFacade;
import controllers.Managers.HighscoreManager;  
import models.Restaurant;
import models.Entity.*;
import utils.Validator;
import utils.Switch;
import views.UpgradeMenuView;
import java.util.concurrent.CopyOnWriteArrayList;

public class UpgradeController {
    private final GameFacade gameFacade;
    private final UpgradeMenuView upgradeMenuView;
    private final HighscoreManager highscoreManager;  
    
    public UpgradeController(Restaurant restaurant) {
        this.gameFacade = GameFacade.getInstance();
        this.upgradeMenuView = new UpgradeMenuView();
        this.highscoreManager = HighscoreManager.getInstance();  
    }

    private Restaurant getActiveRestaurant() {
        return Restaurant.getActiveRestaurant();
    }

    public void start() {
        while (true) {
            upgradeMenuView.showUpgradeMenu(getActiveRestaurant()); 
            
            int choice = Validator.getValidIntInput(
                () -> System.out.print("Choose menu [1-5]: "),
                1, 5
            );

            if (choice == 5) return;

            Switch.execute(choice,
                this::increaseSeats,
                this::showHireMenu,
                this::upgradeWaiter,
                this::upgradeCook
            );
        }
    }

    private void increaseSeats() {
        Restaurant restaurant = getActiveRestaurant();
        if (restaurant.getSeats() >= 13) {
            upgradeMenuView.showMaxSeatsError();
            return;
        }

        int cost = 100 * restaurant.getSeats();
        if (restaurant.getMoney() < cost) {
            upgradeMenuView.showInsufficientFundsError();
            return;
        }

        restaurant.setMoney(restaurant.getMoney() - cost);
        restaurant.setSeats(restaurant.getSeats() + 1);
    }

    private void showHireMenu() {
        while (true) {
            upgradeMenuView.showHireMenu(getActiveRestaurant());
            
            int choice = Validator.getValidIntInput(
                () -> System.out.print("Choose employee type [1-3]: "),
                1, 3
            );

            if (choice == 3) return;

            Switch.execute(choice,
                this::hireWaiter,
                this::hireChef
            );
        }
    }

    private void hireWaiter() {
        Restaurant restaurant = getActiveRestaurant();
        CopyOnWriteArrayList<Waiter> waiters = restaurant.getWaiters();
        if (waiters.size() >= 7) {
            upgradeMenuView.showMaxEmployeesError();
            return;
        }

        
        int cost = 150 * (waiters.size() + 1);
        if (restaurant.getMoney() < cost) {
            upgradeMenuView.showInsufficientFundsError();
            return;
        }

        restaurant.setMoney(restaurant.getMoney() - cost);
        gameFacade.hireWaiter();
    }

    private void hireChef() {
        Restaurant restaurant = getActiveRestaurant();
        CopyOnWriteArrayList<Chef> chefs = restaurant.getChefs();
        if (chefs.size() >= 7) {
            upgradeMenuView.showMaxEmployeesError();
            return;
        }

        
        int cost = 200 * (chefs.size() + 1);
        if (restaurant.getMoney() < cost) {
            upgradeMenuView.showInsufficientFundsError();
            return;
        }

        restaurant.setMoney(restaurant.getMoney() - cost);
        gameFacade.hireChef();
    }

    private void upgradeWaiter() {
        Restaurant restaurant = getActiveRestaurant();
        CopyOnWriteArrayList<Waiter> waiters = restaurant.getWaiters();
        
        if (waiters.isEmpty()) {
            System.out.println("No waiters to upgrade!");
            return;
        }

        while (true) {
            upgradeMenuView.showWaiterList(waiters);
            
            int choice = Validator.getValidIntInput(
                () -> System.out.print("Choose waiter [0-" + waiters.size() + "]: "),
                0, waiters.size()
            );

            if (choice == 0) return;

            Waiter waiter = waiters.get(choice - 1);
            if (waiter.getSpeed() >= 5) {
                upgradeMenuView.showMaxStatError();
                continue;
            }

            if (restaurant.getMoney() < 150) {
                upgradeMenuView.showInsufficientFundsError();
                continue;
            }

            restaurant.setMoney(restaurant.getMoney() - 150);
            waiter.setSpeed(waiter.getSpeed() + 1);
            return;
        }
    }

    private void upgradeCook() {
        Restaurant restaurant = getActiveRestaurant();
        CopyOnWriteArrayList<Chef> chefs = restaurant.getChefs();
        
        if (chefs.isEmpty()) {
            System.out.println("No chefs to upgrade!");
            return;
        }

        while (true) {
            upgradeMenuView.showChefList(chefs);
            
            int choice = Validator.getValidIntInput(
                () -> System.out.print("Choose chef [0-" + chefs.size() + "]: "),
                0, chefs.size()
            );

            if (choice == 0) return;

            Chef chef = chefs.get(choice - 1);
            upgradeMenuView.showChefUpgradeOptions();
            
            int statChoice = Validator.getValidIntInput(
                () -> System.out.print("Choose stat to upgrade [1-2]: "),
                1, 2
            );

            if (restaurant.getMoney() < 150) {
                upgradeMenuView.showInsufficientFundsError();
                continue;
            }

            boolean upgraded = false;
            if (statChoice == 1 && chef.getSpeed() < 5) {
                chef.setSpeed(chef.getSpeed() + 1);
                upgraded = true;
            } else if (statChoice == 2 && chef.getSkill() < 5) {
                chef.setSkill(chef.getSkill() + 1);
                upgraded = true;
            }

            if (upgraded) {
                restaurant.setMoney(restaurant.getMoney() - 150);
                return;
            } else {
                upgradeMenuView.showMaxStatError();
            }
        }
    }
}
