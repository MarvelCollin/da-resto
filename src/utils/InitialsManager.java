package utils;

import java.util.ArrayList;
import java.util.Random;

public class InitialsManager {
    private static InitialsManager instance;
    private ArrayList<String> availableInitials;
    private Random random;
    
    private InitialsManager() {
        random = new Random();
        resetInitials();
    }
    
    public static InitialsManager getInstance() {
        if(instance == null) {
            instance = new InitialsManager();
        }
        return instance;
    }
    
    public void resetInitials() {
        availableInitials = new ArrayList<>();
        for(String initial : Constants.INITIALS) {
            availableInitials.add(initial);
        }
    }
    
    public String getUniqueInitial() {
        if(availableInitials.isEmpty()) {
            resetInitials();
        }
        int index = random.nextInt(availableInitials.size());
        return availableInitials.remove(index);
    }
}
