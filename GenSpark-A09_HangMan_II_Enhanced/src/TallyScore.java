import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TallyScore {


    public static int totalScore = 0;
    public static int winningScore = 0;
    public static int gameNumber = 0;


    public static double winningChance = 0;

    public static double total = 0;
    public static double replayVal = 0;


    public static int winnerVal = 0;




    public static void displayScores() throws FileNotFoundException {

        String path = "/GenSpark-FullStack/Java-GenSpark/A09_Hangman_2/scores.csv";
        String line = "";

        try {
            BufferedReader buffRead = new BufferedReader(new FileReader(path));

            while((line = buffRead.readLine()) != null) {
                String[] values = line.split(", ");
                System.out.println("Name: " + values[0] + ", Score: " + values[1]);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    public static void totalScore() throws FileNotFoundException {

            winningScore = 0;
            totalScore = 0;
            gameNumber = 0;
            winningChance  = 0;

            total = 0;
            replayVal = 0;

            winnerVal = 0;



        System.out.println(HangmanII_Enhanced.registeredPlayer + ", do you want to see your total score? (y or n)");
            Scanner yesOrNo = new Scanner(System.in);
            String newYesOrNo = yesOrNo.nextLine();

            if (newYesOrNo.charAt(0) == 'y') {


                String path = "/GenSpark-FullStack/Java-GenSpark/A09_Hangman_2/scores.csv";
                String line = "";

                try {
                    BufferedReader br = new BufferedReader(new FileReader(path));


                    while ((line = br.readLine()) != null) {
                        String[] values = line.split(", ");

                        String currentName = values[0];


                        if (currentName.equals(HangmanII_Enhanced.registeredPlayer)) {
                            winningScore = Integer.parseInt(values[1]);
                            totalScore = totalScore + winningScore;

                            gameNumber++;
                        }


                    }

                    double totalScoreValue = totalScore;
                    double gameNumberValue = gameNumber;

                    winningChance = totalScoreValue/gameNumberValue * 100;

                    winnerVal = (int)winningChance;



                    System.out.println("You have won total of " + totalScore + " times in " + gameNumber + " games!");

                    System.out.println("===> Your winning percentage is " + winnerVal + "%.\n");


                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            } else if (newYesOrNo.charAt(0) == 'n') {


        }


    }



}
