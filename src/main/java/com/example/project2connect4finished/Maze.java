package com.example.project2connect4finished;

public class Maze {
    private Cell[][] cells;
    public Maze(int width, int height) {
        cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = Cell.CLOSED;
            }
        }
    }
    public int getWidth() {
        return cells.length;
    }
    public int getHeight() {
        return cells[0].length;
    }
    public boolean inMaze(Position p) {
        return !(p.getX() < 0 || p.getY() < 0 || p.getX() >= getWidth() || p.getY() >= getHeight());
    }
    public Cell getStateFor(Position p) {
        if (inMaze(p)) {
            return cells[p.getX()][p.getY()];
        }
        return Cell.CLOSED;
    }
    public void setStateFor(Position p, Cell state) {
        if (inMaze(p)) {
            cells[p.getX()][p.getY()] = state;
        }
    }
}
