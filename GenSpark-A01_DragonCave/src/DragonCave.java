import java.util.Scanner;

/************************************************************************************************************
 *          Project: GenSpark- Java Bootcamp Training Program
 *          Project #: A01_DragonCave
 *          Abstract: A simple introductory Java class to demonstrate the use of predicate logic on strings
 *          Task: A simple game asking the user to select a cave. One cave has a deadly dragon and
 *                the other has a friendly one. The choice determines life or death.
 *          Behaviour: The program allows user to enter 1 or 2 to enter either caves. If user enters
 *                 0, the programs exits. Also, if users enters anything else, the program alerts
 *                 the user that an invalid selection was made. Program exits upon execution of any
 *                 choice made.
 *          Programmer: Patrick Kingori, 08/18/2021.
 *          Github: https://github.com/pkingori/Java-GenSpark-Projects/blob/main/GenSpark-A01_DragonCave/src/DragonCave.java
 ****************************************************************************************************************/


public class DragonCave {
    public static void main(String[] args) {
        // Printing the game instructions
        System.out.println("\n********************Welcome to Dragons Cave!************************\n");
        System.out.println("READ THE FOLLOWING INSTRUCTIONS:\n");
        System.out.println("You are in a land full of dragons." +
                "In front of you, there are two caves.\n" +
                "In one cave, the dragon is friendly and will share its treasure with you.\n" +
                "In the other cave, the dragon is mean, angry,and hungry! Ready to pounce on you.\n" +
                "Which cave will you select to go into?\n" +
                "(Please select cave 1 or cave 2.)\n" +
                "(Press 0 to exit)\n");

        // Listening for user input
        System.out.println("Please make selection now (choose wisely): ");
        Scanner listenCaveChoice = new Scanner(System.in);          // Create listener
        String selection = listenCaveChoice.nextLine();             // Prompt for user selection
        System.out.println("\nYou have selected: " + selection);      // echo selection user made

        // Determine whether selection was red or blue pill or surrender (live, die or exit)
        // MENU:
        // Selection 1 = red pill (you die between the jaws of the mean hungry dragon)
        // Selection 2 = blue pill ( you live with the friendly dragon)
        // Selection 0 = You are a quitter
        // Any other selection = invalid choice

        // Selection 1
        if (selection.equals("1")) {
            System.out.println("Oh no! that was a bad choice; You chose the red pill.\n" +
                    "The dragon just pounced on you and turned you into minced meat.\n" +
                    "But there's hope! You have another life to live.\nMake some good choices next time.\n");
        }

        // Selection 2
        else if (selection.equals("2")) {
            System.out.println("Congratulations! You chose wisely!\n" +
                    " Welcome to a life full of shared dragon treasures.\n ");
        }
        // Selection 0
        else if (selection.equals("0")) {
            System.out.println("Don't be a quitter!\n" +
                    "Success in life comes with making hard choices. Try again!");
        }
        // Invalid selection
            // Checks whether user entered anything else besides 1, 2 or 0
        else if (!selection.equals("1") || !selection.equals("2") || !selection.equals("0")) {


            System.out.println("ATTENTION! You have made an invalid selection.\n" +
                    "You can only select 1, 2 or 0. Start again\n" +
                    "Remember, you can only win battles that you are in.\n" +
                    "Enter the battle again to choose your cave\n");

        }
        System.out.println("\n*******************GAME OVER!*********************\n");
    }

}

