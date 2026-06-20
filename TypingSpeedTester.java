import java.util.Random;
import java.util.Scanner;

public class TypingSpeedTester {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] paragraphs = {
                "Java is a powerful programming language.",
                "Practice coding every day to improve your skills.",
                "Data structures and algorithms are important."
        };

        Random random = new Random();

        String paragraph =
                paragraphs[random.nextInt(paragraphs.length)];

        System.out.println("=================================");
        System.out.println("      TYPING SPEED TESTER");
        System.out.println("=================================");

        System.out.println();
        System.out.println("Type the following paragraph:");
        System.out.println();
        System.out.println(paragraph);

        System.out.println();
        System.out.println("Start typing below:");
        System.out.println();

        long startTime = System.currentTimeMillis();

        String typedText = sc.nextLine();

        long endTime = System.currentTimeMillis();

        double timeTaken =
                (endTime - startTime) / 1000.0;

        int wordsTyped =
                typedText.trim().split("\\s+").length;

        double wpm = calculateWPM(wordsTyped, timeTaken);

        System.out.println();
        System.out.println("========== RESULT ==========");

        System.out.printf("Time Taken : %.2f seconds%n",
                timeTaken);

        System.out.println("Words Typed: " +
                wordsTyped);

        System.out.printf("WPM        : %.2f%n",
                wpm);

        sc.close();
    }

    public static double calculateWPM(int wordsTyped, double timeTaken) {
        double minutes = timeTaken / 60.0;
        return wordsTyped / minutes;
    }

    public static int countCorrectCharacters(String original, String typed) {
        int correct = 0;
        int minLength = Math.min(original.length(), typed.length());
        for (int i = 0; i < minLength; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                correct++;
            }
        }
        return correct;
    }

    public static double calculateAccuracy(String original, String typed) {
        int correct = countCorrectCharacters(original, typed);
        return ((double) correct / original.length()) * 100;
    }

    public static int countMistakes(String original, String typed) {
        int correct = countCorrectCharacters(original, typed);
        return original.length() - correct;
    }
}

