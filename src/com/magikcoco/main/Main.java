package com.magikcoco.main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        double height = 480.0;
        double width = 640.0;

        TextArea textArea = new TextArea();
        textArea.setPrefHeight(height-40);
        textArea.setPrefWidth(width-15);
        textArea.setLayoutY(7.5);
        textArea.setLayoutX(7.5);
        textArea.setDisable(true);

        TextField textField = new TextField();
        textField.setPrefHeight(25);
        textField.setPrefWidth(width-15);
        textField.setLayoutY(textArea.getPrefHeight()+10);
        textField.setLayoutX(7.5);
        textField.setOnKeyPressed(event -> {
            handleGuiKeyPress(event, textField, textArea);
        });

        AnchorPane root = new AnchorPane();
        root.getChildren().add(textArea);
        root.getChildren().add(textField);

        Scene scene = new Scene(root, width, height);

        stage.setScene(scene);
        stage.setTitle("Improbable Obstruction to Fantastical Force");
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            System.out.println("Closing application...");
            System.exit(0);
        });

        stage.show();
    }

    private void handleGuiKeyPress(KeyEvent event, TextField textField, TextArea textArea) {
        if(event.getCode() == KeyCode.ENTER){
            textArea.clear();
            textArea.setText(textField.getText());
            textField.clear();
        }
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
