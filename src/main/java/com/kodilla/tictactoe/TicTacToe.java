package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    GridPane root;
    Controller controller;

    private Parent gameScene() {
        root = new GridPane();
        controller = new Controller(root);
        Image bckg = new Image("file:src/main/resources/TTT_table.jpg");
        BackgroundSize bckgSize = new BackgroundSize(600, 600, true, true, true , false);
        BackgroundImage bckgImage = new BackgroundImage(bckg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bckgSize);
        root.setPrefSize(600, 800);
        root.setBackground(new Background(bckgImage));

        int tileCounter = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                GameTile gameTile = new GameTile(controller);
                gameTile.setTranslateX(j * 200);
                gameTile.setTranslateY(i * 200);
                tileCounter++;
                gameTile.idNum = tileCounter;

                root.getChildren().add(gameTile);
            }
        }
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(new Scene(gameScene()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
