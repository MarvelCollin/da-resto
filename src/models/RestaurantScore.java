package models;

public class RestaurantScore implements Comparable<RestaurantScore> {
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
