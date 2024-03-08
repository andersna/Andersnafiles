import java.util.Random;
import java.util.Scanner;

public class Wordle {
    private String guess = "";
    private String correct;
    private final String BG_GREEN = "\u001b[42m";
    private final String BG_YELLOW = "\u001b[43m";
    private final String RESET = "\u001b[0m";
    private int guessCounter = 0;
    Scanner sc = new Scanner(System.in);

    WordleOrd ord = new WordleOrd();
    boolean isInArray = false;
    private String wrongWord = "";

    private void setword() {
        correct = ord.randomOrd();
    }

    public void guessComparison() {
        for (String word : ord.wordleOrdbog()) {
            if (word.equals(guess)) {
                isInArray = true;
                break;
            }
        }
        if (guess.length() != 5) {
            System.out.println("Ikke 5 bogstaver prøv igen");
            guessCounter--;
        } else if (isInArray == false) {
            System.out.println("Ordet er ikke i ordbogen");
            guessCounter--;
        } else {
            for (int i = 0; i < 5; i++) {
                if (guess.substring(i, i + 1).equals(correct.substring(i, i + 1))) {
                    System.out.print(BG_GREEN + guess.substring(i, i + 1) + RESET);
                } else if (correct.indexOf(guess.substring(i, i + 1)) > -1) {
                    System.out.print(BG_YELLOW + guess.substring(i, i + 1) + RESET);
                } else {
                    System.out.print(guess.substring(i, i + 1));
                    wrongWord +=guess.substring(i,i+1);
                }
            }
        }
    }

    public void play() {
        System.out.println("Wørdle");
        setword();
        while (guessCounter <= 6) {
            System.out.println("Bogstaver der ikke indgår: " + wrongWord);
            System.out.print("Tag et gæt > ");
            guess = sc.nextLine().toUpperCase();
            guessComparison();
            guessCounter++;
            isInArray = false;
            System.out.println();
            if(guess.equals(correct)){
                System.out.println("du har vundet");
                System.exit(0);
            }
        }
        if (guess != correct) {
            System.out.println("Du har tabt, det korrekte ord er " + correct + ".");
        } else {
            System.out.println("Du vinder!");
        }
    }
}