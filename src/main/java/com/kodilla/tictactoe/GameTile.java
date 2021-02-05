package com.kodilla.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameTile extends StackPane {

    private int idNum;
    private final Text text = new Text();

    public GameTile(Controller controller) {

        Rectangle border = new Rectangle(200, 200);
        border.setFill(null);
        border.setStroke(Color.color(0, 0, 0, 0));
        setAlignment(Pos.CENTER);
        getChildren().addAll(border, text);
        text.setFont(Font.font(0));

        setOnMouseClicked( event -> {
            GameTile src = (GameTile) event.getSource();
            if (src.text.getText().isEmpty()) {
                controller.startPlayer(src);

            }
        });
    }

    public int getIdNum() {
        return idNum;
    }

    public Text getText() {
        return text;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    public void setText(String txt) {
        text.setText(txt);
    }
}
