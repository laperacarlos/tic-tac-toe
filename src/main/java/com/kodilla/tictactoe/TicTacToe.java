package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    Pane rootGameScene;
    Controller controller;

    private final Image bckg = new Image("file:src/main/resources/TTT_table.jpg");
    private final BackgroundSize bckgSize = new BackgroundSize(600, 600, true, true, true , false);
    private final BackgroundImage bckgImage = new BackgroundImage(bckg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bckgSize);

    private final Button resetButton = new Button("New game");
    private final BackgroundSize resetBtnSize = new BackgroundSize(200, 100, true, false, true , false);
    private final Image greenButton = new Image("file:src/main/resources/Green_button.png");
    private final BackgroundImage resetBtnImage = new BackgroundImage(greenButton, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, resetBtnSize);

    private final Label xWinsLabel = new Label();
    private final Label oWinsLabel = new Label();
    private final Label drawsLabel = new Label();

    private int xWins = 0;
    private int oWins = 0;
    private int draws = 0;

    void cleanUp() {
        rootGameScene.getChildren().clear();
        controller.resetGame();

    }
    void nextRound () {
        controller.nextRound();
    }

    void restart(Stage stage) {
       // cleanUp();
        if (controller.xIsWin) {
            xWins++;
        } else if (controller.oIsWin) {
            oWins++;
        } else if (controller.draw) {
            draws++;
        }
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

                rootGameScene.getChildren().addAll(gameTile);
                controller.addTileToListOfMoves(gameTile);
            }
        }
        rootGameScene.setTranslateX(0);
        rootGameScene.setTranslateY(0);

        resetButton.setPrefSize(200, 100);
        resetButton.setBackground(new Background(resetBtnImage));
        resetButton.setTranslateX(50);
        resetButton.setTranslateY(650);

        resetButton.setOnAction( e -> {
            //controller.resetGame();
            restart(stage);
            //nextRound();
        });
        rootGameScene.getChildren().add(resetButton);

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


        Scene scene = new Scene(rootGameScene, 600, 800);

        stage.setTitle("Tic-Tac-Toe");
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        startGame(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
