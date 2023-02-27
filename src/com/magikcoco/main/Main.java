package com.magikcoco.main;

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

    private void handleGuiKeyPress(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            String lastPrompt = textArea.getText();
            String[] commands = textField.getText().strip().toLowerCase().split(" ");
            String newPrompt = handleGameCommands(commands);
            if(!newPrompt.equalsIgnoreCase("")){
                textArea.setText(lastPrompt+"\n\n"+newPrompt);
            } else {
                newPrompt = "Command failure";
                textArea.setText(lastPrompt+"\n\n"+newPrompt);
            }
            textField.clear();
        }
    }

    private static String handleGameCommands(String[] commands){
        String returnString = "";
        for(int i = 0; i < commands.length; i++){
            if(commands[i].equalsIgnoreCase("help")){
                //help command
                returnString+= "Available commands are:\n" +
                        "help - display this message\n" +
                        "heading=[up/down/none]:[right/left/none]:[forward/backward/none] - change current heading\n" +
                        "speed=[integer] - change your current speed\n\n";
            } else if(commands[i].substring(0,8).equalsIgnoreCase("heading=")) {
                //heading command
                returnString += "Your heading has been changed to ";
                String[] headingInfo = commands[i].substring(8).split(":");
                int argCounter = 0;
                boolean yFlag = false;
                boolean xFlag = false;
                boolean zFlag = false;
                for(String direction : headingInfo){
                    switch (direction){
                        case "up":
                            if(!yFlag){
                                returnString += "up:";
                                yFlag = true;
                            } else {
                                return "";
                            }
                            argCounter++;
                            break;
                        case "down":
                            if(!yFlag){
                                returnString += "down:";
                                yFlag = true;
                            } else {
                                return "";
                            }
                            argCounter++;
                            break;
                        case "right":
                            if(!xFlag){
                                returnString += "right:";
                                xFlag = true;
                            } else {
                                return "";
                            }
                            argCounter++;
                            break;
                        case "left":
                            if(!xFlag){
                                returnString += "left:";
                                xFlag = true;
                            } else {
                                return "";
                            }
                            argCounter++;
                            break;
                        case "forward":
                            if(!zFlag){
                                returnString += "forward\n\n";
                                zFlag = true;
                            } else {
                                return "";
                            }
                            argCounter++;
                            break;
                        case "backward":
                            if(!zFlag){
                                returnString += "backward\n\n";
                                zFlag = true;
                            } else {
                                return "";
                            }
                            argCounter++;
                            break;
                        case "none":
                            if(argCounter == 0){
                                returnString += "none:";
                                yFlag = true;
                            } else if(argCounter == 1) {
                                returnString += "none:";
                                xFlag = true;
                            } else if(argCounter == 2) {
                                returnString += "none\n\n";
                                zFlag = true;
                            } else {
                                return "";
                            }
                            argCounter++;
                            break;
                        default:
                            return ""; //command failure
                    }
                }
            } else if(commands[i].substring(0,6).equalsIgnoreCase("speed=")) {
                try{
                    Integer newSpeed = Integer.parseInt(commands[i].substring(6));
                    returnString += "Your new speed is "+newSpeed+"\n\n";
                } catch(NumberFormatException e){
                    return "";
                }
            } else {
                return ""; //in case of unrecognized command, total command failure
            }
        }
        //TODO: execute the commands
        return returnString;
    }
}
