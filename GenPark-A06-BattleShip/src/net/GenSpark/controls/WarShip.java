package net.GenSpark.controls;

import net.GenSpark.Status.DamageReport;


public class WarShip {
    private final String name;
    private final int size;
    private int lives;

    public WarShip(String name, int size) {
        this.name = name;
        this.size = size;
        this.lives = size;
    }

    public void hit() {
        if(lives > 0) {
            System.out.printf("%nKudos! The %s was hit", name);
            lives--;
        } else {
            System.out.println("Terminated!");
        }
    }

    public DamageReport getState() {
        if(lives == 0) {
            return DamageReport.TERMINATED;
        } else if(lives < size) {
            return DamageReport.IMPACTED;
        } else {
            return DamageReport.CLEAR;
        }
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}