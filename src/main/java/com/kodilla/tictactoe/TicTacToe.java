package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private Pane rootGameScene;
    private Controller controller;

    private final MenuLabel menuLabel = new MenuLabel();

    private final BackgroundSize playBckgSize = new BackgroundSize(200, 70, true, false, true, false);
    private final Image playImage = new Image("file:src/main/resources/playNow.png");
    private final BackgroundImage playBckgImage = new BackgroundImage(playImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, playBckgSize);


    private final Image bckg = new Image("file:src/main/resources/TTT_table.jpg");
    private final BackgroundSize bckgSize = new BackgroundSize(600, 600, true, true, true , false);
    private final BackgroundImage bckgImage = new BackgroundImage(bckg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bckgSize);

    private final Button nxtRoundButton = new Button("Next Round");
    private final BackgroundSize nxtRoundBtnSize = new BackgroundSize(200, 100, true, false, true , false);
    private final Image greenButton = new Image("file:src/main/resources/Green_button.png");
    private final BackgroundImage nxtRoundBtnImage = new BackgroundImage(greenButton, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, nxtRoundBtnSize);

    private final Button newGameButton = new Button();
    private final BackgroundSize newGameBtnSize = new BackgroundSize(200, 100, true, false, true , false);
    private final Image blueButton = new Image("file:src/main/resources/newGameButton.png");
    private final BackgroundImage newGameBtnImage = new BackgroundImage(blueButton, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, newGameBtnSize);

    private final Label winLabelBlue = new Label();
    private final BackgroundSize winLabelBlueSize = new BackgroundSize(500, 500, true, false, true , false);
    private final Image blueLabel = new Image("file:src/main/resources/ButBlue.png");
    private final BackgroundImage winLabelBlueImage = new BackgroundImage(blueLabel, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, winLabelBlueSize);

    private final Label xWinsLabel = new Label();
    private final Label oWinsLabel = new Label();
    private final Label drawsLabel = new Label();

    private final Button playButton = new Button();

    private int xWins = 0;
    private int oWins = 0;
    private int draws = 0;
    private int numberOfGames = 0;
    boolean playerPlayX;
    boolean level1;

    boolean roundCheck() {
        return xWins + oWins + draws == numberOfGames;
    }

    private void nextGame (Stage stage) {
        oWins = 0;
        xWins = 0;
        draws = 0;
        startGame(stage);
    }

    private void pointsCounter() {
        if (controller.isxIsWin()) {
            xWins++;
        } else if (controller.isoIsWin()) {
            oWins++;
        } else if (controller.isDraw()) {
            draws++;
        }
    }

    private void nextRound(Stage stage) {
        startGame(stage);
    }

    void startGame(Stage stage) {
        rootGameScene = new Pane();
        controller = new Controller();

        rootGameScene.setPrefSize(600, 800);
        rootGameScene.setBackground(new Background(bckgImage));

        int tileMarker = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                GameTile gameTile = new GameTile(controller);
                gameTile.setTranslateX(j * 200);
                gameTile.setTranslateY(i * 200);
                tileMarker++;
                gameTile.idNum = tileMarker;
                gameTile.setDisable(true);

                rootGameScene.getChildren().addAll(gameTile);
                controller.addTileToListOfMoves(gameTile);
                controller.addTileToMapOfMoves(tileMarker, gameTile);
            }
        }

        winLabelBlue.setPrefSize(500, 500);
        winLabelBlue.setBackground(new Background(winLabelBlueImage));
        winLabelBlue.setTranslateX(50);
        winLabelBlue.setTranslateY(50);
        winLabelBlue.setFont(new Font("Arial", 30));
        winLabelBlue.setTextAlignment(TextAlignment.CENTER);
        winLabelBlue.setTextFill(Color.GREENYELLOW);
        winLabelBlue.setAlignment(Pos.CENTER);

        nxtRoundButton.setPrefSize(200, 100);
        nxtRoundButton.setBackground(new Background(nxtRoundBtnImage));
        nxtRoundButton.setTranslateX(50);
        nxtRoundButton.setTranslateY(600);
        nxtRoundButton.setDisable(true);
        nxtRoundButton.setAlignment(Pos.CENTER);
        nxtRoundButton.setOnAction( e -> {
            pointsCounter();
            xWinsLabel.setText("SpaceX number of wins: " + xWins);
            oWinsLabel.setText("WirdO number of wins: " + oWins);
            drawsLabel.setText("Number of draws: " + draws);

            if (roundCheck()) {
                if (xWins > oWins) {
                    winLabelBlue.setText("SpaceX has won the game!!!\nIf you want to play again press \n\"NEW GAME\"!");
                } else if (oWins > xWins) {
                    winLabelBlue.setText("WeirdO has won the game!!!\nIf you want to play again press \n\"NEW GAME\"!");
                } else {
                    winLabelBlue.setText("The game ends with a draw!\nIf you want to play again press \n\"NEW GAME\"!");
                }
                rootGameScene.getChildren().add(winLabelBlue);
                nxtRoundButton.setDisable(true);
                rootGameScene.getChildren().stream()
                        .filter(tile -> tile instanceof GameTile)
                        .map(tile -> ((GameTile) tile))
                        .forEach(tile -> tile.setDisable(true));
            } else {
                nextRound(stage);
                rootGameScene.getChildren().stream()
                        .filter(tile -> tile instanceof GameTile)
                        .map(tile -> ((GameTile) tile))
                        .forEach(tile -> tile.setDisable(false));
                controller.playerPlayX = playerPlayX;
                controller.level1 = level1;
                nxtRoundButton.setDisable(false);
            }
        });

        newGameButton.setPrefSize(200, 100);
        newGameButton.setBackground(new Background(newGameBtnImage));
        newGameButton.setAlignment(Pos.CENTER);
        newGameButton.setTranslateX(50);
        newGameButton.setTranslateY(700);
        newGameButton.setOnAction( e -> {
            nextGame(stage);
            menuLabel.setVisible(true);
            playButton.setVisible(true);
        });

        playButton.setBackground(new Background(playBckgImage));
        playButton.setPrefSize(200, 70);
        playButton.setTranslateY(450);
        playButton.setTranslateX(220);
        playButton.setAlignment(Pos.CENTER);
        playButton.setOnAction( event -> {
            numberOfGames = menuLabel.getNumberToPlay();
            playerPlayX = menuLabel.isPlayerPlayX();
            controller.playerPlayX = playerPlayX;
            level1 = menuLabel.isLevel1();
            controller.level1 = level1;
            playButton.setVisible(false);
            menuLabel.setVisible(false);
            nxtRoundButton.setDisable(false);
            rootGameScene.getChildren().stream()
                    .filter(tile -> tile instanceof GameTile)
                    .map(tile -> ((GameTile) tile))
                    .forEach(tile -> tile.setDisable(false));
        });

        xWinsLabel.setPrefSize(200, 100);
        xWinsLabel.setTranslateX(400);
        xWinsLabel.setTranslateY(600);
        xWinsLabel.setText("SpaceX number of wins: " + xWins);

        oWinsLabel.setPrefSize(200, 100);
        oWinsLabel.setTranslateX(400);
        oWinsLabel.setTranslateY(650);
        oWinsLabel.setText("WirdO number of wins: " + oWins);

        drawsLabel.setPrefSize(200, 100);
        drawsLabel.setTranslateX(400);
        drawsLabel.setTranslateY(700);
        drawsLabel.setText("Number of draws: " + draws);

        rootGameScene.getChildren().add(xWinsLabel);
        rootGameScene.getChildren().add(oWinsLabel);
        rootGameScene.getChildren().add(drawsLabel);
        rootGameScene.getChildren().add(nxtRoundButton);
        rootGameScene.getChildren().add(menuLabel);
        rootGameScene.getChildren().add(playButton);
        rootGameScene.getChildren().add(newGameButton);

        Scene scene = new Scene(rootGameScene, 600, 800);

        stage.setTitle("Tic-Tac-Toe");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       try {
           startGame(primaryStage);
       } catch (Exception e) {
           throw new Exception();
       }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
