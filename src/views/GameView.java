package views;

import models.Restaurant;
import models.Entity.*;
import java.util.List;

public class GameView {
    public void startGame() {
        this.start();
    }

    public void start(){

    }

    public void displayGame(Restaurant restaurant){ 
        System.out.println("=====================");
        System.out.printf("    %s%n", restaurant.getName());
        System.out.println("=====================");
        displayTable(restaurant);
    }

    private void displayTable(Restaurant restaurant) {
        final String BORDER = "+----------+----------+----------+";
        final String HEADER = "| Customer | Waiter   | Chef     |";
        
        System.out.println(BORDER);
        System.out.println(HEADER);
        System.out.println(BORDER);
        
        int maxRows = getMaxRows(restaurant);
        for(int i = 0; i < maxRows; i++) {
            System.out.println(createRowString(
                getInitialOrEmpty(restaurant.getCustomers(), i),
                getInitialOrEmpty(restaurant.getWaiters(), i),
                getInitialOrEmpty(restaurant.getChefs(), i)
            ));
        }
        
        System.out.println(BORDER);
    }

    private <T extends Entity> String getInitialOrEmpty(List<T> entities, int index) {
        return index < entities.size() ? 
            String.format("| %-8s ", entities.get(index).getInitial()) : 
            "|          ";
    }

    private String createRowString(String customer, String waiter, String chef) {
        return customer + waiter + chef.replace(" |", "|");
    }

    private int getMaxRows(Restaurant restaurant) {
        return Math.max(
            Math.max(
                restaurant.getCustomers().size(),
                restaurant.getWaiters().size()
            ),
            restaurant.getChefs().size()
        );
    }

    public void displayStatus(){

    }
}
