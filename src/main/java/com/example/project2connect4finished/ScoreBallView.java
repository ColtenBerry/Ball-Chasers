package com.example.project2connect4finished;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ScoreBallView extends Parent {
    private static ScoreBall ball;
    private static Circle c;
    public ScoreBallView(ScoreBall ball) {
        ScoreBallView.ball = ball;
        c = new Circle();
        c.setFill(Color.YELLOW);
        c.setStroke(Color.BLACK);
        getChildren().add(c);
    }
    public static void update() {
        c.setRadius(ScoreBall.radius);
        c.setTranslateX(ScoreBall.getScoreBallX());
        c.setTranslateY(ScoreBall.getScoreBallY());
    }
}
