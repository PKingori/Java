package net.GenSpark.navy;

import net.GenSpark.CombatZone.Sea;
import net.GenSpark.Status.DamageReport;

import java.awt.*;
import java.util.Scanner;


public class Navy implements INavyStamp {
    private int stillAlive = 17;

    private int id;
    private Sea sea;
    private Scanner scanner;

    public Navy(int id) {
        this.id = id;
        this.sea = new Sea();
        this.scanner = new Scanner(System.in);
    }

    public int getId() {
        return id;
    }

    public Sea getBoard() {
        return sea;
    }

    @Override
    public void placeShips() {
        System.out.printf("%n********** B  A  T  T  L  E  S  H  I  P      B  E  G  I  N  S  ******************%n");
        System.out.printf("%n======== TRANSMISSION RECEIVED FROM COMMANDER IN CHIEF: BATTLE SHIP GO TO WAR!  ========%n", id);
        sea.placeShipsOnBoard();
    }

    @Override
    public void fireAt(INavyStamp opponent) {

    }

    public void ShootAt(Navy enemy) {
        System.out.printf("%n Attention Commando %d - Enter coordinates of your target: ", id);

        boolean isPointValid = false;
        while(!isPointValid) try {
            Point point = new Point(scanner.nextInt(), scanner.nextInt());
            int x = (int) point.getX() - 1;
            int y = (int) point.getY() - 1;

            DamageReport result = ((Navy) enemy)
                    .getBoard()
                    .getField(x, y)
                    .shootAt();

            if (result == DamageReport.IMPACTED || result == DamageReport.TERMINATED) {
                stillAlive--;
            }

            isPointValid = true;
        } catch (IllegalArgumentException e) {
            System.out.printf(e.getMessage());
        }
    }

    @Override
    public int getStillAlive() {
        return stillAlive;
    }


}