package com.example.project2connect4finished;

public class ScoreBall {
    public static int radius;
    private static Point point;
    public static Point startPoint = new Point(300, 100);

    public ScoreBall(int radius, Point point) {
        this.radius = radius;
        this.point = startPoint;

    }

    public static Point getPoint() {
        return point;
    }

    public static double getScoreBallX() {
        return point.getX();
    }

    public static double getScoreBallY() {
        return point.getY();
    }

    public static void setScoreBallPoint(Point point) {
        ScoreBall.point = point;
    }

    public static void scoreBallCollision(Maze dungeon) {
        if (CollisionDetermine.checkCircleCollide(userBall.point.getX(), userBall.point.getY(),
                userBall.getRadius(), getScoreBallX(), getScoreBallY(), ScoreBall.radius)) {
            makeNewScoreBall(dungeon);
            Project3Controller.score += 1;
        }
    }
    public static void makeNewScoreBall(Maze dungeon) {
        Point scoreBallRandomSpawn = RandomSpot.makeRandomPoint(5);
        Position spot = scoreBallRandomSpawn.getPosition();
        while (!dungeon.getStateFor(spot).canEnter()) {
            scoreBallRandomSpawn = RandomSpot.makeRandomPoint(5);
            spot = scoreBallRandomSpawn.getPosition();
        }
        ScoreBall.setScoreBallPoint(scoreBallRandomSpawn);

    }
}
