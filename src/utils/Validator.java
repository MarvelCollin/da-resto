package utils;

import java.util.Scanner;
import interfaces.IValidator;
import interfaces.IParser;

public class Validator {
    private static final Scanner scanner = new Scanner(System.in);

    public static <T> T getInput(Runnable prompt, IValidator<T> validator, IParser<T> parser) {
        while (true) {
            prompt.run();
            try {
                T input = parser.parse(scanner.nextLine().trim());
                if (validator.validate(input)) return input;
                System.out.println("Invalid input! Please try again.");
            } catch (Exception e) {
                System.out.println("Invalid format! Try again.");
            }
        }
    }

    public static int getValidIntInput(Runnable prompt, int min, int max) {
        return getInput(prompt, 
            input -> input >= min && input <= max,
            Integer::parseInt
        );
    }

    public static String getValidStringInput(Runnable prompt, int min, int max) {
        return getInput(prompt,
            input -> input.length() >= min && input.length() <= max,
            s -> s
        );
    }
}
