package net.GenSpark.Assets;

import net.GenSpark.Status.DamageReport;
import net.GenSpark.controls.WarShip;


public class ShipField implements IBattleField {
    private final WarShip ship;

    public ShipField(WarShip ship) {
        this.ship = ship;
    }

    @Override
    public char getMedal() {
        char icon;
        DamageReport shipState = ship.getState();
        switch (shipState) {
            case IMPACTED: icon = 'O';
                break;
            case TERMINATED: icon = 'O';
                break;
            case CLEAR: icon = 'X';
                break;
            default: icon = ' ';
                break;
        }
        return icon;
    }

    @Override
    public DamageReport shootAt() {
        ship.hit();
        return ship.getState();
    }
}