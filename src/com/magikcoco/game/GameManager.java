package com.magikcoco.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class GameManager {

    public Map<String, Integer[]> gameCoordsMap;

    public void setBoard(int size){
        Random rand = new Random();
        List<String> gameElements = new ArrayList<>();
        gameElements.add("player");
        gameElements.add("computer");
        int j = 1;
        for(int i = 0; i < (size/100); i += 100){
            gameElements.add("obstacle"+j);
            j++;
        }
        gameCoordsMap = new HashMap<>();
        for(String element : gameElements){
            gameCoordsMap.put(element, new Integer[]{rand.nextInt(size), rand.nextInt(size), rand.nextInt(size)});
        }
    }
}
