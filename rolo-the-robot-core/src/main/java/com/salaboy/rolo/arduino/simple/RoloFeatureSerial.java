/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.arduino.simple;
import com.salaboy.rolo.arduino.serial.Serial;
import com.salaboy.rolo.the.robot.comm.HornetQSessionWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author salaboy
 */
public class RoloFeatureSerial {
    private Serial serial;
    private SerialProxy serialProxy;
    private HornetQSessionWriter notifications;
    
    
    public RoloFeatureSerial(String iname, int irate) {
        serialProxy = new SerialProxy();
        this.serial = new Serial(serialProxy, iname, irate);
        this.serial.bufferUntil(";".charAt(0));
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
    }

    public void setNotifications(HornetQSessionWriter notifications) {
        this.notifications = notifications;
    }

    
     // We need a class descended from PApplet so that we can override the
    // serialEvent() method to capture serial data.  We can't use the Arduino
    // class itself, because PApplet defines a list() method that couldn't be
    // overridden by the static list() method we use to return the available
    // serial ports.  This class needs to be public so that the Serial class
    // can access its serialEvent() method.
    public class SerialProxy{

        public SerialProxy() {
            // Create the container for the registered dispose() methods, so that
            // our Serial instance can register its dispose() method (which it does
            // automatically).
            
        }

        public void serialEvent(Serial which) {
            // Notify the Arduino class that there's serial data for it to process.
            while (available() > 0) {
                processInput();
            }
        }
    }
    
    public void dispose() {
        this.serial.dispose();
    }
    
     /**
     * Get a list of the available Arduino boards; currently all serial devices
     * (i.e. the same as Serial.list()). In theory, this should figure out
     * what's an Arduino board and what's not.
     */
    public static String[] list() {
        String[] list = Serial.list();
        for(String item : list){
            System.out.println("Serial things: "+item);
        }
        return list;
    }
    
    private int available() {
        return serial.available();
    }

    private void processInput() {
        String data = serial.readStringUntil(";".charAt(0));
        if(data != null && !data.equals("")){
            System.out.println("DATA: '"+data+"'");
            if(notifications!=null){
                try {
                    notifications.write(data);
                } catch (IOException ex) {
                    Logger.getLogger(RoloFeatureSerial.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        
    }
    
    public void sendMessage(String msg){
        System.out.println("Sending Message: "+msg+";");
        serial.write(msg+";");
    }
    
}
