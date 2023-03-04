package com.magikcoco.game;

public class CommandHandler {
    public static String handleGameCommands(String[] commands, PlayerCharacter player){
        String returnString = "";
        Thread speed = null;
        boolean speedFlag = false;
        Thread heading = null;
        boolean headingFlag = false;
        try{
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
                    char z = 'n';
                    char y = 'n';
                    char x = 'n';
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
                                    y = 'u';
                                } else {
                                    return "";
                                }
                                argCounter++;
                                break;
                            case "down":
                                if(!yFlag){
                                    returnString += "down:";
                                    yFlag = true;
                                    y = 'd';
                                } else {
                                    return "";
                                }
                                argCounter++;
                                break;
                            case "right":
                                if(!xFlag){
                                    returnString += "right:";
                                    xFlag = true;
                                    x = 'r';
                                } else {
                                    return "";
                                }
                                argCounter++;
                                break;
                            case "left":
                                if(!xFlag){
                                    returnString += "left:";
                                    xFlag = true;
                                    x = 'l';
                                } else {
                                    return "";
                                }
                                argCounter++;
                                break;
                            case "forward":
                                if(!zFlag){
                                    returnString += "forward\n\n";
                                    zFlag = true;
                                    z = 'f';
                                } else {
                                    return "";
                                }
                                argCounter++;
                                break;
                            case "backward":
                                if(!zFlag){
                                    returnString += "backward\n\n";
                                    zFlag = true;
                                    z = 'b';
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
                    char finalY = y;
                    char finalX = x;
                    char finalZ = z;
                    heading = new Thread(()->{
                        player.xHeading = finalX;
                        player.yHeading = finalY;
                        player.zHeading = finalZ;
                    });
                    headingFlag = true;
                } else if(commands[i].substring(0,6).equalsIgnoreCase("speed=")) {
                    try{
                        Integer newSpeed = Integer.parseInt(commands[i].substring(6));
                        returnString += "Your new speed is "+newSpeed+"\n\n";
                        speed = new Thread(()->{
                            player.speed = newSpeed;
                        });
                        speedFlag = true;
                    } catch(NumberFormatException e){
                        return "";
                    }
                } else {
                    return ""; //in case of unrecognized command, total command failure
                }
            }
            //TODO: execute the commands
            if(speedFlag){
                speed.start();
            }
            if(headingFlag){
                heading.start();
            }
            return returnString;
        } catch (Exception e){
            return returnString;
        }
    }
}
