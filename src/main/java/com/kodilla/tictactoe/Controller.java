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
    private final Map<Integer, GameTile> mapListOfMoves = new HashMap<>();
    private boolean xIsWin;
    private boolean oIsWin;
    private boolean draw;
    private boolean playerPlayX = true;
    private boolean level1;

    public Controller() {
    }

    private void cmpMove() {
        ImageView imgX = new ImageView(imageX);
        ImageView imgO = new ImageView(imageO);
        imgX.setFitWidth(100);
        imgX.setPreserveRatio(true);
        imgO.setFitWidth(100);
        imgO.setPreserveRatio(true);

        if (oIsWin || xIsWin || draw)
            return;

        GameTile cmpTile = level0Move();

        if (level1) {
           cmpTile = level1Move();
        }

        if(playerPlayX) {
            cmpTile.setText("0");
            oTiles.add(cmpTile.getIdNum());
            cmpTile.getChildren().add(imgO);
            System.out.println(oTiles);
        } else {
            cmpTile.setText("X");
            xTiles.add(cmpTile.getIdNum());
            cmpTile.getChildren().add(imgX);
            System.out.println(xTiles);
        }
        mapListOfMoves.remove(cmpTile.getIdNum());
        gameCheck();
    }

    public void startPlayer(GameTile gameTile) {
        ImageView imgX = new ImageView(imageX);
        ImageView imgO = new ImageView(imageO);
        imgO.setFitWidth(100);
        imgO.setPreserveRatio(true);
        imgX.setFitWidth(100);
        imgX.setPreserveRatio(true);

        if (!gameTile.getText().getText().isEmpty())
            return;
        if (xIsWin || oIsWin || draw)
            return;

        if(playerPlayX) {
            gameTile.setText("X");
            xTiles.add(gameTile.getIdNum());
            gameTile.getChildren().add(imgX);
            System.out.println(xTiles);
        } else {
            gameTile.setText("O");
            oTiles.add(gameTile.getIdNum());
            gameTile.getChildren().add(imgO);
            System.out.println(oTiles);
        }
        mapListOfMoves.remove(gameTile.getIdNum());
        gameCheck();
        cmpMove();
    }

    private void gameCheck() {

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

    private GameTile level0Move() {
        List<GameTile> listToMove = listOfMoves.stream()
                .filter(tile -> tile.getText().getText().equals(""))
                .collect(Collectors.toList());

        Random generator = new Random();
        int cmpId = generator.nextInt(listToMove.size());
        return listToMove.get(cmpId);
    }

    private GameTile level1Move() {
        Set<Integer> tileList;
        if (playerPlayX) {
            tileList = oTiles;
        } else {
            tileList = xTiles;
        }

        GameTile cmpTile = null;
        if (mapListOfMoves.containsKey(5)) {
            cmpTile = mapListOfMoves.get(5);
        } else if(mapListOfMoves.containsKey(1)) {
            cmpTile = mapListOfMoves.get(1);
        }  else if(mapListOfMoves.containsKey(9) && tileList.contains(5) && tileList.contains(1)) {
            cmpTile = mapListOfMoves.get(9);
        } else if(mapListOfMoves.containsKey(2)) {
            cmpTile = mapListOfMoves.get(2);
        } else if(mapListOfMoves.containsKey(3) && tileList.contains(1) && tileList.contains(2)) {
            cmpTile = mapListOfMoves.get(3);
        } else if(mapListOfMoves.containsKey(8) && tileList.contains(5) && tileList.contains(2)) {
            cmpTile = mapListOfMoves.get(8);
        } else if(mapListOfMoves.containsKey(7) && tileList.contains(1) && tileList.contains(4)) {
            cmpTile = mapListOfMoves.get(7);
        } else if(mapListOfMoves.containsKey(6) && tileList.contains(4) && tileList.contains(5)) {
            cmpTile = mapListOfMoves.get(6);
        }else if(mapListOfMoves.containsKey(4)) {
            cmpTile = mapListOfMoves.get(4);
        } else if(mapListOfMoves.containsKey(3)) {
            cmpTile = mapListOfMoves.get(3);
        } else if(mapListOfMoves.containsKey(7)) {
            cmpTile = mapListOfMoves.get(7);
        } else if(mapListOfMoves.containsKey(6)) {
            cmpTile = mapListOfMoves.get(6);
        } else if(mapListOfMoves.containsKey(8)) {
            cmpTile = mapListOfMoves.get(8);
        } else if(mapListOfMoves.containsKey(9)) {
            cmpTile = mapListOfMoves.get(9);
        }
        return cmpTile;
    }

    public void addTileToListOfMoves(GameTile gameTile) {
        listOfMoves.add(gameTile);
    }

    public void addTileToMapOfMoves(Integer number, GameTile gameTile) {
        mapListOfMoves.put(number, gameTile);
    }

    public boolean isxIsWin() {
        return xIsWin;
    }

    public boolean isoIsWin() {
        return oIsWin;
    }

    public boolean isDraw() {
        return draw;
    }

    public void setPlayerPlayX(boolean playerPlayX) {
        this.playerPlayX = playerPlayX;
    }

    public void setLevel1(boolean level1) {
        this.level1 = level1;
    }
}
