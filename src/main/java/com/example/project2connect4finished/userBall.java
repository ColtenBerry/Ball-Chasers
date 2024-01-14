package com.example.project2connect4finished;

public class userBall {
	public static double dx;
	public static double dy;
	public static int radius;
	public static double speed;
	public static Point point;
	private Position location;
	public userBall(int radius, Point point) {
		userBall.point = point;
		userBall.radius = radius;
		dx = 0;
		dy = 0;
		speed = 1.0;
		location = point.getPosition();

	}
	public static Point getStartingUserBallPoint() {return new Point(300, 200);}
	public Position getLocation() {
		return location;
	}
	public static double getRadius() {return radius; }
	public static Point getTopPoint() {
		Point p = new Point(point.getX(), point.getY() - radius);
		return p;
	}
	public static Point getLeftPoint() {
		Point p = new Point(point.getX() - radius, point.getY());
		return p;
	}
	public static Point getRightPoint() {
		Point p = new Point(point.getX() + radius, point.getY());
		return p;
	}
	public static Point getBottomPoint() {
		Point p = new Point(point.getX(), point.getY() + radius);
		return p;
	}
	public static void move() {
			if (point.getX() + radius >= 600) {
				point.setX(600 - radius);
			}
			else if (point.getX() - radius <= 0) {
				point.setX(radius);
			}
		point.setX(point.getX() + dx);
			if (point.getY() + radius >= 400) {
				point.setY(400 - radius);
			}
			else if (point.getY() - radius <= 0) {
				point.setY(radius);
			}
		point.setY(point.getY() + dy);
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
