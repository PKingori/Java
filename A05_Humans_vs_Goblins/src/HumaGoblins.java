/************************************************************************************************************
 *          Project: GenSpark- Java Bootcamp Training Program
 *          Project #: A05_Human vs Goblins
 *          Abstract: A simple java program to demonstrate the fundamental pillars of Object-Oriented Programming (OOP)
 *          Task: A simple game that creates a battleground in form of a grid where human (you, the player) and
 *                goblin ( the computer) race to destroy each other through collisions. The keys, n,s,e,w are used
 *                to control the player's selection of direction. The 'health' of both contestants are tracked; the
 *                least healthy loses.
 *****************************************************************************************************************/
import java.util.Random;    // the randomizer
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.*;


class HumaGlobins {

    public static char[][] arena;
    public static String playerSelection;
    static ArrayList<Integer> locateHuman = new ArrayList<Integer>();
    static ArrayList<Integer> locateGoblin = new ArrayList<Integer>();
    public static int humanLife = 10;
    public static int goblinLife = 10;

// ****************** Game switch ***********************
    public static void main (String[] args) {
        do {
            startWar();
        } while (playerSelection.charAt(0) == 'y');
    }
// ******************************************************




    public static void startWar() {
        locateHuman = new ArrayList<Integer>();
        locateGoblin = new ArrayList<Integer>();
        humanLife = 15;
        goblinLife = 15;

        out.println("\n**************** HUMAN vs GOBLINS! *******************\n");
        Grid();

        Random randomizer = new Random();
        int humanPosition = randomizer.nextInt(100) + 1;
        makeMove(arena, humanPosition, "human");


        randomizer = new Random();
        int goblinLocation = randomizer.nextInt(100) + 1;
        makeMove(arena, goblinLocation, "goblin");
        render(arena);


        out.println("\nThe fight begins!\n" + "Human(#) versus Goblin(@). May the strong one win!");


        while(true){
            Grid();
            keypad();

            locateHuman = new ArrayList<Integer>();
            locateGoblin = new ArrayList<Integer>();

            humanPosition = locateHuman(humanPosition);

            while (locateHuman.contains(humanPosition) || locateGoblin.contains(humanPosition) ||
                    humanPosition - goblinLocation == 1 || goblinLocation - humanPosition == 1 ||
                    humanPosition - goblinLocation == 10 || goblinLocation - humanPosition == 10) {
                out.println("********* That's a real combat !!! **************");
                int min = 1;
                int max = 10;
                int humanBite = (int) (Math.random()*(max-min)) + min;
                out.println("Human venom: " + humanBite + ".");

                int goblinBite = (int) (Math.random()*(max-min)) + min;
                out.println("Goblin venom: " + goblinBite + ".");


                if (humanBite > goblinBite) {
                    out.println("=> Human won! (Human +1, Goblin -3)");
                    humanLife++;
                    goblinLife -= 3;
                    break;
                } else if (goblinBite > humanBite) {
                    out.println("=> Goblin won! (Goblin +1, Human -3)");
                    goblinLife++;
                    humanLife -= 3;
                    break;
                } else if (humanBite == goblinBite) {
                    out.println("This is a draw! The battle will resume another day.");
                    break;
                }

            }

            makeMove(arena, humanPosition, "human");

            String checkScore = checkWinner();
            if (checkScore.length() > 0) {
                out.println(checkScore);
                break;
            }

            Random random = new Random();
            String stringGolblinmove = "nesw";
            int randomInt = random.nextInt(stringGolblinmove.length());
            char moveGoblin = stringGolblinmove.charAt(randomInt);

            if (moveGoblin == 'n') {
                goblinLocation -= 10;
                if (goblinLocation < 1) goblinLocation += 100;

            } else if (moveGoblin == 's') {
                goblinLocation += 10;
                if (!(goblinLocation <= 100)) goblinLocation -= 100;

            } else if (moveGoblin == 'e') {
                goblinLocation++;
                if (!(goblinLocation <= 100)) goblinLocation -= 100;

            } else if (moveGoblin == 'w') {
                goblinLocation--;
                if (goblinLocation < 1) goblinLocation += 100;
            }

            while (locateHuman.contains(goblinLocation) || locateGoblin.contains(goblinLocation) ||
                    humanPosition - goblinLocation == 1 || goblinLocation - humanPosition == 1 ||
                    humanPosition - goblinLocation == 10 || goblinLocation - humanPosition == 10) {

                out.println("Human and Goblin collided. The combat began!");
                int min = 1;
                int max = 10;
                int randomHumanPower = (int) (Math.random()*(max-min)) + min;
                out.println("Human's life: " + randomHumanPower + ".");

                int randomGoblinPower = (int) (Math.random()*(max-min)) + min;
                out.println("Goblin's life: " + randomGoblinPower + ".");


                if (randomGoblinPower < randomHumanPower) {
                    out.println("=> Human won! (Human +1, Goblin -3)");
                    humanLife = humanLife + 1;
                    goblinLife = goblinLife - 3;
                    break;
                } else if (randomGoblinPower > randomHumanPower) {
                    out.println("=> Goblin won! (Goblin +1, Human -3)");
                    goblinLife = goblinLife + 1;
                    humanLife = humanLife - 3;
                    break;
                } else if (randomHumanPower == randomGoblinPower) {
                    out.println("=> Draw! We will walk away for now. Until we meet again!");
                    break;
                }

            }

            makeMove(arena, goblinLocation, "goblin");
            render(arena);

            checkScore = checkWinner();
            if (checkScore.length() > 0) {
                out.println(checkScore);
                break;
            }

        }

        out.println("Play again? (y or n)");
        Scanner playAgain = new Scanner(in);
        playerSelection = playAgain.nextLine();
    }

