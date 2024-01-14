package com.example.project2connect4finished;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BotBallView extends Parent{
    private BotBall ball;
    private Circle c;
    public BotBallView(BotBall ball) {
        this.ball = ball;
        c = new Circle();
        c.setFill(Color.RED);
        c.setStroke(Color.BLACK);
        getChildren().add(c);
        }

        public void update() {
            c.setRadius(ball.getRadius());
            c.setTranslateX(ball.getX());
            c.setTranslateY(ball.getY());
        }

}
