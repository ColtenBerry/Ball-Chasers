package com.example.project2connect4finished;

public class RandomSpot {
    static Point spot1 = new Point(BotBall.ENEMY_RADIUS * 2, BotBall.ENEMY_RADIUS * 2);
    static Point spot2 = new Point(BotBall.ENEMY_RADIUS * 2, 400 - BotBall.ENEMY_RADIUS * 2);
    static Point spot3 = new Point(600 - BotBall.ENEMY_RADIUS * 2, BotBall.ENEMY_RADIUS * 2);
    static Point spot4 = new Point(600 - BotBall.ENEMY_RADIUS * 2, 400 - BotBall.ENEMY_RADIUS * 2);
    static Point[] botBallLocationSpots = {spot1, spot2, spot3, spot4};
    public static Point botBallLocationPoint() {
        java.util.Random random = new java.util.Random();
        int randomPoint = random.nextInt(botBallLocationSpots.length);
        return botBallLocationSpots[randomPoint];
    }
    public static Point makeRandomPoint(int radius) {
        java.util.Random random = new java.util.Random();
        int yValue = random.nextInt(radius, 400 - radius);
        int xValue = random.nextInt(radius, 600 - radius);
        return new Point(xValue, yValue);
    }
}
