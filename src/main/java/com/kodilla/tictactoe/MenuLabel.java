package com.kodilla.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class MenuLabel extends GridPane {

    private static final Image imageO = new Image("file:src/main/resources/O_orange.png");
    private static final Image imageX = new Image("file:src/main/resources/X_space.png");
    private static final Image blueBckg = new Image("file:src/main/resources/ButBlue.png");

    private final TextField number = new TextField();
    private boolean playerPlayX = true;
    //private boolean playerPlayO = false;

    public MenuLabel() {
        setPrefSize(500, 500);
        setTranslateX(50);
        setTranslateY(50);
        BackgroundSize bckgSize = new BackgroundSize(500, 500, true, false, true, false);
        BackgroundImage bckgImage = new BackgroundImage(blueBckg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bckgSize);
        setBackground(new Background(bckgImage));

        Label menu = new Label();
        menu.setPrefSize(200, 70);
        BackgroundSize menuBckgSize = new BackgroundSize(200, 70, true, false, true, false);
        Image menuImage = new Image("file:src/main/resources/menuLabel.png");
        BackgroundImage menuBckgImage = new BackgroundImage(menuImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, menuBckgSize);

        menu.setBackground(new Background(menuBckgImage));
        menu.setTranslateX(160);
        menu.setAlignment(Pos.CENTER);

        number.setMaxWidth(40);
        number.setPrefHeight(40);
        number.setTranslateX(350);
        number.setTranslateY(100);

        Label numberOfRounds = new Label("Enter number of rounds:");
        numberOfRounds.setTranslateX(50);
        numberOfRounds.setTranslateY(100);
        numberOfRounds.setFont(Font.font(25));

        Label difficult = new Label("Chose difficult level");
        difficult.setTranslateX(50);
        difficult.setTranslateY(200);
        difficult.setFont(Font.font(25));

        Label figure = new Label("Chose your figure: ");
        figure.setTranslateX(50);
        figure.setTranslateY(300);
        figure.setFont(Font.font(25));

        ImageView imgX = new ImageView(imageX);
        imgX.setFitWidth(40);
        imgX.setPreserveRatio(true);
        ImageView imgO = new ImageView(imageO);
        imgO.setFitWidth(40);
        imgO.setPreserveRatio(true);

        Button xButton = new Button();
        xButton.setPrefSize(40, 40);
        xButton.setTranslateX(290);
        xButton.setTranslateY(300);
        xButton.setStyle("-fx-background-color: transparent;");
        xButton.setGraphic(imgX);

        Button oButton = new Button();
        oButton.setPrefSize(40, 40);
        oButton.setTranslateX(380);
        oButton.setTranslateY(300);
        oButton.setStyle("-fx-background-color: transparent;");
        oButton.setGraphic(imgO);

        oButton.setOnMouseClicked(event -> {
            oButton.setStyle("-fx-border-color: blue;" + "-fx-border-width: 2, 2;" + "-fx-background-color: transparent;");
            playerPlayX = false;
            //playerPlayO = true;
            xButton.setStyle("-fx-background-color: transparent;");
        });

        xButton.setOnMouseClicked(event -> {
            xButton.setStyle("-fx-border-color: blue;" + "-fx-border-width: 2, 2;" + "-fx-background-color: transparent;");
            playerPlayX = true;
            //playerPlayO = false;
            oButton.setStyle("-fx-background-color: transparent;");
        });

        getChildren().addAll(difficult, numberOfRounds, figure, menu, number, xButton, oButton);
    }

    public int getNumberToPlay() {
        String textInput = number.getText();
        try {
            return Integer.parseInt(textInput);
        } catch (Exception e) {
            return 1;
        }
    }

    public boolean isPlayerPlayX() {
        return playerPlayX;
    }

    //public boolean isPlayerPlayO() {
        //return playerPlayO;

}

