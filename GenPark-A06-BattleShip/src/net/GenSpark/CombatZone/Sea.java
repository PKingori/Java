package net.GenSpark.CombatZone;

import net.GenSpark.Assets.IBattleField;
import net.GenSpark.Assets.SeaField;
import net.GenSpark.controls.WarShip;
import net.GenSpark.Assets.ShipField;
import net.GenSpark.controls.Load;

import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;


public class Sea {
    private static final char SEA = '~';
    private static final int BATTLESPOT = 9;
    private static final char[] BATTLECOORDINATES = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final String HORIZONTAL = "H";
    private static final String VERTICAL = "V";

    private final Scanner scanner;
    private final IBattleField[][] seaBoard;
    private static final WarShip[] ships;

    static {
        ships = new WarShip[] {
                new WarShip("Carrier", Load.ARLEIGH),
                new WarShip("Battleship", Load.NIMITZ),
                new WarShip("Cruiser", Load.TICONDEROGA),
                new WarShip("Submarine", Load.ZUMWALT),
                new WarShip("Destroyer", Load.HAMILTON)
        };
    }

    public Sea() {
        this.scanner = new Scanner(System.in);
        this.seaBoard = new IBattleField[BATTLESPOT][BATTLESPOT];
        for(int i = 0; i < BATTLESPOT; i++) {
            for(int j = 0; j < BATTLESPOT; j++) {
                seaBoard[i][j] = new SeaField();
            }
        }

    }

    public void placeShipsOnBoard() {
        Arrays.stream(ships).forEach(ship -> {
            boolean horizontal = askValidShipDirection();
            Point startingPoint = askValidStartingPoint(ship, horizontal);
            placeValidShip(ship, startingPoint, horizontal);
            printBoard();
        });
    }

    public IBattleField getField(int x, int y) {
        if(!isInsideBoard(x, y)) {
            throw new IllegalArgumentException("Outside board - try again: ");
        }
        return seaBoard[y][x];
    }

    public void printBoard() {
        System.out.print("\t");

        for(int i = 0; i < BATTLESPOT; i++) {
            System.out.print(BATTLECOORDINATES[i] + "\t");
        }

        System.out.println();

        for(int i = 0; i < BATTLESPOT; i++) {
            System.out.print((i+1) + "\t");
            for(int j = 0; j < BATTLESPOT; j++) {
                System.out.print(seaBoard[i][j].getMedal() + "\t");
            }
            System.out.println();
        }
    }

    private boolean askValidShipDirection() {
        System.out.printf("%n------------------------------ ");
        System.out.printf("%nShip Orientation Instructions: ");
        System.out.printf("%n------------------------------ ");
        System.out.printf("%n Select (H) for Horizontal Ship Orientation");
        System.out.printf("%n Select (V) for Vertical Ship Orientation   ");

        String orientation;
        do {
            orientation = scanner.nextLine().trim();
        }while (!HORIZONTAL.equals(orientation) && !VERTICAL.equals(orientation));

        return HORIZONTAL.equals(orientation);
    }

    private Point askValidStartingPoint(WarShip ship, boolean horizontal) {
        Point from;
        do {
            System.out.printf("%nEnter coordinate of %s (example, 4 5; 5 6) (ship load =  %d): ", ship.getName(), ship.getSize());
            from = new Point(scanner.nextInt(), scanner.nextInt());
        } while(!isValidStartingPoint(from, ship.getSize(), horizontal));

        return from;
    }

    private boolean isValidStartingPoint(Point from, int length, boolean horizontal) {
        int xDiff = 0;
        int yDiff = 0;
        if(horizontal) {
            xDiff = 1;
        } else {
            yDiff = 1;
        }

        int x = (int)from.getX() - 1;
        int y = (int)from.getY() - 1;
        if(!isInsideBoard(x, y) ||
                (!isInsideBoard(x + length,y) && horizontal) ||
                (!isInsideBoard(x, y + length) && !horizontal)
        ) {
            return false;
        }

        for(int i = 0; i < length; i++) {
            if(seaBoard[(int)from.getY() + i *yDiff - 1]
                    [(int)from.getX() + i *xDiff - 1].getMedal() != SEA){
                return false;
            }
        }
        return true;
    }

    private void placeValidShip(WarShip ship, Point startingPoint, boolean horizontal) {
        int xDiff = 0;
        int yDiff = 0;
        if(horizontal) {
            xDiff = 1;
        } else {
            yDiff = 1;
        }
        for(int i = 0; i < ship.getSize() ; i++) {
            seaBoard[(int)startingPoint.getY() + i*yDiff - 1]
                    [(int)startingPoint.getX()+ i*xDiff - 1] = new ShipField(ship);
        }
    }
    private boolean isInsideBoard(int x, int y){
        return x <= BATTLESPOT && x >= 0
                && y <= BATTLESPOT && y >= 0;
    }
}
