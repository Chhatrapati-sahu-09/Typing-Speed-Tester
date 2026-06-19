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

        String typedText = sc.nextLine();
    }
}
