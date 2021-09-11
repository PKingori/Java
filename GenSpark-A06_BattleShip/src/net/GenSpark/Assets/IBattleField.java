package net.GenSpark.Assets;

import net.GenSpark.Status.DamageReport;


public interface IBattleField {
    default char getMedal() {
        var i = 0;
        return (char) i;
    }

    DamageReport shootAt();
}