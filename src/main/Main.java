package main;

import controllers.MainController;
import models.Restaurant;

public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant("DaResto");
        MainController mainController = new MainController(restaurant);
        mainController.start();
    }
}
