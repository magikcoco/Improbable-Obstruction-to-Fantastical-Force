package com.magikcoco.main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        int height = 480;
        int width = 640;
        Group root = new Group();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.setTitle("Improbable Obstruction to Fantastical Force");
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("# ");
                String input = br.readLine();
                switch(input.toLowerCase()){
                    case "exit":
                        handleExit();
                        break;
                    case "play":
                        handlePlay();
                        break;
                    case "guiplay":
                        //launch is blocking the thread
                        launch(args);
                        break;
                    case "help":
                        handleHelp();
                        break;
                    default:
                        System.out.println("'help' for command options");
                        break;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void handlePlay(){
        System.out.println("wip");
    }

    private static void handleExit(){
        System.exit(0);
    }

    private static void handleHelp(){
        System.out.println("COMMAND OPTIONS:\n" +
                "exit - exits application\n" +
                "play - start a game in the console\n" +
                "guiplay - start a game in a new window\n" +
                "help - display this command list");
    }
}
