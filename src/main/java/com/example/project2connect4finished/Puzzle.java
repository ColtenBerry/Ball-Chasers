package com.example.project2connect4finished;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Puzzle {
    private Maze dungeon;
    private userBall hero;
    private BotBall enemy;
   public Puzzle (int w, int h) {
        dungeon = new Maze(w, h);
    }
    public Color getColorFor(Position spot) {
        return dungeon.getStateFor(spot).getColor();
    }
    public boolean canEnter(Position spot) {
        return dungeon.getStateFor(spot).canEnter();
    }
    public void fill (Position spot) {
        dungeon.setStateFor(spot, Cell.CLOSED);
    }
    public void clear(Position spot) {
        if (dungeon.inMaze(spot)) {
            dungeon.setStateFor(spot, Cell.EMPTY);

        }
    }
    public int getWidth() {
        return dungeon.getWidth();
    }
    public int getHeight() {
        return dungeon.getHeight();
    }
    public Maze getDungeon() {
       return dungeon;
    }
    public void placeUser(Pane game) {
       hero = new userBall(10, userBall.getStartingUserBallPoint());
       UserBallView view = new UserBallView(hero);
       game.getChildren().add(view);
    }
    public Position getUserPosition() {
        return hero.getLocation();
    }
    public void placeEnemy(Pane game) {
       enemy = new BotBall(5);
       BotBallView view = new BotBallView(enemy);
       game.getChildren().add(view);
    }
    public void placeScoreBall(Pane game) {
       ScoreBall coin = new ScoreBall(5, ScoreBall.getPoint());
       ScoreBallView view = new ScoreBallView(coin);
       game.getChildren().add(view);
    }

    public Position getEnemyPosition() {
       return enemy.getLocation();
    }
}
