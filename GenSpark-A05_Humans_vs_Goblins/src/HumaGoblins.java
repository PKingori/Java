/************************************************************************************************************
 *          Project: GenSpark- Java Bootcamp Training Program
 *          Project #: A05_Human vs Goblins
 *          Abstract: A simple java program to demonstrate the fundamental pillars of Object-Oriented Programming (OOP)
 *          Task: A simple game that creates a battleground in form of a grid where human (you, the player) and
 *                goblin ( the computer) race to destroy each other through collisions. The keys, n,s,e,w are used
 *                to control the player's selection of direction. The 'health' of both contestants are tracked; the
 *                least healthy loses.
 *****************************************************************************************************************/
import java.util.Arrays;
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


        out.println("\nThe fight begins!\n" + "You are Human(▄) versus I am Goblin(▓). May the strong one win!");


        while(true){
            Grid();
            keypad();

            locateHuman = new ArrayList<Integer>();
            locateGoblin = new ArrayList<Integer>();

            humanPosition = locateHuman(humanPosition);

            while (combatOn(humanPosition, humanPosition - goblinLocation, goblinLocation - humanPosition)) {
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
                    out.println("**************** That's a draw ****************");
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

            while (combatOn(goblinLocation, humanPosition - goblinLocation, goblinLocation - humanPosition)) {

                out.println("Human and Goblin collided. The combat began!");
                int min = 1;
                int max = 10;
                int humanVenom = (int) (Math.random()*(max-min)) + min;
                out.println("Human's life: " + humanVenom + ".");

                int goblinVenom = (int) (Math.random()*(max-min)) + min;
                out.println(new StringBuilder().append("Goblin's life: ").append(goblinVenom).append(".").toString());


                if (goblinVenom < humanVenom) {
                    out.println("=> Human won! (Human +1, Goblin -3)");
                    humanLife++;
                    goblinLife -= 3;
                    break;
                } else if (goblinVenom > humanVenom) {
                    out.println("=> Goblin won! (Goblin +1, Human -3)");
                    goblinLife++;
                    humanLife -= 3;
                    break;
                } else if (humanVenom == goblinVenom) {
                    out.println("********************* That's a draw! ****************");
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

    private static boolean combatOn(int humanPosition, int i, int i2) {
        boolean b = locateHuman.contains(humanPosition) || locateGoblin.contains(humanPosition) ||
                i == 1 || i2 == 1 ||
                i == 10 || i2 == 10;
        return b;
    }

    private static void keypad() {
        out.println(
                "\n------------------- Control Keys -------------------------\n" +
                "            n\n" +
                "          w + e    ▄ (Human life left: " + humanLife + ")\n" +
                "            s      ▓ (Goblin life left: " + goblinLife + ")\n" +
                "----------------------------------------------------------\n");
    }

    private static int locateHuman(int humanPosition) {
        try {
            Scanner scan = new Scanner(in);
            out.println("Select your next move (n/s/e/w)");
            char nextHumanPos = scan.next().charAt(0);

            if (nextHumanPos == 'n') {
                humanPosition -= 10;
                if (humanPosition < 1) {
                    humanPosition += 100;

                }
            }

            if (nextHumanPos == 's') {
                humanPosition += 10;
                if (humanPosition > 100) {
                    humanPosition -= 100;

                }

            }

            if (nextHumanPos == 'e') {
                humanPosition++;
                if (humanPosition > 100) {
                    humanPosition -= 100;
                }
            }

            if (nextHumanPos == 'w') {
                humanPosition--;
                if (humanPosition < 1) {
                    humanPosition += 100;

                }
            }

        } catch (Exception e) {
            out.println("Please select a one of these directions: n/s/e/w.");
        }
        return humanPosition;
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

        Arrays.stream(area).forEach(row -> {
            for (char c : row) {
                out.print(c);
            }
            out.println();
        });
    }

    public static void makeMove(char[][] battleground, int coordinate, String player) {

        char sign = '▄';

        if (player.equals("human")) {
            sign = '▄';
            locateHuman.add(coordinate);
        } else if (player.equals("goblin")) {
            sign = '▓';
            locateGoblin.add(coordinate);
        }


        switch(coordinate) {
            case 1:
                battleground[1][3] = sign;
                break;
            case 2:
                battleground[1][5] = sign;
                break;
            case 3:
                battleground[1][7] = sign;
                break;
            case 4:
                battleground[1][9] = sign;
                break;
            case 5:
                battleground[1][11] = sign;
                break;
            case 6:
                battleground[1][13] = sign;
                break;
            case 7:
                battleground[1][15] = sign;
                break;
            case 8:
                battleground[1][17] = sign;
                break;
            case 9:
                battleground[1][19] = sign;
                break;
            case 10:
                battleground[1][21] = sign;
                break;

            case 11:
                battleground[2][3] = sign;
                break;
            case 12:
                battleground[2][5] = sign;
                break;
            case 13:
                battleground[2][7] = sign;
                break;
            case 14:
                battleground[2][9] = sign;
                break;
            case 15:
                battleground[2][11] = sign;
                break;
            case 16:
                battleground[2][13] = sign;
                break;
            case 17:
                battleground[2][15] = sign;
                break;
            case 18:
                battleground[2][17] = sign;
                break;
            case 19:
                battleground[2][19] = sign;
                break;
            case 20:
                battleground[2][21] = sign;
                break;


            case 21:
                battleground[3][3] = sign;
                break;
            case 22:
                battleground[3][5] = sign;
                break;
            case 23:
                battleground[3][7] = sign;
                break;
            case 24:
                battleground[3][9] = sign;
                break;
            case 25:
                battleground[3][11] = sign;
                break;
            case 26:
                battleground[3][13] = sign;
                break;
            case 27:
                battleground[3][15] = sign;
                break;
            case 28:
                battleground[3][17] = sign;
                break;
            case 29:
                battleground[3][19] = sign;
                break;
            case 30:
                battleground[3][21] = sign;
                break;


            case 31:
                battleground[4][3] = sign;
                break;
            case 32:
                battleground[4][5] = sign;
                break;
            case 33:
                battleground[4][7] = sign;
                break;
            case 34:
                battleground[4][9] = sign;
                break;
            case 35:
                battleground[4][11] = sign;
                break;
            case 36:
                battleground[4][13] = sign;
                break;
            case 37:
                battleground[4][15] = sign;
                break;
            case 38:
                battleground[4][17] = sign;
                break;
            case 39:
                battleground[4][19] = sign;
                break;
            case 40:
                battleground[4][21] = sign;
                break;


            case 41:
                battleground[5][3] = sign;
                break;
            case 42:
                battleground[5][5] = sign;
                break;
            case 43:
                battleground[5][7] = sign;
                break;
            case 44:
                battleground[5][9] = sign;
                break;
            case 45:
                battleground[5][11] = sign;
                break;
            case 46:
                battleground[5][13] = sign;
                break;
            case 47:
                battleground[5][15] = sign;
                break;
            case 48:
                battleground[5][17] = sign;
                break;
            case 49:
                battleground[5][19] = sign;
                break;
            case 50:
                battleground[5][21] = sign;
                break;


            case 51:
                battleground[6][3] = sign;
                break;
            case 52:
                battleground[6][5] = sign;
                break;
            case 53:
                battleground[6][7] = sign;
                break;
            case 54:
                battleground[6][9] = sign;
                break;
            case 55:
                battleground[6][11] = sign;
                break;
            case 56:
                battleground[6][13] = sign;
                break;
            case 57:
                battleground[6][15] = sign;
                break;
            case 58:
                battleground[6][17] = sign;
                break;
            case 59:
                battleground[6][19] = sign;
                break;
            case 60:
                battleground[6][21] = sign;
                break;



            case 61:
                battleground[7][3] = sign;
                break;
            case 62:
                battleground[7][5] = sign;
                break;
            case 63:
                battleground[7][7] = sign;
                break;
            case 64:
                battleground[7][9] = sign;
                break;
            case 65:
                battleground[7][11] = sign;
                break;
            case 66:
                battleground[7][13] = sign;
                break;
            case 67:
                battleground[7][15] = sign;
                break;
            case 68:
                battleground[7][17] = sign;
                break;
            case 69:
                battleground[7][19] = sign;
                break;
            case 70:
                battleground[7][21] = sign;
                break;



            case 71:
                battleground[8][3] = sign;
                break;
            case 72:
                battleground[8][5] = sign;
                break;
            case 73:
                battleground[8][7] = sign;
                break;
            case 74:
                battleground[8][9] = sign;
                break;
            case 75:
                battleground[8][11] = sign;
                break;
            case 76:
                battleground[8][13] = sign;
                break;
            case 77:
                battleground[8][15] = sign;
                break;
            case 78:
                battleground[8][17] = sign;
                break;
            case 79:
                battleground[8][19] = sign;
                break;
            case 80:
                battleground[8][21] = sign;
                break;



            case 81:
                battleground[9][3] = sign;
                break;
            case 82:
                battleground[9][5] = sign;
                break;
            case 83:
                battleground[9][7] = sign;
                break;
            case 84:
                battleground[9][9] = sign;
                break;
            case 85:
                battleground[9][11] = sign;
                break;
            case 86:
                battleground[9][13] = sign;
                break;
            case 87:
                battleground[9][15] = sign;
                break;
            case 88:
                battleground[9][17] = sign;
                break;
            case 89:
                battleground[9][19] = sign;
                break;
            case 90:
                battleground[9][21] = sign;
                break;


            case 91:
                battleground[10][3] = sign;
                break;
            case 92:
                battleground[10][5] = sign;
                break;
            case 93:
                battleground[10][7] = sign;
                break;
            case 94:
                battleground[10][9] = sign;
                break;
            case 95:
                battleground[10][11] = sign;
                break;
            case 96:
                battleground[10][13] = sign;
                break;
            case 97:
                battleground[10][15] = sign;
                break;
            case 98:
                battleground[10][17] = sign;
                break;
            case 99:
                battleground[10][19] = sign;
                break;
            case 100:
                battleground[10][21] = sign;
                break;


            default:
                break;
        }

    }

    public static String checkWinner() {

        if (humanLife <= 0) {

            return gameOver("Goblin destroyed you!\n", "************ Game Over ***************");

        } else if (goblinLife <= 0) {

            return gameOver("You destroyed Goblin!\n", "************ Game Over **************");

        }

        return "";
    }

    private static String gameOver(String s, String s2) {
        return "Human Life: " + humanLife + "\n" +
                "Goblin Life: " + goblinLife + "\n" +
                s + s2;
    }


}