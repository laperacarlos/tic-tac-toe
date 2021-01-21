package com.kodilla.tictactoe;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    private Image imageO = new Image("file:src/main/resources/O_orange.png");
    private Image imageX = new Image("file:src/main/resources/X_space.png");

    GridPane root;
    boolean lastX = true;
    Set<Integer> xTiles = new HashSet<>();
    Set<Integer> oTiles = new HashSet<>();
    List<Set<Integer>> winCombos = new ArrayList<>();


    public Controller(GridPane root) {
        this.root = root;
        Set<Integer> winSet1 = new HashSet<>();
        winSet1.add(1);
        winSet1.add(2);
        winSet1.add(3);
        Set<Integer> winSet2 = new HashSet<>();
        winSet2.add(4);
        winSet2.add(5);
        winSet2.add(6);
        Set<Integer> winSet3 = new HashSet<>();
        winSet3.add(7);
        winSet3.add(8);
        winSet3.add(9);
        Set<Integer> winSet4 = new HashSet<>();
        winSet4.add(1);
        winSet4.add(4);
        winSet4.add(7);
        Set<Integer> winSet5 = new HashSet<>();
        winSet5.add(2);
        winSet5.add(5);
        winSet5.add(8);
        Set<Integer> winSet6 = new HashSet<>();
        winSet6.add(3);
        winSet6.add(6);
        winSet6.add(9);
        Set<Integer> winSet7 = new HashSet<>();
        winSet7.add(1);
        winSet7.add(5);
        winSet7.add(9);
        Set<Integer> winSet8 = new HashSet<>();
        winSet8.add(3);
        winSet8.add(5);
        winSet8.add(7);
        winCombos.add(winSet1);
        winCombos.add(winSet2);
        winCombos.add(winSet3);
        winCombos.add(winSet4);
        winCombos.add(winSet5);
        winCombos.add(winSet6);
        winCombos.add(winSet7);
        winCombos.add(winSet8);
        System.out.println(winCombos.size());
        System.out.println(winSet4.size());
    }

    public void cmpMove() {
        ImageView imgX = new ImageView(imageX);
        ImageView imgO = new ImageView(imageO);
        imgX.setFitWidth(100);
        imgX.setPreserveRatio(true);
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

    public boolean tileCheck(GameTile gameTile) {
        boolean result = xTiles.contains(gameTile.idNum) || oTiles.contains(gameTile.idNum);
            return result;
    }


    public void startPlayer(GameTile gameTile) {
        ImageView imgX = new ImageView(imageX);
        ImageView imgO = new ImageView(imageO);
        imgX.setFitWidth(100);
        imgX.setPreserveRatio(true);
        imgO.setFitWidth(100);
        imgO.setPreserveRatio(true);

        if(!tileCheck(gameTile)) {

            gameTile.text.setText("X");
            xTiles.add(gameTile.idNum);
            gameTile.getChildren().add(imgX);
            gameCheck();
            lastX = true;
        }
    }

    public void gameCheck() {
        if (xComboCheck() || oComboCheck()) {

            endOfGame();
        }
    }

    public void endOfGame() {
        if (winCombos.contains(xTiles)) {
            System.out.println("Wygrały X");
        } else if (winCombos.contains(oTiles)) {
            System.out.println("Wygrały O");
        } else {
            System.out.println("Remis");
        }
    }

    public boolean xComboCheck() {
        return winCombos.stream()
                .anyMatch(combo -> xTiles.containsAll(combo));
    }

    public boolean oComboCheck() {
        return winCombos.stream()
                .anyMatch(combo -> oTiles.containsAll(combo));
    }




}
