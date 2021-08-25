/*******************************************************************************************************************
 *          Project: GenSpark- Java Bootcamp Training Program
 *          Project #: A03_Hangman (Capital Punishment)
 *          Abstract: A simple hangman game that challenges the user to guess a country that continues to execute or hang
 *                    criminals or practise capital punishment.
 *          Task: To create a Java Program that gives a user 6 trials to guess a letter from a randomly selected word
 *                and "executes"  them as hangman for missing all 6 trials.
 *          Behaviour: The program prompts the user to guess a country that still practises capital punishment today
 *                     Upon successful trials, the programs iterates to prompt them to enter additional spellings
 *                     The user is alerted whenever there are repeated letters entered.
 *
 *          Programmer: Patrick Kingori
 *          Github: https://github.com/pkingori/Java-GenSpark/blob/main/GenSpark-A03-Hangman/src/hangman.java
 ****************************************************************************************************************/
import java.util.Scanner;


public class hangman {
    public static String[] countries = {
           // A list of some countries that still practise capital punishment. Real hangman countries

            "china",
            "iran",
            "vietnam",
            "iraq",
            "egypt",
            "usa",
            "japan",
            "pakistan",
            "singapore",
            "somalia",
            "sudan",
            "belarus",
            "yemen",
            "afghanistan",
            "botswana",
            "taiwan",
            "thailand",
            "korea",};

    public static String country = countries[(int) (Math.random() * countries.length)];

    public static String underline = new String(new char[country.length()]).replace("\0", "-");
    public static int count = 0;
    public static Scanner sc;
//    public static int countCorrect;
    public static int Repeater;
    public static int countUnderlineRepeat;
    public static String underscore = "";
    public static char userSelection;
    public static String wrong = "";
    public static String guess;
    public static String echo;


    public static void main(String[] args) {

        do {
            count = 0;
            wrong = "";

            String newCountry = countries[(int) (Math.random() * countries.length)];
            country = newCountry;

//            System.out.println(country);           // Programmer's tester- Enable or disable country to be seen

            underscore = "";
            for (int i = 0; i < country.length(); i++) {
                underscore += "-";
            }
            underline = underscore;

            gameCount();

            System.out.println("Wanna guess another hangman country? (y or n)");
            Scanner playAgain = new Scanner(System.in);
            userSelection = playAgain.next().charAt(0);

        } while (userSelection == 'y');

    }


    private static void gameCount() {

        Scanner sc = new Scanner(System.in);


        do {


            hangmanImage();
            int attemptsRemaining = 7 - count;
            System.out.println("You've got " + attemptsRemaining + " Attempts Remaining.");


            try {
                System.out.println("Guess a letter.");
                System.out.println(underline);
                guess = sc.next();
                hang(guess);
            } catch (Exception e) {
                System.out.println("Please enter a single letter.");
            }

            echoWrongLetters(guess);
            repeatMessage();
            wrongGuess(guess);
            underlineCheck(guess);
            underlineCheckMessage();

        } while (count < 7 && underline.contains("-"));
    }


    public static void hang(String guess) {
        String newUnderline = "";

        for (int i = 0; i < country.length(); i++) {
            if (country.charAt(i) == guess.charAt(0)) {
                newUnderline += guess.charAt(0);
            } else if (underline.charAt(i) != '-') {
                newUnderline += country.charAt(i);
            } else {
                newUnderline += "-";
            }
        }

        for (int i = 0; i < country.length(); i++) {
        }


        if (underline.equals(newUnderline)) {
            count++;
            hangmanImage();
        } else {
            underline = newUnderline;
        }

        if (underline.equals(country)) {
            System.out.println("Yes! you got it! Sadly enough, " + "\"" + country + "\"" + " still practises hangman for real! You have won!");

        }



    }


