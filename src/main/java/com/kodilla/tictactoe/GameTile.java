package com.kodilla.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameTile extends StackPane {

    int idNum;
    Text text = new Text();

    public GameTile(Controller controller) {

        Rectangle border = new Rectangle(200, 200);
        border.setFill(null);
        border.setStroke(Color.color(0, 0, 0, 0));
        setAlignment(Pos.CENTER);
        getChildren().addAll(border, text);
        text.setFont(Font.font(30));

        setOnMouseClicked( event -> {
            GameTile src = (GameTile) event.getSource();
            controller.startPlayer(src);
        });
    }
}
