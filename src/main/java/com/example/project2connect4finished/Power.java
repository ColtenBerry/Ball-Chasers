package com.example.project2connect4finished;

public enum Power {
    FREEZE{
        final int cost = 5;
        private int quantity = 0;
        @Override
        public void activation() {
            BotBall.speed = 0;
        }

        @Override
        public Power getPower() {
            return this;
        }

        @Override
        public int getCost() {
            return this.cost;
        }

        @Override
        public int getQuantity() {
            return this.quantity;
        }

        @Override
        public void setQuantity(int newQuantity) {
            this.quantity = newQuantity;
        }

        @Override
        public String toString() {
            return "Freeze";
        }
    },
    TELEPORT {
        final int cost = 1;
        private int quantity = 0;
        @Override
        public void activation() {
            Point newPoint = RandomSpot.makeRandomPoint(userBall.radius);
            userBall.point = newPoint;
        }

        @Override
        public Power getPower() {
            return this;
        }

        @Override
        public int getCost() {
            return this.cost;
        }

        @Override
        public int getQuantity() {
            return this.quantity;
        }

        @Override
        public void setQuantity(int newQuantity) {
            this.quantity = newQuantity;
        }

        @Override
        public String toString() {
            return "Teleport";
        }
    },
    TEMPSPEEDBOOST {
        final int cost = 5;
        private int quantity = 0;
        @Override
        public void activation() {
            userBall.speed = 5;
        }

        @Override
        public Power getPower() {
            return this;
        }

        @Override
        public int getCost() {
            return this.cost;
        }

        @Override
        public int getQuantity() {
            return this.quantity;
        }

        @Override
        public void setQuantity(int newQuantity) {
            this.quantity = newQuantity;
        }

        @Override
        public String toString() {
            return "Speed Boost";
        }
    },
    KEY {
        final int cost = 50;
        private int quantity = 0;
        @Override
        public void activation() {
            Project3Controller.gameWon = true;
        }

        @Override
        public Power getPower() {
            return this;
        }

        @Override
        public int getCost() {
            return this.cost;
        }

        @Override
        public int getQuantity() {
            return this.quantity;
        }

        @Override
        public void setQuantity(int newQuantity) {
            this.quantity = newQuantity;
        }

        @Override
        public String toString() {
            return "Key";
        }
    };

    abstract public void activation();
    abstract public Power getPower();
    abstract public int getCost();
    abstract public int getQuantity();
    abstract public void setQuantity(int newQuantity);
    abstract public String toString();
}
