import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class TypingSpeedTester {

    private static Scanner sc = new Scanner(System.in);
    private static String username;

    public static double calculateWPM(
            int wordsTyped,
            double timeTaken) {

        double minutes = timeTaken / 60.0;

        return wordsTyped / minutes;
    }

    public static int countCorrectCharacters(
            String original,
            String typed) {

        int correct = 0;

        int minLength =
                Math.min(original.length(),
                        typed.length());

        for(int i = 0; i < minLength; i++) {

            if(original.charAt(i)
                    == typed.charAt(i)) {

                correct++;
            }
        }

        return correct;
    }

    public static double calculateAccuracy(
            String original,
            String typed) {

        int correct =
                countCorrectCharacters(
                        original,
                        typed);

        return ((double) correct
                / original.length()) * 100;
    }

    public static int countMistakes(
            String original,
            String typed) {

        int correct =
                countCorrectCharacters(
                        original,
                        typed);

        return original.length() - correct;
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println(
        Colors.CYAN + Colors.BOLD +
        """
╔══════════════════════════════════════════════════════════════╗
║                                                              ║
║   ████████╗██╗   ██╗██████╗ ██╗███╗   ██╗ ██████╗           ║
║   ╚══██╔══╝╚██╗ ██╔╝██╔══██╗██║████╗  ██║██╔════╝           ║
║      ██║    ╚████╔╝ ██████╔╝██║██╔██╗ ██║██║  ███╗          ║
║      ██║     ╚██╔╝  ██╔═══╝ ██║██║╚██╗██║██║   ██║          ║
║      ██║      ██║   ██║     ██║██║ ╚████║╚██████╔╝          ║
║      ╚═╝      ╚═╝   ╚═╝     ╚═╝╚═╝  ╚═══╝ ╚═════╝           ║
║                                                              ║
║               ⚡ SPEED TESTER PRO ⚡                         ║
║                                                              ║
╚══════════════════════════════════════════════════════════════╝
"""
        + Colors.RESET);

        System.out.print("Enter Username: ");
        username = sc.nextLine();

        loadingAnimation();

        while(true) {

            System.out.println(
            Colors.YELLOW +
            """
┌───────────────────────────────┐
│        MAIN MENU              │
├───────────────────────────────┤
│  1. Start Test                │
│  2. View Scores               │
│  3. Leaderboard               │
│  4. Exit                      │
└───────────────────────────────┘
"""
            + Colors.RESET);

            System.out.print(
                    "Choose: ");

            int choice =
                    Integer.parseInt(
                            sc.nextLine());

            switch(choice) {

                case 1:
                    startTest();
                    break;

                case 2:
                    viewScores();
                    break;

                case 3:
                    showLeaderboard();
                    break;

                case 4:
                    System.exit(0);

                default:
                    System.out.println(
                            "Invalid choice.");
            }
        }
    }

    public static void startTest() throws InterruptedException {

        System.out.println();
        System.out.println("Choose Difficulty Level:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        System.out.print("Choose: ");

        int levelChoice = Integer.parseInt(sc.nextLine());

        String[] easy = {
                "Java is fun.",
                "Practice coding daily.",
                "Programming improves logic."
        };

        String[] medium = {
                "Data structures are important in software development.",
                "Object oriented programming helps organize code."
        };

        String[] hard = {
                "Multithreading enables concurrent execution of multiple tasks.",
                "Algorithms and optimization techniques improve efficiency."
        };

        String[] selectedParagraphs;
        switch(levelChoice) {
            case 1:
                selectedParagraphs = easy;
                break;
            case 2:
                selectedParagraphs = medium;
                break;
            case 3:
                selectedParagraphs = hard;
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Easy.");
                selectedParagraphs = easy;
                break;
        }

        Random random = new Random();

        String paragraph =
                selectedParagraphs[random.nextInt(
                        selectedParagraphs.length)];

        System.out.println(
                "=================================");

        System.out.println(
                "      TYPING SPEED TESTER");

        System.out.println(
                "=================================");

        System.out.println();

        System.out.println(
                "Type the following paragraph:");

        System.out.println();

        System.out.println(paragraph);

        System.out.println();

        System.out.println(
                "Start typing below:");

        System.out.println();

        System.out.println(
        Colors.GREEN +
        "\nGet Ready...\n"
        + Colors.RESET);

        for(int i = 3; i >= 1; i--) {

            System.out.println(
                    Colors.YELLOW +
                    i +
                    Colors.RESET);

            Thread.sleep(1000);
        }

        System.out.println(
                Colors.GREEN +
                "GO 🚀" +
                Colors.RESET);

        long startTime =
                System.currentTimeMillis();

        String typedText =
                sc.nextLine();

        long endTime =
                System.currentTimeMillis();

        if(typedText.trim().isEmpty()) {

            System.out.println(
                    "No text entered. Test cancelled.");

            return;
        }

        double timeTaken =
                (endTime - startTime)
                        / 1000.0;

        int wordsTyped =
                typedText.trim()
                        .split("\\s+").length;

        double wpm =
                calculateWPM(
                        wordsTyped,
                        timeTaken);

        double accuracy =
                calculateAccuracy(
                        paragraph,
                        typedText);

        int mistakes =
                countMistakes(
                        paragraph,
                        typedText);

        System.out.println();

        System.out.println(
                "========== RESULT ==========");

        System.out.printf(
                "Time Taken : %.2f seconds%n",
                timeTaken);

        System.out.println(
                "Words Typed: "
                        + wordsTyped);

        System.out.printf(
                "WPM        : %.2f%n",
                wpm);

        System.out.printf(
                "Accuracy   : %.2f%%%n",
                accuracy);

        System.out.println(
                "Mistakes   : "
                        + mistakes);

        saveScore(
                username,
                wpm,
                accuracy);
    }

    public static void saveScore(
            String username,
            double wpm,
            double accuracy)
    {

        try {

            FileWriter writer =
                    new FileWriter(
                            "scores.txt",
                            true);

            writer.write(
                    username +
                    "," +
                    String.format("%.2f", wpm) +
                    "," +
                    String.format("%.2f", accuracy)
                    + "\n");

            writer.close();

        } catch(IOException e) {

            System.out.println(
                    "Error saving score.");
        }
    }

    public static void viewScores() {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    "scores.txt"));

            String line;

            System.out.println(
                    "\n===== SCORE HISTORY =====");

            while((line =
                    reader.readLine()) != null) {

                System.out.println(line);
            }

            reader.close();

        } catch(Exception e) {

            System.out.println(
                    "No score history found.");
        }
    }

    static class LeaderboardEntry {
        String username;
        double wpm;

        LeaderboardEntry(String username, double wpm) {
            this.username = username;
            this.wpm = wpm;
        }
    }

    public static void showLeaderboard() {

        ArrayList<LeaderboardEntry> list = new ArrayList<>();

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    "scores.txt"));

            String line;

            while((line =
                    reader.readLine()) != null) {

                String[] parts = line.split(",");

                if(parts.length >= 2) {

                    String name = parts[0].trim();

                    double wpmValue =
                            Double.parseDouble(
                                    parts[1].trim());

                    list.add(
                            new LeaderboardEntry(
                                    name,
                                    wpmValue));
                }
            }

            reader.close();

        } catch(Exception e) {

            System.out.println(
                    "No score history found.");

            return;
        }

        Collections.sort(list, new Comparator<LeaderboardEntry>() {
            @Override
            public int compare(LeaderboardEntry a, LeaderboardEntry b) {
                return Double.compare(b.wpm, a.wpm);
            }
        });

        System.out.println(
                "\n===== LEADERBOARD =====");

        System.out.println();

        for(int i = 0; i < list.size(); i++) {

            LeaderboardEntry entry = list.get(i);

            System.out.printf(
                    "%d. %-12s %.2f WPM%n",
                    (i + 1),
                    entry.username,
                    entry.wpm);
        }
    }

    public static void loadingAnimation()
            throws InterruptedException {

        System.out.print("Loading");

        for(int i = 0; i < 5; i++) {
            Thread.sleep(400);
            System.out.print(".");
        }

        System.out.println();
    }
}
