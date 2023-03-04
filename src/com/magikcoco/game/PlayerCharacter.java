package com.magikcoco.game;

public class PlayerCharacter {
    public int xPos;
    public int yPos;
    public int zPos;
    public int speed;
    public char xHeading;
    public char yHeading;
    public char zHeading;

    public PlayerCharacter(int xPos, int yPos, int zPos){
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
        speed = 10;
        xHeading = 'n';
        yHeading = 'n';
        zHeading = 'n';
        Thread reporter = new Thread(()->{
            reporter();
        });
        Thread positionUpdater = new Thread(()->{
            positionUpdater();
        });
        reporter.start();
        positionUpdater.start();
    }

    private void reporter(){
        while(true){
            String report = "PlayerCharacter: ";
            report += "heading="+xHeading+":"+yHeading+":"+zHeading+" ";
            report += "speed="+speed+" ";
            report += "position="+xPos+":"+yPos+":"+zPos+" ";
            GameManager.reportHandlerGui(report);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("interupted");
            }
        }
    }

    private void positionUpdater(){
        while(true){
            if(xHeading == 'r'){
                xPos+=speed;
            } else if(xHeading == 'l'){
                xPos-=speed;
            }
            if(yHeading == 'u'){
                yPos+=speed;
            } else if(yHeading == 'd'){
                yPos-=speed;
            }
            if(zHeading == 'f'){
                zPos+=speed;
            } else if(zHeading == 'b'){
                zPos-=speed;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("interupted");
            }
        }
    }
}
