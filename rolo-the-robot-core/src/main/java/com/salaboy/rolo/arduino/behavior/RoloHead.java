/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.arduino.behavior;

import com.salaboy.rolo.api.Servo180;
import com.salaboy.rolo.api.UltraSonicSensor;
import java.util.Map;

/**
 *
 * @author salaboy
 */
public interface RoloHead {

  void setNeck(Servo180 servo180);

  Servo180 getNeck();

  void setDistanceSensor(UltraSonicSensor ultraSonicSensor);

  UltraSonicSensor getDistanceSensor();

  void scan();
  
  Map<Integer, Integer> getLastScan();
  
  void stopScanning();

  void cleanLastScan();
}
