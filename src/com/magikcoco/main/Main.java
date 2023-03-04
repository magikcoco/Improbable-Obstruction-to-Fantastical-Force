package com.magikcoco.main;

import com.magikcoco.game.CommandHandler;
import com.magikcoco.game.GameManager;
import com.magikcoco.game.PlayerCharacter;
import javafx.application.Application;
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

    private static PlayerCharacter player;
    private TextArea textArea;
    private TextField textField;

    @Override
    public void start(Stage stage) throws Exception{
        double height = 480.0;
        double width = 640.0;

        textArea = new TextArea();
        textArea.setPrefHeight(height-40);
        textArea.setPrefWidth(width-15);
        textArea.setLayoutY(7.5);
        textArea.setLayoutX(7.5);
        textArea.setDisable(true);

        textField = new TextField();
        textField.setPrefHeight(25);
        textField.setPrefWidth(width-15);
        textField.setLayoutY(textArea.getPrefHeight()+10);
        textField.setLayoutX(7.5);
        textField.setOnKeyPressed(event -> {
            handleGuiKeyPress(event);
        });

        textArea.setText("Report: No information to display\n");

        AnchorPane root = new AnchorPane();
        root.getChildren().add(textArea);
        root.getChildren().add(textField);

        GameManager.setGuiDisplay(textArea);
        GameManager.setGuiPrompt(textField);

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


    public static void main(String[] args) {
        int[] coords = GameManager.randomCoordinates(100);
        player = new PlayerCharacter(coords[0], coords[1], coords[2]);
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

    private void handleGuiKeyPress(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            String lastPrompt = textArea.getText();
            String[] commands = textField.getText().strip().toLowerCase().split(" ");
            String newPrompt = CommandHandler.handleGameCommands(commands, player);
            if(!newPrompt.equalsIgnoreCase("")){
                textArea.setText(lastPrompt+"\n\n"+newPrompt);
            } else {
                newPrompt = "Command failure";
                textArea.setText(lastPrompt+"\n\n"+newPrompt);
            }
            textField.clear();
        }
    }
}
