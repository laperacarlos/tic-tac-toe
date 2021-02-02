package com.kodilla.tictactoe;

import javafx.scene.control.Alert;

public class AlertInfo {
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public AlertInfo() {
    }
    public void setAlert(String message) {
        alert.setHeaderText(message);
        alert.setContentText("Click OK and start next round!");
        alert.showAndWait();
    }
}
