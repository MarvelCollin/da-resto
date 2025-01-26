package views;

import java.util.ArrayList;

public class HighscoreView {
    public void showHighscores(ArrayList<Integer> scores) {
        System.out.println("\n====== Highscores ======");
        if(scores.isEmpty()) {
            System.out.println("No highscores yet!");
        } else {
            for(int i = 0; i < Math.min(scores.size(), 10); i++) {
                System.out.printf("%d. %d\n", i+1, scores.get(i));
            }
        }
        System.out.println("=====================\n");
    }
}
