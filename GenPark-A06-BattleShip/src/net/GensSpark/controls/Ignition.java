package net.GenSpark.controls;

import net.GenSpark.navy.Navy;


public class Ignition {
    private Navy[] marines;

    public Ignition() {

        this.marines = new Navy[]{
                new Navy(1),
                new Navy(2)
        };
    }

    public void start() {
        int i = 0;
        int j = 1;
        int len = marines.length;
        Navy player = null;

        this.marines[i].placeShips();
        this.marines[j].placeShips();

        while(marines[0].getStillAlive() > 0 &&
                marines[1].getStillAlive() > 0) {

            marines[i++ % len].ShootAt(marines[j++ % len]);
            player = (marines[0].getStillAlive() < marines[1].getStillAlive()) ?
                    marines[1] :
                    marines[0];
        }

        System.out.printf("Kudos Commando %d, You triumphed!",player.getId());
    }
}