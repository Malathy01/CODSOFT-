import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        int score = 0; 
        boolean playAgain = true;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I will think of a number between 1 and 100.");

        while (playAgain) {
            int secretNumber = rand.nextInt(100) + 1; 
            int attemptsLeft = 3; 
            boolean guessedCorrectly = false;

            System.out.println("\nNew Round Started!");
            System.out.println("You have " + attemptsLeft + " attempts to guess the number.");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");

                while (!input.hasNextInt()) {
                    System.out.print("Invalid input! Enter a number: ");
                    input.next();
                }

                int guess = input.nextInt();
                attemptsLeft--;

                if (guess == secretNumber) {
                    System.out.println("Correct! You guessed the number.");
                    score++;
                    guessedCorrectly = true;
                    break;
                } else if (guess < secretNumber) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }

                if (attemptsLeft > 0) {
                    System.out.println("Attempts left: " + attemptsLeft);
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Out of attempts! The number was: " + secretNumber);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String choice = input.next().trim().toLowerCase();
            if (!choice.equals("yes") && !choice.equals("y")) {
                playAgain = false;
            }
        }

        System.out.println("\nGame Over! Your score: " + score + " round(s) won.");}
}