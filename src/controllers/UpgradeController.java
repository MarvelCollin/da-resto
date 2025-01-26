package controllers;

import controllers.Facades.GameFacade;
import models.Restaurant;
import models.Entity.*;
import utils.Validator;
import utils.Switch;
import views.UpgradeMenuView;
import java.util.concurrent.CopyOnWriteArrayList;

public class UpgradeController {
    private final GameFacade gameFacade;
    private final UpgradeMenuView upgradeMenuView;
    private final Restaurant restaurant;
    
    public UpgradeController(Restaurant restaurant) {
        this.gameFacade = GameFacade.getInstance();
        this.upgradeMenuView = new UpgradeMenuView(restaurant);
        this.restaurant = restaurant;
    }

    public void start() {
        while (true) {
            upgradeMenuView.showUpgradeMenu(); 
            
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
            upgradeMenuView.showHireMenu(restaurant);
            
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
        CopyOnWriteArrayList<Waiter> waiters = restaurant.getWaiters();
        if (waiters.size() >= 7) {
            upgradeMenuView.showMaxEmployeesError();
            return;
        }

        int cost = 150 * waiters.size();
        if (restaurant.getMoney() < cost) {
            upgradeMenuView.showInsufficientFundsError();
            return;
        }

        restaurant.setMoney(restaurant.getMoney() - cost);
        gameFacade.hireWaiter();
    }

    private void hireChef() {
        CopyOnWriteArrayList<Chef> chefs = restaurant.getChefs();
        if (chefs.size() >= 7) {
            upgradeMenuView.showMaxEmployeesError();
            return;
        }

        int cost = 200 * chefs.size();
        if (restaurant.getMoney() < cost) {
            upgradeMenuView.showInsufficientFundsError();
            return;
        }

        restaurant.setMoney(restaurant.getMoney() - cost);
        gameFacade.hireChef();
    }

    private void upgradeWaiter() {
        while (true) {
            upgradeMenuView.showWaiterList(restaurant.getWaiters());
            
            int choice = Validator.getValidIntInput(
                () -> System.out.print("Choose waiter [0-" + restaurant.getWaiters().size() + "]: "),
                0, restaurant.getWaiters().size()
            );

            if (choice == 0) return;

            Waiter waiter = restaurant.getWaiters().get(choice - 1);
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
        while (true) {
            upgradeMenuView.showChefList(restaurant.getChefs());
            
            int choice = Validator.getValidIntInput(
                () -> System.out.print("Choose chef [0-" + restaurant.getChefs().size() + "]: "),
                0, restaurant.getChefs().size()
            );

            if (choice == 0) return;

            Chef chef = restaurant.getChefs().get(choice - 1);
            upgradeMenuView.showChefUpgradeOptions();
            
            int statChoice = Validator.getValidIntInput(
                () -> System.out.print("Choose stat to upgrade [1-2]: "),
                1, 2
            );

            if (restaurant.getMoney() < 150) {
                upgradeMenuView.showInsufficientFundsError();
                continue;
            }

            restaurant.setMoney(restaurant.getMoney() - 150);
            if (statChoice == 1 && chef.getSpeed() < 5) {
                chef.setSpeed(chef.getSpeed() + 1);
            } else if (statChoice == 2 && chef.getSkill() < 5) {
                chef.setSkill(chef.getSkill() + 1);
            } else {
                upgradeMenuView.showMaxStatError();
                continue;
            }
            return;
        }
    }
}
