package controllers.Managers;

import java.io.*;
import java.util.*;

public class HighscoreManager {
    private static HighscoreManager instance;
    private static final String HIGHSCORE_FILE = "highscore.txt";
    private List<RestaurantScore> scores;
    
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
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("#");
                if (parts.length == 2) {
                    scores.add(new RestaurantScore(parts[0], Integer.parseInt(parts[1])));
                }
            }
            scanner.close();
            
            Collections.sort(scores);
        } catch (IOException e) {
            System.out.println("Error loading highscores!");
        }
    }
    
    public void addScore(String name, int score) {
        scores.add(new RestaurantScore(name, score));
        Collections.sort(scores);
        if (scores.size() > 10) {
            scores = scores.subList(0, 10);
        }
        saveHighscores();
    }
    
    private void saveHighscores() {
        try {
            FileWriter writer = new FileWriter(HIGHSCORE_FILE);
            for(RestaurantScore score : scores) {
                writer.write(score.name + "#" + score.score + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving highscores!");
        }
    }
    
    public List<RestaurantScore> getHighscores() {
        return new ArrayList<>(scores);
    }
    
    public boolean isInTopTen(int score) {
        if (scores.size() < 10) return true;
        return score > scores.get(scores.size() - 1).score;
    }
    
    public static class RestaurantScore implements Comparable<RestaurantScore> {
        private String name;
        private int score;
        
        public RestaurantScore(String name, int score) {
            this.name = name;
            this.score = score;
        }
        
        public String getName() { return name; }
        public int getScore() { return score; }
        
        @Override
        public int compareTo(RestaurantScore other) {
            return other.score - this.score;
        }
        
        @Override
        public String toString() {
            return String.format("%s - %d", name, score);
        }
    }
}
