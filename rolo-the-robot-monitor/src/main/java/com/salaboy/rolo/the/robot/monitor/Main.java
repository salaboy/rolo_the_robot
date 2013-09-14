/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.the.robot.monitor;

import com.salaboy.rolo.the.robot.comm.CommandClientHandler;
import com.salaboy.rolo.the.robot.comm.RoloClientConnector;
import com.salaboy.rolo.the.robot.comm.UIView;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hornetq.api.core.client.ClientSession;
import peasy.PeasyCam;
import processing.core.PApplet;

/**
 *
 * @author salaboy
 */
public class Main extends PApplet implements UIView{

    private RoloClientConnector roloClientConnector;
    private boolean connected = false;
    
    private int positionX = 0;
    private int positionY = 0;

    private int rotationQuarter = 0;

    private int screenWidth = 1280;
    private int screenHeight = 800;
    private PeasyCam cam;

    private String status = "disconnected";
    
    private int wallPosition = 0;

    @Override
    public void setup() {
        size(screenWidth, screenHeight, P3D);
        if (frame != null) {
            frame.setResizable(true);
        }
        cam = new PeasyCam(this, width / 2, height / 2, 0, 100);
        cam.setMinimumDistance(50);
        cam.setMaximumDistance(1000);
        cam.setWheelScale(1.5);
        // exitFullscreen();
        smooth();
        noStroke();
        
        roloClientConnector = new RoloClientConnector("rolo-monitor", new CommandClientHandler(this));
        if (!connected) {
            connected = roloClientConnector.connect("127.0.0.1", 5445);
            if (connected) {
                this.status = "The monitor is connected!";
                
            }
        } else {
            try {
                roloClientConnector.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

    }

    @Override
    public boolean sketchFullScreen() {
        return false;
    }

    private void exitFullscreen() {
        frame.setBounds(0, 0, width, height);
        setBounds((screenWidth - width) / 2, (screenHeight - height) / 2, width, height);
        //frame.setLocation((screenWidth - width) / 2,(screenHeight - height) / 2);
        setLocation((screenWidth - width) / 2, (screenHeight - height) / 2);
    }

    @Override
    public void draw() {
        background(0);
        lights();
        pushMatrix();
        text(status, 100, 100);
        translate((width / 2), height / 2, -100);

        int green = color(0, 200, 0);
        fill(green);
        rect(-width, -height, 2000, 2000);
        popMatrix();

        pushMatrix();

        translate((width / 2) + positionX, height / 2 + positionY, -50);
        int white = color(250, 250, 250);
        fill(white);
//        if (positionX > 50) {
//            pushMatrix();
//            translate(150, 0, 0);
//            box(10, 50, 50);
//            popMatrix();
//        }
//
//        if (positionX < -50) {
//            pushMatrix();
//            translate(-150, 0, 0);
//            box(10, 50, 50);
//            popMatrix();
//        }
//
//        if (positionY > 50) {
//            pushMatrix();
//            translate(0, 150, 0);
//            box(50, 10, 50);
//            popMatrix();
//        }
//
//        if (positionY < - 50) {
//            pushMatrix();
//            translate(0, -150, 0);
//            box(50, 10, 50);
//            popMatrix();
//        }

        if(wallPosition > 0){
            pushMatrix();
            translate(0, wallPosition, 0);
            box(50, 10, 50);
            popMatrix();
        }
        
        rotateZ(radians(45 * rotationQuarter));
        box(100);

        popMatrix();
    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        if (e.isActionKey()) {
            switch (e.getID()) {
                case KeyEvent.KEY_PRESSED:
                    switch (e.getKeyCode()) {
                        case LEFT:
                            positionX -= 5;
                            System.out.println("Position X: " + positionX);
                            break;
                        case RIGHT:
                            positionX += 5;
                            System.out.println("Position X: " + positionX);
                            break;
                        case UP:
                            positionY += 5;
                            System.out.println("positionY : " + positionY);
                            break;
                        case DOWN:
                            positionY -= 5;
                            System.out.println("positionY : " + positionY);
                            break;
                        default:
                            System.out.println("Key code "+ e.getKeyCode());
                            break;

                    }

                    

            }
        }else{
            
            switch (e.getID()) {
                case KeyEvent.KEY_PRESSED:
                    switch (e.getKeyChar()) {
                        case 'q':
                            rotationQuarter += 1;
                            System.out.println("rotationQuarter : " + rotationQuarter);
                            break;
                        case 'e':
                            rotationQuarter -= 1;
                            System.out.println("rotationQuarter : " + rotationQuarter);
                            break;
                        
                    }  
                
            }
        }
    }

    public static void main(String args[]) {
        PApplet.main(new String[]{"--present", "com.salaboy.rolo.the.robot.monitor.Main"});
    }

    public void messageReceived(ClientSession session, Object message, String producerId) throws Exception {
        String[] values = message.toString().split(":");
        
        if (values[0].equals("DISTANCE_REPORT")) {
            positionX = Integer.valueOf(values[1]);
        }
    }

}
