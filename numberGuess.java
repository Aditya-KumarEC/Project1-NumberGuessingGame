import java.util.Random;
import java.util.Scanner;

public class numberGuess {

    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 100;
    private static final int MAX_ATTEMPTS = 7;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Advanced Number Guessing Game!");
        System.out.println("Try to guess the number between " + LOWER_BOUND + " and " + UPPER_BOUND);

        boolean playAgain;
        int totalGames = 0;
        int totalWins = 0;

        do {
            totalGames++;
            boolean won = playGame(scanner);
            if (won) totalWins++;

            System.out.print("\nDo you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("\nGame Summary:");
        System.out.println("Total Games Played: " + totalGames);
        System.out.println("Games Won: " + totalWins);
        System.out.println("Thanks for playing!");
        scanner.close();
    }

    private static boolean playGame(Scanner scanner) {
        Random random = new Random();
        int target = random.nextInt(UPPER_BOUND - LOWER_BOUND + 1) + LOWER_BOUND;
        int attemptsLeft = MAX_ATTEMPTS;

        while (attemptsLeft > 0) {
            int guess = getValidGuess(scanner, attemptsLeft);
            attemptsLeft--;

            if (guess == target) {
                System.out.println("Correct! You guessed the number in " + (MAX_ATTEMPTS - attemptsLeft) + " tries.");
                return true;
            } else if (guess < target) {
                System.out.println("Too low!");
            } else {
                System.out.println("Too high!");
            }
        }

        System.out.println("You've run out of attempts. The number was: " + target);
        return false;
    }

    private static int getValidGuess(Scanner scanner, int attemptsLeft) {
        int guess = -1;
        boolean valid = false;

        while (!valid) {
            System.out.print("Enter your guess (" + attemptsLeft + " attempts left): ");
            if (scanner.hasNextInt()) {
                guess = scanner.nextInt();
                if (guess >= LOWER_BOUND && guess <= UPPER_BOUND) {
                    valid = true;
                } else {
                    System.out.println("Please enter a number between " + LOWER_BOUND + " and " + UPPER_BOUND);
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }

        return guess;
    }
} 