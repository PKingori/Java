package net.GenSpark.Assets;

import net.GenSpark.Status.DamageReport;


public class SeaField implements IBattleField {
    private boolean isTargetHit = false;

    @Override
    public char getMedal() {
        return isTargetHit ? 'M' : '~';
    }

    @Override
    public DamageReport shootAt() {
        isTargetHit = true;
        return DamageReport.CLEAR;
    }
}