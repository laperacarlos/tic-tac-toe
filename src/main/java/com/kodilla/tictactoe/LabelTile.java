package com.kodilla.tictactoe;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class LabelTile extends GridPane {

    public LabelTile(Controller controller) {
        GridPane labelRoot = new GridPane();
        labelRoot.setPrefSize(200, 600);
        labelRoot.setStyle("-fx-background-color: black;");
        setAlignment(Pos.CENTER);

        Label xWinsLabel = new Label();
        BackgroundSize bckgSize = new BackgroundSize(200, 100, true, false, true , false);
        Image greenButton = new Image("file:src/main/resources/Green_button.png");
        BackgroundImage bckgImage = new BackgroundImage(greenButton, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bckgSize);
        xWinsLabel.setPrefSize(200, 100);
        xWinsLabel.setBackground(new Background(bckgImage));
        xWinsLabel.setText("SpaceX number of wins: ");
        xWinsLabel.setAlignment(Pos.CENTER);
        xWinsLabel.setTranslateX(350);
        xWinsLabel.setTranslateY(25);

        getChildren().add(xWinsLabel);



    }


}
