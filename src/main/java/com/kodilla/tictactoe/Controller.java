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
    private final AlertInfo alertInfo = new AlertInfo();
    boolean xIsWin = false;
    boolean oIsWin = false;
    boolean draw = false;
    boolean playerPlayX = true;

    //boolean playerPlayO;
    boolean difficultLevel1;

    public Controller() {
    }

    public void cmpMove() {
        ImageView imgX = new ImageView(imageX);
        ImageView imgO = new ImageView(imageO);
        imgX.setFitWidth(100);
        imgX.setPreserveRatio(true);
        imgO.setFitWidth(100);
        imgO.setPreserveRatio(true);

        if (oIsWin || xIsWin || draw)
            return;

        List<GameTile> listToMove = listOfMoves.stream()
                .filter(tile -> tile.text.getText().equals(""))
                .collect(Collectors.toList());

        //Tu rozkminić kod od zaawansowanego ruchu kompa
        Random generator = new Random();
        int cmpId = generator.nextInt(listToMove.size());
        GameTile cmpTile = listToMove.get(cmpId);

        if(playerPlayX) {
            cmpTile.text.setText("0");
            oTiles.add(cmpTile.idNum);
            cmpTile.getChildren().add(imgO);
            System.out.println(oTiles);
        } else {
            cmpTile.text.setText("X");
            xTiles.add(cmpTile.idNum);
            cmpTile.getChildren().add(imgX);
            System.out.println(xTiles);
        }
        gameCheck();
    }

    public void startPlayer(GameTile gameTile) {
        ImageView imgX = new ImageView(imageX);
        ImageView imgO = new ImageView(imageO);
        imgO.setFitWidth(100);
        imgO.setPreserveRatio(true);
        imgX.setFitWidth(100);
        imgX.setPreserveRatio(true);

        if (!gameTile.text.getText().isEmpty())
            return;
        if (xIsWin || oIsWin || draw)
            return;

        if(playerPlayX) {
            gameTile.text.setText("X");
            xTiles.add(gameTile.idNum);
            gameTile.getChildren().add(imgX);
            System.out.println(xTiles);
        } else {
            gameTile.text.setText("O");
            oTiles.add(gameTile.idNum);
            gameTile.getChildren().add(imgO);
            System.out.println(oTiles);
        }

        gameCheck();
        cmpMove();
    }

    public void gameCheck() {

        if (winSets.comboCheck(xTiles)) {
            alertInfo.setAlert("SpaceX has won!");
            xIsWin = true;
        } else if (winSets.comboCheck(oTiles)) {
            alertInfo.setAlert("WeirdO has won!");
            oIsWin = true;
        } else if ((xTiles.size() == 4 && oTiles.size() == 5) || (xTiles.size() == 5 && oTiles.size() == 4)) {
            alertInfo.setAlert("It's a draw!");
            draw = true;
        }
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
