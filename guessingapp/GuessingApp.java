import java.util.Random;
import java.util.Scanner;

/**
 * Use Case 1: Game Initialization
 *
 * This class is responsible for:
 * - Setting game boundaries
 * - Generating a random target number
 * - Displaying game rules
 *
 * Demonstrates:
 * - Encapsulation
 * - Constructor initialization
 * - Random number generation
 */
class GameConfig {

    private final int MIN = 1;
    private final int MAX = 100;
    private final int MAX_ATTEMPTS = 7;
    private final int MAX_HINTS = 3;

    int targetNumber;

    /**
     * Constructor is automatically called when a GameConfig object is created.
     * It initializes the random target number for the game.
     */
    public GameConfig() {
        Random random = new Random();
        this.targetNumber = random.nextInt(MAX - MIN + 1) + MIN;
    }

    public int getTargetNumber() {
        return targetNumber;
    }

    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    public int getMaxHints() {
        return MAX_HINTS;
    }

    public void showRules() {
        System.out.println("🎯 Guess a number between " + MIN + " and " + MAX);
        System.out.println("You have " + MAX_ATTEMPTS + " attempts.");
        System.out.println("Hints will be provided after wrong guesses.\n");
    }
}

/*
 * Use Case 2: User Guess Submission
 *
 * This class is responsible for comparing
 * the user's guess with the target number.
 *
 * It does NOT handle input or output.
 */
class GuessValidator {

    /*
     * Compares guess with target and
     * returns the comparison result.
     */
    public static String validateGuess(int guess, int target) {

        if (guess == target) {
            return "CORRECT";
        } else if (guess < target) {
            return "LOW";
        }
        return "HIGH";
    }
}

/*
 * Use Case 3: Hint Generation
 *
 * This class is responsible for generating
 * controlled hints based on the number of
 * incorrect attempts made by the player.
 *
 * Hint logic is isolated to avoid cluttering
 * the main game flow.
 */
class HintService {

    /*
     * Generates a hint based on how many hints
     * have already been used.
     *
     * Hints provide partial information without
     * revealing the exact number.
     */
    public static String generateHint(int target, int hintCount) {

        if (hintCount == 1) {
            return (target % 2 == 0)
                    ? "Hint: Number is EVEN"
                    : "Hint: Number is ODD";
        } else if (hintCount == 2) {
            return (target > 50)
                    ? "Hint: Number is greater than 50"
                    : "Hint: Number is 50 or less";
        }

        return "No more hints available";
    }
}

/**
 * MAIN CLASS
 *
 * Coordinates the game flow:
 * 1. Initialize game
 * 2. Accept user guesses
 * 3. Validate guesses
 * 4. Stop when game ends
 *
 * @author Developer
 * @version 3.0
 */
public class GuessingApp {

    public static void main(String[] args) {

        System.out.println("Welcome to the Guessing App");

        GameConfig config = new GameConfig();
        config.showRules();

        Scanner scanner = new Scanner(System.in);
        int attempts = 0;
        int hintsUsed = 0;

        /*
         * Game loop runs until the player
         * exhausts the maximum attempts.
         */
        while (attempts < config.getMaxAttempts()) {

            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;

            String result = GuessValidator.validateGuess(
                    guess, config.getTargetNumber());

            /*
             * A hint is generated only after
             * an incorrect guess and within
             * the allowed hint limit.
             */
            if (!"CORRECT".equals(result) && hintsUsed < config.getMaxHints()) {
                hintsUsed++;
                System.out.println(
                        HintService.generateHint(config.getTargetNumber(), hintsUsed)
                );
            }

            System.out.println(result);

            /*
             * Stop the loop immediately
             * if the correct number is guessed.
             */
            if ("CORRECT".equals(result)) {
                break;
            }
        }
    }
}
