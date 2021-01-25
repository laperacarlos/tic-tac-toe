package com.kodilla.tictactoe;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class LabelTile extends GridPane {

    public LabelTile() {
        GridPane labelRoot = new GridPane();
        labelRoot.setPrefSize(200, 600);
        labelRoot.setStyle("-fx-background-color: black;");
        setAlignment(Pos.CENTER);

        Button resetButton = new Button();
        BackgroundSize bckgSize = new BackgroundSize(200, 100, true, false, true , false);
        Image greenButton = new Image("file:src/main/resources/Green_button.png");
        BackgroundImage bckgImage = new BackgroundImage(greenButton, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bckgSize);
        resetButton.setPrefSize(200, 100);
        resetButton.setBackground(new Background(bckgImage));
        resetButton.setText("New game");
        resetButton.setTranslateX(50);
        resetButton.setTranslateY(50);
        getChildren().add(resetButton);



    }


}
