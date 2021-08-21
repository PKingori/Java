/************************************************************************************************************
 *          Project: GenSpark- Java Bootcamp Training Program
 *          Project #: A02_Guess_The_Number
 *          Abstract: A simple introductory Java class to demonstrate the use of loop, conditional and predicate logic
 *          Task: A simple game asking the user to guess a number between 1 and 20 that the computer "thought" of.
 *              The user is allowed only 6 attempts. The user is asked whether to play again at the end.
 *          Behaviour: The program asks the user's name at first and recalls it upon subsequent replays of the game.
 *                     It accepts the user's guesses and counts down the remaining trials. It messages the user whether
 *                     their guess was more or less than the random number it created (that falls between 1 and 20).
 *                     If the user guesses correctly or if they have exhausted the 6 trials without getting the answer,
 *                     it prompts the user to play again and replays a new game if and only if 'Y' or 'y' are pressed.
 *                     If the user exhausts the 6 trials, the program also reveals to the user the correct number.
 *
 *          Programmer: Patrick Kingori
 *          Github: https://github.com/pkingori/Java-GenSpark-Projects/blob/main/GenSpark-A02_Guess_The_Number/src/Udemy/JavaMasterClass/Play.java
 ****************************************************************************************************************/
package Udemy.JavaMasterClass;
import java.util.Scanner;

// Create the Play loop which controls the iteration of the game when user is prompted to play again
public class Play {
    public static void main(String[] args) {

        // Listening to the user's name
        System.out.println("\n************************** Welcome to Compuguess **********************\n");
        System.out.println("Please enter your name:");
        Scanner getName = new Scanner(System.in);           //set listener
        String userName = getName.nextLine();               // Create and store username


        // Let's use the user's input of their name as their consent to play and thus begin the do-while loop to play
        String playAgain = "Y";                             // we set the playAgain string to "Y"

        do {                                                // This is the do that actually iterates the game


        System.out.println("Welcome, " + userName + "!\n");
        System.out.println("Here are some instructions:\n" +
                "I'll think of a number between 1 and 20. You will try to guess it.\n" +
                "You only have 6 attempts to guess it right. Good luck!\n");
        // Generating the number between 1 and 20
        // Declare the MinMax
        int Min = 1;        // Declare the minimum value of 1
        int Max = 20;       // Declare the maximum value of 20
        int thoughtNum = (int) (Math.random()*(Max-Min+1)+Min);     // The randomizer
        //        System.out.println(thoughtNum);                  // tester (programmer's switch button)

        // Beginning the for loop to count the number of guess attempts
    for(int guessAttempt = 1; guessAttempt <= 6; guessAttempt++){
        System.out.println("\nAttempt No.: "+guessAttempt+". Please guess a number between "+Min+
                " and "+ Max + ". Number of trials left = " + (6-guessAttempt));

        // Listening to the user's choice
        Scanner in = new Scanner(System.in);        // Set listener
        int guessedNum = in.nextInt();              // Listen to the choice and store it

        // Check if guessed number matches thought of number
        if(guessedNum == thoughtNum){
            System.out.println("Wow! Congratulations! "+userName + ", good job, you guessed my number "+
                    "in only " + guessAttempt + " attempt(s)!" );
            guessAttempt = 6;                                           // Terminate the guess attempts
        }
        // Check if guessed number is less than thought of number
        else if (guessedNum < thoughtNum){
            System.out.println("Oops! Nice try, but your number was LESS than the one I thought of. Try again.");
        }

        // else the guessed number is greater than thought of number
        else {
            System.out.println("Oops! Nice try, but your number was MORE than the one I thought of. Try again.");
        }

        // Announce the answer to the users who fail to guess correctly
        if (guessedNum != thoughtNum && guessAttempt >= 6) {
            System.out.println("\n\n ************ The number was: " + thoughtNum + " ****************");
        }

        }

        // Prompt users to select Y if they want to play again or N if not. This is exclusive
        System.out.println(" \nDo you want to play again? [Please select Y or N]");

        // Listen for user selection to play again
        Scanner getPlayResponse = new Scanner(System.in);           // set listener
        playAgain = getPlayResponse.nextLine();              // store play again choice in playAgain

        }       // end of do (playAgain)
        while (playAgain.equals("Y") || playAgain.equals("y"));      // Set play again iff user enters Y or y
        // end game if Y is not entered
        System.out.println("****************** GAME OVER ! *****************");

    }       // end of main
}       // end of class Play







