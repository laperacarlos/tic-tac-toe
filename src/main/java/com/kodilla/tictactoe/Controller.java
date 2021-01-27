package com.kodilla.tictactoe;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    private static final Image imageO = new Image("file:src/main/resources/O_orange.png");
    private static final Image imageX = new Image("file:src/main/resources/X_space.png");
    private final WinCombos winSets = new WinCombos();
    private final List<GameTile> listOfMoves = new ArrayList<>();
    private final Set<Integer> xTiles = new HashSet<>();
    private final Set<Integer> oTiles = new HashSet<>();
    boolean xIsWin = false;
    boolean oIsWin = false;
    boolean draw = false;

    public Controller() {
    }

    public void cmpMove() {
        ImageView imgO = new ImageView(imageO);
        imgO.setFitWidth(100);
        imgO.setPreserveRatio(true);

        if (oIsWin||xIsWin||draw)
            return;

        List<GameTile> listToMove = listOfMoves.stream()
                .filter(tile -> tile.text.getText().equals(""))
                .collect(Collectors.toList());

        Random generator = new Random();
        int cmpId = generator.nextInt(listToMove.size());
        GameTile cmpTile = listToMove.get(cmpId);

        cmpTile.text.setText("0");
        oTiles.add(cmpTile.idNum);
        cmpTile.getChildren().add(imgO);
        System.out.println(oTiles);

        gameCheck();
    }

    public void startPlayer(GameTile gameTile) {
        ImageView imgX = new ImageView(imageX);
        imgX.setFitWidth(100);
        imgX.setPreserveRatio(true);

        if (!gameTile.text.getText().isEmpty())
            return;
        if (xIsWin||oIsWin||draw)
            return;

        gameTile.text.setText("X");
        xTiles.add(gameTile.idNum);
        gameTile.getChildren().add(imgX);
        System.out.println(xTiles);
        gameCheck();

        cmpMove();
    }

    public void gameCheck() {
        if (comboCheck(xTiles) || comboCheck(oTiles)) {
            if (comboCheck(xTiles)) {
                System.out.println("Wygrały X");
                xIsWin = true;
            } else if (comboCheck(oTiles)) {
                System.out.println("Wygrały O");
                oIsWin= true;
            }
        } else if ((xTiles.size() == 4 && oTiles.size() == 5) || (xTiles.size() == 5 && oTiles.size() == 4)) {
            System.out.println("Remis");
            draw = true;
        }
    }

    public boolean comboCheck(Set<Integer> tiles) {
        return winSets.getWinCombos().stream()
                .anyMatch(tiles::containsAll);
    }

    public void resetGame() {
        xTiles.clear();
        oTiles.clear();
        listOfMoves.clear();
    }

    public void nextRound() {
        xTiles.clear();
        oTiles.clear();
        listOfMoves.forEach(t -> t.text.setText(""));
    }

    public void addTileToListOfMoves(GameTile gameTile) {
        listOfMoves.add(gameTile);
    }
}
