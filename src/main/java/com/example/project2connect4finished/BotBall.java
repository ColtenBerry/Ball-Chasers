package com.example.project2connect4finished;

public class BotBall {
    public static final int ENEMY_RADIUS = 5;

    private Point startingBotBallPoint = RandomSpot.botBallLocationPoint();
    private Point point;
    private int radius;
    public static double speed = 1;
    private Position location;
    public BotBall(int radius) {
        this.radius = radius;
        this.point = new Point (startingBotBallPoint.getX(), startingBotBallPoint.getY());
        location = startingBotBallPoint.getPosition();
    }

    public Position getLocation() {
        return location;
    }

    public double getX() {
        return point.getX();
    }

    public double getY() {
        return point.getY();
    }

    public double getRadius() {return radius; }
    public Point getTopPoint() {
        Point p = new Point(point.getX(), point.getY() - radius);
        return p;
    }
    public Point getLeftPoint() {
        Point p = new Point(point.getX() - radius, point.getY());
        return p;
    }
    public Point getRightPoint() {
        Point p = new Point(point.getX() + radius, point.getY());
        return p;
    }
    public Point getBottomPoint() {
        Point p = new Point(point.getX(), point.getY() + radius);
        return p;
    }

    @Override
    public String toString() {
        return "BotBall@" + point;
    }

    public void move() {
        if (Screen.GAME.returnGameIsGoing()) {
            if (userBall.point.getX() > point.getX()) {
                point.setX(point.getX() + speed);
            }
            else {
                point.setX(point.getX() - speed);
            }
            if (userBall.point.getY() > point.getY()) {
                point.setY(point.getY() + speed);
            }
            else {
                point.setY(point.getY() - speed);
            }
        }
        if (!Project3Controller.mazeData.canEnter(getTopPoint().getPosition())) {
            point.setY(point.getY() + speed);
        }
        if (!Project3Controller.mazeData.canEnter(getLeftPoint().getPosition())) {
            point.setX(point.getX() + speed);
        }
        if (!Project3Controller.mazeData.canEnter(getRightPoint().getPosition())) {
            point.setX(point.getX() - speed);
        }
        if (!Project3Controller.mazeData.canEnter(getBottomPoint().getPosition())) {
            point.setY(point.getY() - speed);
        }


    }
}
