package com.example.project2connect4finished;

public class Timer {
    private static double time;
    private static double initialTime;
    public Timer() {
        time = 0;
        initialTime = 0;
    }
    public static double getTime() {
        return time;
    }
    public static void setTime(double time) {
        Timer.time = time;
    }
    public static double getInitialTime() {return initialTime;}
    public static void setInitialTime(double initialTime) {Timer.initialTime = initialTime;}
    public static boolean checkTime(double initialTime,double goalTime) {
        if (time / 100 - initialTime <= goalTime) {
            return true;
        }
        return false;
    }
    public void update() {
        if (Screen.GAME.returnGameIsGoing()) {
            time += 1;
        }
    }
}
