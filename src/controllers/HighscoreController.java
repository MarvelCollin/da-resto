package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HighscoreController {
    private static final String HIGHSCORE_PATH = "highscore.txt";
    
    public HighscoreController() {
        start();
    }

    public void start() {
        readHighscores();
    }
    
    private void readHighscores() {
        try {
            File file = new File(HIGHSCORE_PATH);
            Scanner reader = new Scanner(file);
            
            while(reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
            }
            reader.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("No highscores yet!");
        }
    }
}
