/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.arduino.serial;
import com.salaboy.rolo.events.HardwareNotificationEvent;
import com.salaboy.rolo.events.BodyEvent;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;


/**
 *
 * @author salaboy
 */
@Singleton
public class RoloFeatureSerial {
    private Serial serial;
    private SerialProxy serialProxy;
    
    
    @Inject
    private Event<HardwareNotificationEvent> hardwareNotificationEvent;

    public RoloFeatureSerial() {
        this( 115200);
    }
    
    private String resolveSerialPort(){
        String[] list = list();
        for(String s : list){
            if(s.startsWith("/dev/tty.usbmodem")){
                System.out.println("Selected Serial Port: "+s);
                return s;
            }
        }
        System.out.println("No Serial Port Found! ");
        return "";
    }
    
    
    public RoloFeatureSerial( int irate) {
        String iname = resolveSerialPort();
        serialProxy = new SerialProxy();
        this.serial = new Serial(serialProxy, iname, irate);
        this.serial.bufferUntil(";".charAt(0));
        
        try {
            list();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
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
     * @return the list of Serial devices
     */
    public static String[] list() {
        return Serial.list();
    }
    
    private int available() {
        return serial.available();
    }

    private void processInput() {
        String data = serial.readStringUntil(";".charAt(0));
        if(data != null && !data.equals("")){
            System.out.println("DATA: '"+data+"'");
            if(hardwareNotificationEvent != null){
                hardwareNotificationEvent.fire(new HardwareNotificationEvent(data));
            }
        }
       
        
    }
    
    public void onMessageEvent(@Observes BodyEvent event){
        sendMessage(event.getPayload());
    }
    
    public synchronized void sendMessage(String msg){
        if(serial.isOutputNull()){
            System.out.println("Serial ON -> Sending Message: "+msg+";");
            serial.write(msg+";");
        }else{
            System.out.println("Serial OFF -> Sending Message: "+msg+";");
        }
    }
    
}