    public static void wrongGuess(String guess) {

        int i;
        int countCorrect;
        countCorrect = 0;

        for (i = 0; i < country.length(); i++) {
            if (country.charAt(i) == guess.charAt(0)) {
                countCorrect++;
                echo = "";
            }
        }

        if (countCorrect == 0) {
            if (Repeater == 0 ) {
                wrong = wrong + guess.charAt(0);
            } else {
                echo = "You have already guessed that letter. Choose again.\n";
            };
        }


    }

    public static void underlineCheck (String guess) {

        countUnderlineRepeat = 0;

        int i;

        for (i = 0; i < underline.length(); i++) {
            if (underline.charAt(i) == guess.charAt(0))
                countUnderlineRepeat++;
        }
    }

    public static void underlineCheckMessage () {

        if (countUnderlineRepeat > 0) {
            echo = "You have already guessed that letter. Choose again.\n";

        }

    }


    public static void echoWrongLetters(String guess) {
        int i;
        Repeater = 0;
        echo = "";

        for (i = 0; i < wrong.length(); i++) {
            if (wrong.charAt(i) == guess.charAt(0))
                Repeater++;
        }
    }


    public static void repeatMessage() {
        echo = "";

        if (Repeater == 0) {
            echo = "";
        } else { }

    }




    public static void hangmanImage() {
        if (count == 0) {

            System.out.println("\n**************WELCOME TO THE  REAL HANGMAN COUNTRY GAME  ***************************");
            System.out.println("Guess a country that still practises the real hangman (capital punishment) today.");
            System.out.println("Please enter all letters in small caps only.");
            System.out.println("   +----+");
            System.out.println("       |");
            System.out.println("       |");
            System.out.println("       |");
            System.out.println("       |");
            System.out.println("      ===");
            System.out.println("Begin your trial. Good luck!" + ": " + wrong + "\n");

        }
        if (count == 1) {
            System.out.println("   +----+");
            System.out.println("   |   |");
            System.out.println("       |");
            System.out.println("       |");
            System.out.println("       |");
            System.out.println("      ===");
            System.out.println("Missed letters" + ": " + wrong + "\n" + echo);
        }
        if (count == 2) {
            System.out.println("   +----+");
            System.out.println("   |   |");
            System.out.println("   O   |");
            System.out.println("       |");
            System.out.println("       |");
            System.out.println("      ===");
            System.out.println("Missed letters" + ": " + wrong + "\n" + echo);

        }
        if (count == 3) {
            System.out.println("   +----+");
            System.out.println("   |   |");
            System.out.println("   O   |");
            System.out.println("   |   |");
            System.out.println("       |");
            System.out.println("      ===");
            System.out.println("Missed letters" + ": " + wrong + "\n" + echo);

        }
        if (count == 4) {
            System.out.println("   +----+");
            System.out.println("   |   |");
            System.out.println("   O   |");
            System.out.println("  /|   |");
            System.out.println("       |");
            System.out.println("      ===");
            System.out.println("Missed letters" + ": " + wrong + "\n" + echo);

        }
        if (count == 5) {
            System.out.println("   +----+");
            System.out.println("   |   |");
            System.out.println("   O   |");
            System.out.println("  /|\\  |");
            System.out.println("       |");
            System.out.println("      ===");
            System.out.println("Missed letters" + ": " + wrong + "\n" + echo);

        }
        if (count == 6) {
            System.out.println("   +----+");
            System.out.println("   |   |");
            System.out.println("   O   |");
            System.out.println("  /|\\  |");
            System.out.println("  /    |");
            System.out.println("      ===");
            System.out.println("oops! wrong choice" + ": " + wrong + "\n" + echo);

        }
        if (count == 7) {
            System.out.println("   +----+");
            System.out.println("   |   |");
            System.out.println("   O   |");
            System.out.println("  /|\\  |");
            System.out.println("  / \\  |");
            System.out.println("      ===");
            System.out.println("Nice try! The hangman country you missed is " + "\"" + country + "\"" + "!");

        }
    }



}
