import java.util.Random;
import java.util.Scanner;

public class TypingSpeedTester {

    public static void main(String[] args) {

        String[] paragraphs = {
                "Java is a powerful programming language.",
                "Practice coding every day to improve your skills.",
                "Data structures and algorithms are important."
        };

        Random random = new Random();

        String paragraph =
                paragraphs[random.nextInt(paragraphs.length)];

        System.out.println("=== Typing Speed Tester ===");
        System.out.println();
        System.out.println("Type the following paragraph:");
        System.out.println(paragraph);

        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("Start typing below:");

        long startTime = System.currentTimeMillis();

        String typedText = sc.nextLine();

        long endTime = System.currentTimeMillis();

        double timeTaken =
                (endTime - startTime) / 1000.0;

        System.out.println("Time Taken: " +
                   timeTaken +
                   " seconds");

        int wordsTyped =
                typedText.trim().split("\\s+").length;

        System.out.println("Words Typed: " +
                   wordsTyped);
    }
}
