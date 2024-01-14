package com.example.project2connect4finished;

public enum Screen {
    MAIN_MENU, GAME, STORE, FINISHED, TUTORIAL;
    private static Screen activeScreen;
    private static int roundNumber = 0;
    public static int getRoundNumber() {
        return roundNumber;
    }
    public static void setRoundNumber(int newNumber) {
        roundNumber = newNumber;
    }
    public static void setActiveScreen(Screen screen) {
        activeScreen = screen;
    }
    public static Screen getActiveScreen() {
        return activeScreen;
    }
    private static boolean gameIsGoing = false;
    public static void setGameIsGoing(boolean going){
        gameIsGoing = going;
    }
    public boolean returnGameIsGoing() {
        return gameIsGoing;
    }

}
