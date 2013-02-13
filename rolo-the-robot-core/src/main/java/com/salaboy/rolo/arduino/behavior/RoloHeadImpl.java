/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.arduino.behavior;

import com.salaboy.rolo.api.Servo180;
import com.salaboy.rolo.api.UltraSonicSensor;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author salaboy
 */
public class RoloHeadImpl implements RoloHead{
  
  private Servo180 neck;
  private UltraSonicSensor distanceSensor;
  private static final Map<Integer, Integer> lastScan = new HashMap<Integer, Integer>();
  private static boolean scanning = false;
  @Override
  public void scan() {
    new Thread(new Runnable() {
            @Override
            public void run() {
              scanning = true;
              while(scanning){
                for(int i = 1; i < 180 && scanning; i += 10){
                  neck.rotate(i);
                  lastScan.put(neck.getCurrentDegree(), distanceSensor.readDistance());
                  try {
                    Thread.sleep(200);
                  } catch (InterruptedException ex) {
                    Logger.getLogger(RoloHeadImpl.class.getName()).log(Level.SEVERE, null, ex);
                  }
                }
              }
            }
    }).start();
  }

  @Override
  public Servo180 getNeck() {
    return neck;
  }

  @Override
  public void setNeck(Servo180 neck) {
    this.neck = neck;
  }

  public UltraSonicSensor getDistanceSensor() {
    return distanceSensor;
  }

  public void setDistanceSensor(UltraSonicSensor distance) {
    this.distanceSensor = distance;
  }

  @Override
  public Map<Integer, Integer> getLastScan() {
    return lastScan;
  }

  @Override
  public void stopScanning() {
    scanning = false;
  }

  @Override
  public void cleanLastScan() {
    lastScan.clear();
  }
 
}
