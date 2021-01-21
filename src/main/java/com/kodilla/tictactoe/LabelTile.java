package com.kodilla.tictactoe;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class LabelTile extends GridPane {

    private Image imageO = new Image("file:src/main/resources/O_orange.png");
    private Image imageX = new Image("file:src/main/resources/X_space.png");
    private boolean turnX = true;


    public LabelTile() {
        Rectangle border = new Rectangle(200, 200);
        border.setFill(Color.LIGHTBLUE);
        border.setStroke(Color.RED);
        ImageView imgO = new ImageView(imageO);
        imgO.setFitWidth(100);
        imgO.setPreserveRatio(true);
        ImageView imgX = new ImageView(imageX);
        imgX.setFitWidth(100);
        imgX.setPreserveRatio(true);


        setAlignment(Pos.CENTER);
        getChildren().addAll(border);

        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                if (!turnX)
                    return;
                getChildren().add(imgX);
                turnX = false;
            } else if (event.getButton() == MouseButton.SECONDARY) {
                if(turnX)
                    return;
            getChildren().add(imgO);
            turnX = true;
            }
        });
    }


}
