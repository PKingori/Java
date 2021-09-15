/************************************************************************************************************
 *          Project: GenSpark- Java Bootcamp Training Program
 *          Project #: A09_Hangman_II_ENHANCED
 *          Abstract: A Java program that demonstrates familiarity with the use of functional programming and
 *                    handling of IO files. The program also demonstrates application of JavaFX.
 *          Task: To develop a Hangman game that employs functional programming features to replace for loops
 *          Behaviour: The program displays a PNG file for each stage og the hangman execution. It also tallies
 *                   and stores the records of the scores of the player as a CSV file.
 *          Programmer: Patrick Kingori.
 ****************************************************************************************************************/

import java.util.stream.Collectors;
import java.util.*;
import javax.swing.*;
import java.io.*;


public class HangmanII_Enhanced {

    public static String[] hangmanCountries =
            { "china", "iran","vietnam","iraq","egypt","usa","japan","pakistan","singapore",
              "somalia","sudan","belarus","yemen","afghanistan","botswana","taiwan","thailand","korea"};


    public static String country = hangmanCountries[(int) (Math.random() * hangmanCountries.length)];
    public static CorrectGuessesMade correctCountryGuesses = new CorrectGuessesMade(country);
    public static String drawErrorLine = new String(new char[country.length()]).replace("\0", "-");
    public static int counter = 0;
//    public static String newUnderline = "";
    public static char letterChoiceMade;
    public static String missedCountryLetter = "";
    public static String guessLetter;
    public static String echoMessage;
    public static int countCorrect;
    public static int echoCount;
    public static int tallyLiner;
//    public static int getCountCorrect = 0;
    public static String registeredPlayer;


    public static void main(String[] args) throws IOException {
            do {
                printHeader();
                readPlayerName();

                try{
                FileWriter registeredPlayerName = new FileWriter("scores.csv",true);
                BufferedWriter out = new BufferedWriter(registeredPlayerName);
                out.write(registeredPlayer + ", ");
                out.close();
                System.out.println("\nThank you, " + registeredPlayer + ". Your name is registered.\n");
            }catch (Exception e){
                System.err.println("An error occurred while writing to this file! " +
                        e.getMessage());
            }


            counter = 0;
            missedCountryLetter = "";


            String newCountry = hangmanCountries[(int) (Math.random() * hangmanCountries.length)];
            country = newCountry;
            correctCountryGuesses = new CorrectGuessesMade(country);


            System.out.println("(The country that still practises capital punishment is: " + country + ")\n");

// Application of functional programming
            drawErrorLine = Arrays.stream(Arrays.stream(country.split(""))
                    .map(s -> "-")
                    .toArray(String[]::new))
                    .collect(Collectors.joining());

            gameOver();
            TallyScore.totalScore();

            doesPlayerWantToPlayAgain();

                if (doesntWantToPlayAgain()) break;

            } while (letterChoiceMade == 'y');

    }

    private static boolean doesntWantToPlayAgain() {
        if (letterChoiceMade == 'n') {
        System.out.println("***********************  H A N G M A N   II   G A M E   O V E R ! ***************");
            return true;
    }
        return false;
    }

    private static void doesPlayerWantToPlayAgain() {
        System.out.println("Play again? (y or n)");
        Scanner playAgain = new Scanner(System.in);
        letterChoiceMade = playAgain.next().charAt(0);
    }

    private static void printHeader() {
        System.out.print("\n\n******************* WELCOME TO HANGMAN II: THE ENHANCED COUNTRY HANGMAN *******************\n");
        System.out.print("INSTRUCTIONS:\n");
        System.out.print("------------- \n");
        System.out.print("You are required to spell a country that still practises capital punishment\n");


    }

    private static void readPlayerName() {
        System.out.print("But first, please register your name: ");
        Scanner scanner = new Scanner(System.in);
        registeredPlayer = scanner.nextLine();
    }


    private static void gameOver() {

        Scanner sc = new Scanner(System.in);

        do {

            hangmanImage();
            int attemptsRemaining = 7 - counter;
            System.out.println("Attempts left: " + attemptsRemaining + " Attempts Remaining.");


            try {
                System.out.println("Guess a letter of the country.");
                System.out.println(drawErrorLine);
                guessLetter = sc.next();

                correctCountryGuesses.anotherGuessedLetter(guessLetter);

                countryHangman(guessLetter);
            } catch (Exception e) {
                System.out.println("Please make a single letter guess.");
            }


            repeatMissedLetters(guessLetter);
            repeatMessage();
            wrongGuess(guessLetter);
            Liner(guessLetter);
            echoLineMessage();

        } while (counter < 7 && drawErrorLine.contains("-"));
    }





