/************************************************************************************************************
 *          Project: GenSpark- Java Bootcamp Training Program
 *          Project #: A04_PlayXO
 *          Abstract: A simple introductory Java class to demonstrate the use of encapsulation
 *          Task: To write a simple Noughts and Crosses (TicTacToe or X & O) game that prompts the play to select a
 *                location on a 3 X 3 grid and the computer randomly takes the alternat
 *          Behaviour: The program displays a grid and prompts user to enter a number between 1-9 that matches with
 *                the 2D coordinate of the placeholder the player wants to make a move to. The program uses only
 *                a randomizer to select a slot but does not apply any AI logic.
 ****************************************************************************************************************/

import java.util.Random;            // CPU Randomizer
import java.util.Scanner;

public class PlayXO {
    public static void main(String[] args){

        Scanner scanner = new Scanner((System.in));

        char[][] grid = {{ ' ',' ', ' '},
                         { ' ',' ', ' '},
                         { ' ',' ', ' '}};
        render(grid);

        while (true) {

            playerMove(grid, scanner);
            if (isGameOver(grid)){
                break;
            };
            render(grid);

            computerPlayTurn(grid);
            render(grid);
        }
        scanner.close();
    }

    private static boolean isGameOver(char[][] grid) {

        // Validating who won
        if (whoWon(grid, 'X')) {
            render(grid);
            // Player wins
            System.out.println(" ***** CONGRATULATIONS!! YOU WON! ************");
            return true;
        }
         if (whoWon(grid, 'O')) {
            render(grid);
            // Player wins
            System.out.println(" ***** YOU LOST! I WON! ************");
            return true;
        }

        for (int i = 0; i< grid.length; i++){
            for (int j = 0; j < grid[i].length; j++){
                if (grid[i][j] == ' '){
                    return false;
                }
            }
        }
        render(grid);
        System.out.println(" **** It's a draw! GAME OVER **********");
    return true;
    }
    private static boolean whoWon(char[][] grid, char symbol) {
        if ( // Check rows
            (grid[0][0] == symbol && grid[0][1] == symbol && grid[0][2] == symbol) ||
            (grid[1][0] == symbol && grid[1][1] == symbol && grid[1][2] == symbol) ||
            (grid[2][0] == symbol && grid[2][1] == symbol && grid[2][2] == symbol) ||
             // Check columns
            (grid[0][0] == symbol && grid[1][0] == symbol && grid[2][0] == symbol) ||
            (grid[0][1] == symbol && grid[1][1] == symbol && grid[2][1] == symbol) ||
            (grid[0][2] == symbol && grid[1][2] == symbol && grid[2][2] == symbol) ||
            // Check diagonals
            (grid[0][0] == symbol && grid[1][1] == symbol && grid[2][2] == symbol) ||
            (grid[0][2] == symbol && grid[1][1] == symbol && grid[2][0] == symbol) ){

            return true;
        }
        return false;
    }

    private static void computerPlayTurn(char[][] grid) {
        // Make computer make a move
        Random randomize = new Random();
        int compuMove;
        while (true){
            compuMove = randomize.nextInt(9) + 1;
            if (isMoveValid(grid, Integer.toString(compuMove))){
                break;
            }
        }
        System.out.println(" Computer made a move:" + compuMove);

        makeMove(grid, Integer.toString(compuMove), 'O');
    }

    private static boolean isMoveValid (char[][] grid, String coordinate){
        // stop 15:48
        switch (coordinate){
            case "1":
                return (grid[0][0] == ' ');
            case "2":
                return (grid[0][1] == ' ');
            case "3":
                return (grid[0][2] == ' ');
            case "4":
                return (grid[1][0] == ' ');
            case "5":
                return (grid[1][1] == ' ');
            case "6":
                return (grid[1][2] == ' ');
            case "7":
                return (grid[2][0] == ' ');
            case "8":
                return (grid[2][1] == ' ');
            case "9":
                return (grid[2][2] == ' ');
            default:
                return false;
        }

    }

    private static void playerMove(char[][] grid, Scanner scanner) {
        String playerInput;
        while (true){
        System.out.println("Please make a move. Select 1 through 9");
        playerInput = scanner.nextLine();
        if (isMoveValid(grid, playerInput)){
            break;
            } else {
            System.out.println("Invalid move.");
        }
        }

        makeMove(grid, playerInput, 'X');
    }

    private static void makeMove(char[][] grid, String coordinate, char symbol) {
        switch (coordinate){
            case "1":
                grid[0][0] = symbol;
                break;
            case "2":
                grid[0][1] = symbol;
                break;
            case "3":
                grid[0][2] = symbol;
                break;
            case "4":
                grid[1][0] = symbol;
                break;
            case "5":
                grid[1][1] = symbol;
                break;
            case "6":
                grid[1][2] = symbol;
                break;
            case "7":
                grid[2][0] = symbol;
                break;
            case "8":
                grid[2][1] = symbol;
                break;
            case "9":
                grid[2][2] = symbol;
                break;
            default:
                System.out.println("Invalid entry");
        }
    }

    private static void render(char[][] grid) {
        System.out.println(grid[0][0] + "|" + grid[0][1] + "|" + grid[0][2]);
        System.out.println("-+-+-");
        System.out.println(grid[1][0] + "|" + grid[1][1] + "|" + grid[1][2]);
        System.out.println("-+-+-");
        System.out.println(grid[2][0] + "|" + grid[2][1] + "|" + grid[2][2]);
    }
}


