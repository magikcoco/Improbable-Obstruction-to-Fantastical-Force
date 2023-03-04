package com.magikcoco.game;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.*;

public class GameManager {

    public static Map<String, Integer[]> gameCoordsMap;
    private static TextArea guiDisplay;
    private static TextField guiPrompt;

    public static void setBoard(int size){
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

    public static int[] randomCoordinates(int bound){
        Random rand = new Random();
        return new int[]{rand.nextInt(bound), rand.nextInt(bound), rand.nextInt(bound)};
    }

    public static void reportHandlerGui(String report){
        if(guiDisplay != null){
            String[] displayText = guiDisplay.getText().split("\n");
            displayText[0] = report;
            guiDisplay.setText(String.join("\n\n", Arrays.stream(displayText).filter(s -> !s.isEmpty()).toArray(String[]::new)));
        }
    }

    public static void setGuiPrompt(TextField tf){
        guiPrompt = tf;
    }

    public static void setGuiDisplay(TextArea ta){
        guiDisplay = ta;
    }
}
