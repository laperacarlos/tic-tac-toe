package com.kodilla.tictactoe;

import java.util.*;

public class WinCombos {

    private final List<Set<Integer>> winCombos = new ArrayList<>();

    public WinCombos() {
        winCombos.add(new HashSet<>(Arrays.asList(1, 2, 3)));
        winCombos.add(new HashSet<>(Arrays.asList(4, 5, 6)));
        winCombos.add(new HashSet<>(Arrays.asList(7, 8, 9)));
        winCombos.add(new HashSet<>(Arrays.asList(1, 4, 7)));
        winCombos.add(new HashSet<>(Arrays.asList(2, 5, 8)));
        winCombos.add(new HashSet<>(Arrays.asList(3, 6, 9)));
        winCombos.add(new HashSet<>(Arrays.asList(1, 5, 9)));
        winCombos.add(new HashSet<>(Arrays.asList(3, 5, 7)));
    }

    public List<Set<Integer>> getWinCombos() {
        return winCombos;
    }
}
