package com.example.project2connect4finished;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
//import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
//import javafx.scene.input.MouseButton;
//import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
//import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Project3Controller {
    public static boolean gameWon;
    private Timer timer;
    private double initialTime;
    @FXML
    private Label freezeCostLabel;
    @FXML
    private Label spdBoostCostLabel;
    @FXML
    private Label teleportCostLabel;
    @FXML
    private Label keyCostLabel;
    @FXML
    private Button key;
    @FXML
    private Button store;
    @FXML
    private Button backToGame;
    @FXML
    private Button tutorial;
    @FXML
    private Button purchaseFreeze;
    @FXML
    private Button purchaseTeleport;
    @FXML
    private Button purchaseTempSpdBoost;
    @FXML
    private Label freezeQuantityLabel;
    @FXML
    private Label teleportQuantityLabel;
    @FXML
    private Label tempSpdBoostQuantityLabel;
    @FXML
    private Label storeMessageLabel;
    @FXML
    private Button reset;
    @FXML
    public Label timeLabel;
    @FXML
    public Label finalMessageLabel = new Label();
    @FXML
    private Label activePowerLabel;
    @FXML
    public Label coinCountLabel;
    @FXML
    public Label storeCoinCountLabel;
    @FXML
    private Pane gameScreenPane;
    @FXML
    private Label roundLabel;
    @FXML
    private Label keyQuantityLabel;
    private Movement clock;
    public static int score = 0;
    @FXML
    private void startClock(){
        clock.start();
    }
    @FXML
    private void stopClock(){
        clock.stop();
    }
    private int goalTime = 10;
    private ArrayList<BotBall> chasing;
    private ArrayList<BotBallView> bvs;
    private class Movement extends AnimationTimer {

        private long last = 0;
        private ArrayList<BotBall> botBallArrayList;
        private ArrayList<BotBallView> botBallViewArrayList;
        public void setBalls(ArrayList<BotBall> botBalls) {
            this.botBallArrayList = botBalls;
        }
        // handle makes it move
        @Override
        public void handle(long now) {
            long FRAMES_PER_SEC = 100L;
            long INTERVAL = 1000000000L / FRAMES_PER_SEC;
            if (now - last > INTERVAL) {
                ScoreBall.scoreBallCollision(mazeData.getDungeon());
                // for loop - use loop variable instead of "BotBall"
                for (BotBall b: botBallArrayList) {
                    if (CollisionDetermine.checkCircleCollide(userBall.point.getX(), userBall.point.getY(),
                            userBall.getRadius(), b.getX(), b.getY(), b.getRadius())) {
                        roundLost();
                    }
                }
//                for (int x = 0; x < PowerUps.getPowerUps().length; x++) {
//                    if (PowerUps.getActivePower() == PowerUps.getPowerUps()[x]) {
//                        if (Timer.checkTime(initialTime, Timer.getTime(), 5)) {
//                            PowerUps.getPowerUps()[x].activation();
//                            activePowerLabel.setText("Power: " + PowerUps.getPowerUps()[x]);
//                        }
//                    }
//                    else {
//                        BotBall.speed = 1.0;
//                        UserBall.speed = 1.0;
//                        PowerUps.setActivePower(null);
//                    }
//                }
                if (PowerUps.getActivePower() == Power.FREEZE) {
                    if(Timer.checkTime(initialTime, 5)) {
                        Power.FREEZE.activation();
                        activePowerLabel.setText("Power: " + PowerUps.getActivePower());
                    }
                    else {
                        BotBall.speed = 1.0;
                        PowerUps.setActivePower(null);
                    }
                }
                if (PowerUps.getActivePower() == Power.TEMPSPEEDBOOST) {
                    if (Timer.checkTime(initialTime, 5)) {
                        Power.TEMPSPEEDBOOST.activation();
                        activePowerLabel.setText("Power: " + PowerUps.getActivePower());
                    }
                    else {
                        PowerUps.setActivePower(null);
                        userBall.speed = 1.0;
                    }
                }
                if (PowerUps.getActivePower() == Power.TELEPORT) {
                    if (Timer.checkTime(initialTime, 5)) {
                        activePowerLabel.setText("Power: " + PowerUps.getActivePower());
                    }
                    else {
                        PowerUps.setActivePower(null);
                    }
                }
                if (PowerUps.getActivePower() == null) {
                    activePowerLabel.setText("");
                }
                if (!Timer.checkTime(Timer.getInitialTime(), goalTime)) {
                    makeNewEnemy();
                    goalTime += 10;

                }
                for (BotBall b: botBallArrayList) {
                    b.move();
                }
                updateGameLabels();
                updateViews();
                timer.update();
                last = now;
            }
        }

        private void updateGameLabels() {
            coinCountLabel.setText("Coins: " + score);
            timeLabel.setText("Time: " + Timer.getTime() / 100);
            freezeQuantityLabel.setText("" + Power.FREEZE.getQuantity());
            tempSpdBoostQuantityLabel.setText("" + Power.TEMPSPEEDBOOST.getQuantity());
            teleportQuantityLabel.setText("" + Power.TELEPORT.getQuantity());
            keyQuantityLabel.setText("" + Power.KEY.getQuantity());
        }
    }
    private Stage stage;
    private Scene scene;
    boolean firstTime = true;
    @FXML
    public void switchToNewScene(ActionEvent event) throws IOException {
        if (!Screen.GAME.returnGameIsGoing()) {
            Screen.setActiveScreen(Screen.STORE);
            Parent root = FXMLLoader.load(getClass().getResource("Project3Store.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            if (clock != null) {
                clock.stop();
            }
        }
    }
    @FXML
    public void switchBackToGame(ActionEvent event) throws IOException {
        Screen.setActiveScreen(Screen.GAME);
        BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Project3.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToGameFinished(ActionEvent event) throws  IOException {
        if (PowerUps.buyPower(Power.KEY.getCost(), Power.KEY)) {
            Screen.setActiveScreen(Screen.FINISHED);
            Parent root = FXMLLoader.load(getClass().getResource("Project3WinScreen.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    public void switchToTutorial(ActionEvent event) throws  IOException {
        Screen.setActiveScreen(Screen.TUTORIAL);
        Parent root = FXMLLoader.load(getClass().getResource("Project3Tutorial.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void roundLost() {
        Screen.setGameIsGoing(false);
        userBall.dx = 0;
        userBall.dy = 0;
    }
    static Puzzle mazeData;
    HashMap<Position, Rectangle> cells = new HashMap<>();
    double cellWidth, cellHeight;
    public void makeNewEnemy() {
        BotBall b = new BotBall(BotBall.ENEMY_RADIUS);
        chasing.add(b);
        BotBallView bv = new BotBallView(b);
        bvs.add(bv);
        gameScreenPane.getChildren().add(bv);
    }
   @FXML
    public void initialize() {
        if (Screen.getActiveScreen() == Screen.GAME) {
            Screen.setRoundNumber(Screen.getRoundNumber() + 1);
            roundLabel.setText("Round: " + Screen.getRoundNumber());
            chasing = new ArrayList<BotBall>();
            bvs = new ArrayList<BotBallView>();
            Screen.setGameIsGoing(true);
            PowerUps.setActivePower(null);
            coinCountLabel.setText("Coins: " + score);
            timeLabel.setText("Time: " + Timer.getTime());
            setMaze();
            if (firstTime) {
                clock = new Movement();
            }
            clock.setBalls(chasing);
            clock.start();
        }
        else if (Screen.getActiveScreen() == Screen.STORE) {
            storeCoinCountLabel.setText("Coins: " + score);
            freezeQuantityLabel.setText("Quantity: " + Power.FREEZE.getQuantity());
            teleportQuantityLabel.setText("Quantity: " + Power.TELEPORT.getQuantity());
            tempSpdBoostQuantityLabel.setText("Quantity: " + Power.TELEPORT.getQuantity());
            keyQuantityLabel.setText("Quantity: " + Power.KEY.getQuantity());
            freezeCostLabel.setText("Cost: " + Power.FREEZE.getCost());
            teleportCostLabel.setText("Cost: " + Power.TELEPORT.getCost());
            spdBoostCostLabel.setText("Cost: " + Power.TEMPSPEEDBOOST.getCost());
            keyCostLabel.setText("Cost: " + Power.KEY.getCost());
        }
    }
    @FXML
    private void Pressed(KeyEvent e) {
        if (Screen.GAME.returnGameIsGoing()) {
            if (e.getCode().equals(KeyCode.A)) {
//                if ()
                    userBall.dx = -userBall.speed;
            }
            if (e.getCode().equals(KeyCode.D)) {
                if (userBall.point.getX() < 600 - userBall.getRadius()) {
                    userBall.dx = userBall.speed;
                }
            }
            if (e.getCode().equals(KeyCode.W)) {
                if (userBall.point.getY() > 0) {
                    userBall.dy = -userBall.speed;
                }
            }
            if (e.getCode().equals(KeyCode.S)) {
                if (userBall.point.getY() < 400) {
                    userBall.dy = userBall.speed;
                }
            }
            if (e.getCode().equals(KeyCode.F)) {
                if (Power.FREEZE.getQuantity() > 0 && PowerUps.getActivePower() == null) {
                    PowerUps.activatePower(Power.FREEZE);
                    initialTime = Timer.getTime() / 100;
                }
            }
            if (e.getCode().equals(KeyCode.T)) {
                if (Power.TELEPORT.getQuantity() > 0 && PowerUps.getActivePower() == null) {
                    PowerUps.activatePower(Power.TELEPORT);
                    initialTime = Timer.getTime() / 100;
                    Power.TELEPORT.setQuantity(Power.TELEPORT.getQuantity() - 1);
                    while (!mazeData.canEnter(userBall.point.getPosition())) {
                        PowerUps.activatePower(Power.TELEPORT);
                    }
                }
            }
            if (e.getCode().equals(KeyCode.B)) {
                if (Power.TEMPSPEEDBOOST.getQuantity() > 0 && PowerUps.getActivePower() == null) {
                    PowerUps.activatePower(Power.TEMPSPEEDBOOST);
                    initialTime = Timer.getTime() / 100;
                }
            }
    }
    }
    @FXML
    private void Released(KeyEvent e) {
        if (e.getCode().equals(KeyCode.A) || e.getCode().equals(KeyCode.D)) {
            userBall.dx = 0;
        }
        if (e.getCode().equals(KeyCode.W) || e.getCode().equals(KeyCode.S)) {
            userBall.dy = 0;
        }
    }
    @FXML
    private void freezePurchaseButtonPressed() {
        if(PowerUps.buyPower(Power.FREEZE.getCost(), Power.FREEZE)) {
            freezeQuantityLabel.setText("Quantity: " + Power.FREEZE.getQuantity());
            storeCoinCountLabel.setText("Coins: " + score);
        }
        else {
            storeMessageLabel.setText("Not Enough Coins");
        }
    }
    @FXML
    private void teleportPurchaseButtonPressed() {
        if (PowerUps.buyPower(Power.TELEPORT.getCost(), Power.TELEPORT)) {
            teleportQuantityLabel.setText("Quantity: " + Power.TELEPORT.getQuantity());
            storeCoinCountLabel.setText("Coins: " + score);
        }
        else {
            storeMessageLabel.setText("Not Enough Coins");
        }
    }
    @FXML
    private void tempSpeedBoostPurchaseButtonPressed() {
        if (PowerUps.buyPower(Power.TEMPSPEEDBOOST.getCost(), Power.TEMPSPEEDBOOST)) {
            tempSpdBoostQuantityLabel.setText("Quantity: " + Power.TEMPSPEEDBOOST.getQuantity());
            storeCoinCountLabel.setText("Coins: " + score);
        }
        else {
            storeMessageLabel.setText("Not Enough Coins");
        }
    }
    @FXML
    private void keyPurchaseButtonPressed() {
        if (PowerUps.buyPower(Power.KEY.getCost(), Power.KEY)) {
            keyQuantityLabel.setText("Quantity: " + Power.KEY.getQuantity());
            storeCoinCountLabel.setText("Coins: " + score);
        }
        else {
            storeMessageLabel.setText("Not Enough Coins");
        }
    }
    @FXML
    private void resetButtonPressed() {
        if (!Screen.GAME.returnGameIsGoing()) {
            if (Screen.getRoundNumber() < 10) {
                Screen.setRoundNumber(Screen.getRoundNumber() + 1);
                roundLabel.setText("Round: " + Screen.getRoundNumber());
                Screen.setGameIsGoing(true);
                chasing = new ArrayList<BotBall>();
                clock.setBalls(chasing);
                bvs = new ArrayList<BotBallView>();
                setMaze();
                PowerUps.setActivePower(null);
                BotBall.speed = 1.0;
                userBall.speed = 1.0;
                userBall.dx = 0;
                userBall.dy = 0;
//            UserBall.point.setX(UserBall.startingUserBallPoint.getX());
//            UserBall.point.setY(UserBall.startingUserBallPoint.getY());
//            gameScreenPane.setOnMouseClicked(mouseEvent -> {
//                Point point = new Point(mouseEvent.getX(), mouseEvent.getY());
//                Position spot = point.getPosition();
//                System.out.println(spot);
//                Rectangle cell = new Rectangle(spot.getX() * cellWidth, spot.getY() * cellHeight, cellWidth, cellHeight);
//                cells.put(spot, cell);
//                if (!mazeData.canEnter(spot)) {
//                    mazeData.clear(spot);
//                }
//                else {
//                    mazeData.fill(spot);
//                }
//                cell.setFill(mazeData.getColorFor(spot));
//                cell.setStroke(Color.ORANGE);
//                gameScreenPane.getChildren().add(cell);
//            });
            }
            else {
                gameScreenPane.getChildren().clear();
                gameScreenPane.getChildren().add(finalMessageLabel);
                finalMessageLabel.setText("You Lose!");
                finalMessageLabel.setFont(Font.font(72));
                finalMessageLabel.setLayoutX(160);
                finalMessageLabel.setLayoutY(130);
            }
        }

    }
    @FXML
    private void updateViews() {
        UserBallView.update();
        userBall.move();
        ScoreBallView.update();
        for (BotBallView bv: bvs) {
            bv.update();
        }
    }
    public void setMaze() {
       int w = 15;
       int h = 10;
       mazeData = new Puzzle(w, h);
       gameScreenPane.getChildren().clear();
//       cellWidth = gameScreenPane.getWidth() / w;
//       cellHeight = gameScreenPane.getHeight() / h;
        cellWidth = 600 / w;
        cellHeight = 400 / h;
       /*the problem is that gameScreenPane has 0 width and 0 height when setMaze() loads
       in the initialize function, but the values are 600 and 400 when resetButton loads
        */
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                Rectangle cell = new Rectangle(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
                Position spot = new Position(x, y);
                cells.put(spot, cell); // lookup what .put does
                cell.setFill(mazeData.getColorFor(spot));
                cell.setStroke(Color.ORANGE);
                gameScreenPane.getChildren().add(cell);
                if (x == 0 || x == w - 1 || x == 7 || y == 0 || y == h -1 || y == 4 || y == 5) {
                    mazeData.clear(spot);
                    cell.setFill(mazeData.getColorFor(spot));
                }
                if (x == 6 || x == 7 || x == 8) {
                    if (y == 3 || y == 4 || y == 5 || y == 6) {
                        mazeData.clear(spot);
                        cell.setFill(mazeData.getColorFor(spot));

                    }
                }
            }
        }
        mazeData.placeUser(gameScreenPane);
        makeNewEnemy();
        mazeData.placeScoreBall(gameScreenPane);
        timer = new Timer();
        Timer.setTime(0);
        goalTime = 10;
    }

}