    private static void keypad() {
        out.println(
                "\n------------------- Control Keys -------------------------\n" +
                "            n\n" +
                "          w + e    # (Human life left: " + humanLife + ")\n" +
                "            s      @ (Goblin life left: " + goblinLife + ")\n" +
                "----------------------------------------------------------\n");
    }

    private static int locateHuman(int humanPos) {
        try {
            Scanner scan = new Scanner(in);
            out.println("Select your next move (n/s/e/w)");
            char nextHumanPos = scan.next().charAt(0);

            if (nextHumanPos == 'n') {
                humanPos = humanPos - 10;
                if (humanPos < 1) {
                    humanPos = humanPos + 100;

                }
            }

            if (nextHumanPos == 's') {
                humanPos = humanPos + 10;
                if (humanPos > 100) {
                    humanPos = humanPos - 100;

                }

            }

            if (nextHumanPos == 'e') {
                humanPos = humanPos + 1;
                if (humanPos > 100) {
                    humanPos = humanPos - 100;

                }
            }

            if (nextHumanPos == 'w') {
                humanPos = humanPos - 1;
                if (humanPos < 1) {
                    humanPos = humanPos + 100;

                }
            }

        } catch (Exception e) {
            out.println("Please select a one of these directions: n/s/e/w.");
        }
        return humanPos;
    }

