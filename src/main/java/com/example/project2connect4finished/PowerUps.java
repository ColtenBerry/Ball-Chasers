package com.example.project2connect4finished;

public class PowerUps {
    private static Power activePower;
    private static final Power[] powers = new Power[] {Power.TELEPORT, Power.FREEZE, Power.TEMPSPEEDBOOST};
    public PowerUps(Power[] powers) {
        powers = new Power[]{Power.TELEPORT, Power.FREEZE, Power.TEMPSPEEDBOOST};
    }
    public static Power[] getPowerUps() {
        return powers;
    }
    public static Power getActivePower() {
        return activePower;
    }
    public static void setActivePower(Power power) {
        activePower = power;
    }
    public static void activatePower(Power power) {
        PowerUps.setActivePower(power);
        PowerUps.getActivePower().activation();
        if (!power.equals(Power.TELEPORT)) {
            power.setQuantity(power.getQuantity() - 1);
        }
    }
    public static boolean buyPower(int cost, Power power) {
        if (Project3Controller.score - cost >= 0) {
            Project3Controller.score -= cost;
            power.setQuantity(power.getQuantity() + 1);
            return true;
        }
        else {
            return false;
        }
    }
}
