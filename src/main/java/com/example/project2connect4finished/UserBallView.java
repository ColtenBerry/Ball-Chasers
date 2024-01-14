package com.example.project2connect4finished;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class UserBallView extends Parent {

    private static userBall ball;
    private static Circle c;
    public UserBallView(userBall ball) {
        UserBallView.ball = ball;
        c = new Circle();
        c.setFill(Color.BLUE);
        c.setStroke(Color.BLACK);
        getChildren().add(c);
    }

    public static void update() {
        c.setRadius(userBall.getRadius());
        c.setTranslateX(userBall.point.getX());
        c.setTranslateY(userBall.point.getY());
    }

}