    private static void Grid() {
        arena = new char[][]
                {{' ', ' ', ' ', '0', ' ', '1', ' ', '2', ' ', '3', ' ', '4', ' ', '5', ' ', '6', ' ', '7', ' ', '8', ' ', '9'},
                        {'0', ' ', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.'},
                        {'1', ' ', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.'},
                        {'2', ' ', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.'},
                        {'3', ' ', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.'},
                        {'4', ' ', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.'},
                        {'5', ' ', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.'},
                        {'6', ' ', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.'},
                        {'7', ' ', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.'},
                        {'8', ' ', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.'},
                        {'9', ' ', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.', ' ', '.'}};
    }


    public static void render(char[][] area) {

        for (char[] row : area) {
            for (char c : row) {
                out.print(c);
            }
            out.println();
        }
    }



    public static void makeMove(char[][] battleground, int coordinate, String user) {

        char symbol = '#';

        if (user.equals("human")) {
            symbol = '#';
            locateHuman.add(coordinate);
        } else if (user.equals("goblin")) {
            symbol = '@';
            locateGoblin.add(coordinate);
        }


        switch(coordinate) {
            case 1:
                battleground[1][3] = symbol;
                break;
            case 2:
                battleground[1][5] = symbol;
                break;
            case 3:
                battleground[1][7] = symbol;
                break;
            case 4:
                battleground[1][9] = symbol;
                break;
            case 5:
                battleground[1][11] = symbol;
                break;
            case 6:
                battleground[1][13] = symbol;
                break;
            case 7:
                battleground[1][15] = symbol;
                break;
            case 8:
                battleground[1][17] = symbol;
                break;
            case 9:
                battleground[1][19] = symbol;
                break;
            case 10:
                battleground[1][21] = symbol;
                break;

            case 11:
                battleground[2][3] = symbol;
                break;
            case 12:
                battleground[2][5] = symbol;
                break;
            case 13:
                battleground[2][7] = symbol;
                break;
            case 14:
                battleground[2][9] = symbol;
                break;
            case 15:
                battleground[2][11] = symbol;
                break;
            case 16:
                battleground[2][13] = symbol;
                break;
            case 17:
                battleground[2][15] = symbol;
                break;
            case 18:
                battleground[2][17] = symbol;
                break;
            case 19:
                battleground[2][19] = symbol;
                break;
            case 20:
                battleground[2][21] = symbol;
                break;


            case 21:
                battleground[3][3] = symbol;
                break;
            case 22:
                battleground[3][5] = symbol;
                break;
            case 23:
                battleground[3][7] = symbol;
                break;
            case 24:
                battleground[3][9] = symbol;
                break;
            case 25:
                battleground[3][11] = symbol;
                break;
            case 26:
                battleground[3][13] = symbol;
                break;
            case 27:
                battleground[3][15] = symbol;
                break;
            case 28:
                battleground[3][17] = symbol;
                break;
            case 29:
                battleground[3][19] = symbol;
                break;
            case 30:
                battleground[3][21] = symbol;
                break;


            case 31:
                battleground[4][3] = symbol;
                break;
            case 32:
                battleground[4][5] = symbol;
                break;
            case 33:
                battleground[4][7] = symbol;
                break;
            case 34:
                battleground[4][9] = symbol;
                break;
            case 35:
                battleground[4][11] = symbol;
                break;
            case 36:
                battleground[4][13] = symbol;
                break;
            case 37:
                battleground[4][15] = symbol;
                break;
            case 38:
                battleground[4][17] = symbol;
                break;
            case 39:
                battleground[4][19] = symbol;
                break;
            case 40:
                battleground[4][21] = symbol;
                break;


            case 41:
                battleground[5][3] = symbol;
                break;
            case 42:
                battleground[5][5] = symbol;
                break;
            case 43:
                battleground[5][7] = symbol;
                break;
            case 44:
                battleground[5][9] = symbol;
                break;
            case 45:
                battleground[5][11] = symbol;
                break;
            case 46:
                battleground[5][13] = symbol;
                break;
            case 47:
                battleground[5][15] = symbol;
                break;
            case 48:
                battleground[5][17] = symbol;
                break;
            case 49:
                battleground[5][19] = symbol;
                break;
            case 50:
                battleground[5][21] = symbol;
                break;


            case 51:
                battleground[6][3] = symbol;
                break;
            case 52:
                battleground[6][5] = symbol;
                break;
            case 53:
                battleground[6][7] = symbol;
                break;
            case 54:
                battleground[6][9] = symbol;
                break;
            case 55:
                battleground[6][11] = symbol;
                break;
            case 56:
                battleground[6][13] = symbol;
                break;
            case 57:
                battleground[6][15] = symbol;
                break;
            case 58:
                battleground[6][17] = symbol;
                break;
            case 59:
                battleground[6][19] = symbol;
                break;
            case 60:
                battleground[6][21] = symbol;
                break;



            case 61:
                battleground[7][3] = symbol;
                break;
            case 62:
                battleground[7][5] = symbol;
                break;
            case 63:
                battleground[7][7] = symbol;
                break;
            case 64:
                battleground[7][9] = symbol;
                break;
            case 65:
                battleground[7][11] = symbol;
                break;
            case 66:
                battleground[7][13] = symbol;
                break;
            case 67:
                battleground[7][15] = symbol;
                break;
            case 68:
                battleground[7][17] = symbol;
                break;
            case 69:
                battleground[7][19] = symbol;
                break;
            case 70:
                battleground[7][21] = symbol;
                break;



            case 71:
                battleground[8][3] = symbol;
                break;
            case 72:
                battleground[8][5] = symbol;
                break;
            case 73:
                battleground[8][7] = symbol;
                break;
            case 74:
                battleground[8][9] = symbol;
                break;
            case 75:
                battleground[8][11] = symbol;
                break;
            case 76:
                battleground[8][13] = symbol;
                break;
            case 77:
                battleground[8][15] = symbol;
                break;
            case 78:
                battleground[8][17] = symbol;
                break;
            case 79:
                battleground[8][19] = symbol;
                break;
            case 80:
                battleground[8][21] = symbol;
                break;



            case 81:
                battleground[9][3] = symbol;
                break;
            case 82:
                battleground[9][5] = symbol;
                break;
            case 83:
                battleground[9][7] = symbol;
                break;
            case 84:
                battleground[9][9] = symbol;
                break;
            case 85:
                battleground[9][11] = symbol;
                break;
            case 86:
                battleground[9][13] = symbol;
                break;
            case 87:
                battleground[9][15] = symbol;
                break;
            case 88:
                battleground[9][17] = symbol;
                break;
            case 89:
                battleground[9][19] = symbol;
                break;
            case 90:
                battleground[9][21] = symbol;
                break;



            case 91:
                battleground[10][3] = symbol;
                break;
            case 92:
                battleground[10][5] = symbol;
                break;
            case 93:
                battleground[10][7] = symbol;
                break;
            case 94:
                battleground[10][9] = symbol;
                break;
            case 95:
                battleground[10][11] = symbol;
                break;
            case 96:
                battleground[10][13] = symbol;
                break;
            case 97:
                battleground[10][15] = symbol;
                break;
            case 98:
                battleground[10][17] = symbol;
                break;
            case 99:
                battleground[10][19] = symbol;
                break;
            case 100:
                battleground[10][21] = symbol;
                break;


            default:
                break;
        }

    }

    public static String checkWinner() {

        if (humanLife <= 0) {

            return "Human Life: " + humanLife + "\n" +
                    "Goblin Life: " + goblinLife + "\n" +
                    "Goblin destroyed you!\n" + "************ Game Over ***************";

        } else if (goblinLife <= 0) {

            return "Human Life: " + humanLife + "\n" +
                    "Goblin Life: " + goblinLife + "\n" +
                    "You destroyed Goblin!\n" + "************ Game Over **************";

        }

        return "";
    }




}