    public static void countryHangman(String guess) {
        String newLine = "";


// Application of functional Programming
        newLine = Arrays.stream(Arrays.stream(country.split(""))
                .map(s -> {
                    if (correctCountryGuesses.wasLetterValid(s)) {
                        return s;

                    } else {
                        return "-";
                    }
                }).toArray(String[]::new))
                .collect(Collectors.joining());




        if (drawErrorLine.equals(newLine)) {
            counter++;
            hangmanImage();
        } else {
            drawErrorLine = newLine;
        }

        if (drawErrorLine.equals(country)) {
            System.out.println("You got it! The country is " + "\"" + country + "\"" + "! Congratulations!");


            int score = 1;

            try{
                FileWriter recordScore = new FileWriter("scores.csv",true);
                BufferedWriter out = new BufferedWriter(recordScore);
                out.write(score + "\n");
                out.close();
                System.out.println("\nYour score has been registered.\n");
            }catch (Exception e){
                System.err.println("There was an error writing to the file: " +
                        e.getMessage());
            }

        }
    }



    public static void wrongGuess(String guess) {

        int i;
        int countCorrect;
        countCorrect = 0;


        if (correctCountryGuesses.wasLetterValid(guess)) {
            countCorrect = 1;

        }

        if (countCorrect == 0) {
            if (echoCount == 0 ) missedCountryLetter = missedCountryLetter + guess.charAt(0);
            else {
                echoMessage = "Error: That's a repeated guess. Please try again.\n";
            };
        }

    }

    public static void Liner(String guess) {

        tallyLiner = 0;

        int i;


        for (i = 0; i < drawErrorLine.length(); i++) {
            if (drawErrorLine.charAt(i) == guess.charAt(0))
                tallyLiner++;
        }

    }
    public static void echoLineMessage() {

        if (tallyLiner > 0) {
            echoMessage = "You have already guessed that letter. Choose again.\n";

        }

    }


    public static void repeatMissedLetters (String guess) {
        int i;
        echoCount = 0;
        echoMessage = "";


        if (missedCountryLetter.contains(guess)) {

            echoCount = 1;

        }
    }


    public static void repeatMessage() {
        echoMessage = "";

        if (echoCount == 0) {
            echoMessage = "";
        } else { }

    }




    public static <StackPane, Scene> void hangmanImage() {
        if (counter == 0) {


            String fileName = "hangmanPictures/0.PNG";
            hangmanStage(fileName);

            System.out.println("Missed letters" + ": " + missedCountryLetter + "\n");

        }
        if (counter == 1) {


            String fileName = "hangmanPictures/1.PNG";
            hangmanStage(fileName);


            System.out.println("Missed letters" + ": " + missedCountryLetter + "\n" + echoMessage);
        }
        if (counter == 2) {

            String fileName = "hangmanPictures/2.PNG";
            hangmanStage(fileName);

            System.out.println("Missed letters" + ": " + missedCountryLetter + "\n" + echoMessage);

        }
        if (counter == 3) {

            String fileName = "hangmanPictures/3.PNG";
            hangmanStage(fileName);

            System.out.println("Missed letters" + ": " + missedCountryLetter + "\n" + echoMessage);

        }
        if (counter == 4) {

            String fileName = "hangmanPictures/4.PNG";
            hangmanStage(fileName);

            System.out.println("Missed letters" + ": " + missedCountryLetter + "\n" + echoMessage);

        }
        if (counter == 5) {

            String fileName = "hangmanPictures/5.PNG";
            hangmanStage(fileName);

            System.out.println("Missed letters" + ": " + missedCountryLetter + "\n" + echoMessage);

        }
        if (counter == 6) {

            String fileName = "hangmanPictures/6.PNG";
            hangmanStage(fileName);

            System.out.println("Missed letters" + ": " + missedCountryLetter + "\n" + echoMessage);

        }
        if (counter == 7) {

            String fileName = "hangmanPictures/7.PNG";
            hangmanStage(fileName);

            System.out.println("Game Over! The country was " + "\"" + country + "\"" + "!");

            int score = 0;

            try{
                FileWriter recordScore = new FileWriter("scores.csv",true);
                BufferedWriter out = new BufferedWriter(recordScore);
                out.write(score + "\n");
                out.close();
                System.out.println("\nYour score was successfully recorded.\n");
            }catch (Exception e){
                System.err.println("ATTENTION: There was an error while writing to file: " +
                        e.getMessage());
            }

        }
    }

    private static void hangmanStage(String fileName) {
        ImageIcon icon = new ImageIcon(fileName);
        JLabel label = new JLabel(icon);
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new JScrollPane(label));
        f.setSize(600, 600);
        f.setLocation(300, 300);
        f.setVisible(true);
    }


}





