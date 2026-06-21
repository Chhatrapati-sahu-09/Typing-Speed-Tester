import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;
import com.github.lalyos.jfiglet.FigletFont;

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

        try {
            System.out.println(
                    colorize(
                            FigletFont.convertOneLine("Speed Tester"),
                            GREEN_TEXT(),
                            BOLD()
                    )
            );
        } catch (Exception e) {
            System.out.println("Speed Tester");
        }

        System.out.print("Enter Username: ");
        username = sc.nextLine();

        loadingAnimation();

        while(true) {

            System.out.println(
            colorize("""
╔══════════════════════════════╗
║         MAIN MENU           ║
╠══════════════════════════════╣
║ 1. Start Test               ║
║ 2. View Scores              ║
║ 3. Leaderboard              ║
║ 4. Exit                     ║
╚══════════════════════════════╝
""",
            CYAN_TEXT())
            );

            System.out.print("Choose: ");

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
        colorize("""
┌────────────────────────────────────┐
│          TYPE THIS TEXT            │
└────────────────────────────────────┘
""",
        MAGENTA_TEXT())
        );

        System.out.println(
        colorize(
                paragraph,
                WHITE_TEXT())
        );

        System.out.println();

        System.out.println(
                "Start typing below:");

        System.out.println();

        System.out.println(
        colorize(
                "\nGet Ready...\n",
                GREEN_TEXT(),
                BOLD()
        ));

        for(int i = 3; i >= 1; i--) {

            System.out.println(
                    colorize(
                            String.valueOf(i),
                            YELLOW_TEXT(),
                            BOLD()));

            Thread.sleep(1000);
        }

        System.out.println(
                colorize(
                        "GO 🚀",
                        GREEN_TEXT(),
                        BOLD()));

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
        colorize("""
╔════════════════════════════╗
║       TEST RESULTS        ║
╚════════════════════════════╝
""",
        GREEN_TEXT(),
        BOLD())
        );

        System.out.println(
        colorize(
                "⚡ WPM       : " + String.format("%.1f", wpm),
                CYAN_TEXT()));

        System.out.println(
        colorize(
                "🎯 Accuracy  : " +
                        String.format("%.1f", accuracy) + "%",
                GREEN_TEXT()));

        System.out.println(
        colorize(
                "❌ Mistakes  : " +
                        mistakes,
                RED_TEXT()));

        if(wpm >= 70){

            System.out.println(
            colorize("""
🏆 LEGEND TYPIST 🏆
""",
            YELLOW_TEXT(),
            BOLD()));

        }
        else if(wpm >= 50){

            System.out.println(
            colorize("""
🥇 PRO TYPIST
""",
            GREEN_TEXT()));

        }
        else if(wpm >= 30){

            System.out.println(
            colorize("""
🥈 GOOD JOB
""",
            CYAN_TEXT()));

        }

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
        colorize("""
╔════════════════════════════════╗
║          LEADERBOARD          ║
╚════════════════════════════════╝
""",
        BLUE_TEXT(),
        BOLD()));

        for(int i = 0; i < list.size(); i++) {

            LeaderboardEntry entry = list.get(i);
            String rankPrefix;
            if (i == 0) rankPrefix = "🥇 ";
            else if (i == 1) rankPrefix = "🥈 ";
            else if (i == 2) rankPrefix = "🥉 ";
            else rankPrefix = (i + 1) + ". ";

            System.out.println(
            colorize(
                    String.format("%s%-12s %.1f WPM",
                            rankPrefix,
                            entry.username,
                            entry.wpm),
                    CYAN_TEXT()
            ));
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
