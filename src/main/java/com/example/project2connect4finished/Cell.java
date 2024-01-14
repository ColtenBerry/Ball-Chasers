package com.example.project2connect4finished;

import javafx.scene.paint.Color;

public enum Cell {
    EMPTY{
        @Override
        public boolean canEnter() {
            return true;
        }

        @Override
        public Color getColor() {
            return Color.WHITE;
        }
    },
    CLOSED{
        @Override
        public boolean canEnter() {
            return false;
        }

        @Override
        public Color getColor() {
            return Color.BLACK;
        }
    };
    public abstract boolean canEnter();
    public abstract Color getColor();
}
