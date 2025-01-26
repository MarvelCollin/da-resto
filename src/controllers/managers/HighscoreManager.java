package controllers.managers;

import java.io.*;
import java.util.*;

public class HighscoreManager {
    private static HighscoreManager instance;
    private static final String HIGHSCORE_FILE = "highscore.txt";
    private ArrayList<Integer> scores;
    
    private HighscoreManager() {
        scores = new ArrayList<>();
        loadHighscores();
    }
    
    public static HighscoreManager getInstance() {
        if(instance == null) {
            instance = new HighscoreManager();
        }
        return instance;
    }
    
    private void loadHighscores() {
        try {
            File file = new File(HIGHSCORE_FILE);
            if(!file.exists()) {
                file.createNewFile();
                return;
            }
            
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextInt()) {
                scores.add(scanner.nextInt());
            }
            scanner.close();
            
            Collections.sort(scores, Collections.reverseOrder());
        } catch (IOException e) {
            System.out.println("Error loading highscores!");
        }
    }
    
    public void addScore(int score) {
        scores.add(score);
        Collections.sort(scores, Collections.reverseOrder());
        saveHighscores();
    }
    
    private void saveHighscores() {
        try {
            FileWriter writer = new FileWriter(HIGHSCORE_FILE);
            for(int score : scores) {
                writer.write(score + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving highscores!");
        }
    }
    
    public ArrayList<Integer> getHighscores() {
        return new ArrayList<>(scores);
    }
}
