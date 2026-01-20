import java.util.Random;

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

/**
 * GuessingApp - Use Case 1: Game Initialization
 *
 * This class serves as the application entry point.
 * It initializes the game configuration and displays game rules.
 *
 * No user input or gameplay logic is implemented at this stage.
 *
 * @author Developer
 * @version 1.0
 */
public class GuessingApp {

    public static void main(String[] args) {

        System.out.println("Welcome to the Guessing App");
        GameConfig gameConfig = new GameConfig();
        gameConfig.showRules();
    }
}
