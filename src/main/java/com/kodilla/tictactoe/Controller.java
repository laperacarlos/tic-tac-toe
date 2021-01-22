package com.kodilla.tictactoe;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    private static final Image imageO = new Image("file:src/main/resources/O_orange.png");
    private static final Image imageX = new Image("file:src/main/resources/X_space.png");

    private WinCombos winSets = new WinCombos();
    private GridPane root;
    boolean lastX = true;
    private Set<Integer> xTiles = new HashSet<>();
    private Set<Integer> oTiles = new HashSet<>();


    public Controller(GridPane root) {
        this.root = root;
    }

    public void cmpMove() {
        ImageView imgO = new ImageView(imageO);
        imgO.setFitWidth(100);
        imgO.setPreserveRatio(true);

        List<GameTile> listToMove = root.getChildren().stream()
                .filter(tile -> tile instanceof GameTile)
                .map(tile -> ((GameTile) tile))
                .filter(tile -> tile.text.getText().equals(""))
                .collect(Collectors.toList());

        Random generator = new Random();
        int cmpId = generator.nextInt(listToMove.size());
        GameTile cmpTile = listToMove.get(cmpId);

            cmpTile.text.setText("0");
            oTiles.add(cmpTile.idNum);
            cmpTile.getChildren().add(imgO);
            gameCheck();
            lastX = false;

    }

    public void startPlayer(GameTile gameTile) {
        ImageView imgX = new ImageView(imageX);
        imgX.setFitWidth(100);
        imgX.setPreserveRatio(true);

        if (!gameTile.text.getText().isEmpty())
            return;

        gameTile.text.setText("X");
        xTiles.add(gameTile.idNum);
        gameTile.getChildren().add(imgX);
        gameCheck();
        lastX = true;

        cmpMove();
    }

    public void gameCheck() {
        if (xComboCheck() || oComboCheck()) {
            endOfGame();
        }
    }

    public void endOfGame() {
        if (winSets.getWinCombos().contains(xTiles)) {
            System.out.println("Wygrały X");
        } else if (winSets.getWinCombos().contains(oTiles)) {
            System.out.println("Wygrały O");
        } else if ((xTiles.size() == 4 && oTiles.size() == 5) || (xTiles.size() == 5 && oTiles.size() == 4)){
            System.out.println("Remis");
        }
    }

    public boolean xComboCheck() {
        return winSets.getWinCombos().stream()
                .anyMatch(combo -> xTiles.containsAll(combo));
    }

    public boolean oComboCheck() {
        return winSets.getWinCombos().stream()
                .anyMatch(combo -> oTiles.containsAll(combo));
    }




}
