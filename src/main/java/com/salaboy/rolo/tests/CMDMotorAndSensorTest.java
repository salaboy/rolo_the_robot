/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.tests;

import com.salaboy.rolo.RoloMain;
import com.salaboy.rolo.wedo.api.DistanceSensor;
import com.salaboy.rolo.wedo.api.Motor;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 *
 * @author salaboy java -Djava.library.path=/home/pi/projects/javahidapi/linux/
 * -cp rolo_the_robot-1.0-SNAPSHOT.jar
 * com.salaboy.rolo.tests.CMDMotorAndSensorTest
 *
 */
public class CMDMotorAndSensorTest {

    static boolean readSensors = true;
    static long defaultLatency = 100;

    public static void main(String[] args) throws Exception {
        // create Options object
        Options options = new Options();

        // add t option
        options.addOption("t", true, "sensors latency");
        CommandLineParser parser = new PosixParser();
        CommandLine cmd = parser.parse(options, args);

        String sensorLatency = cmd.getOptionValue("t");
        if (sensorLatency == null) {
            System.out.println(" The Default Latency will be used: " + defaultLatency);
        } else {
            System.out.println(" The Latency will be set to: " + sensorLatency);
            defaultLatency = new Long(sensorLatency);
        }

        System.out.println("Starting Sensor CMD Test ...");

        Weld weld = new Weld();

        WeldContainer container = weld.initialize();

        final Motor motor = container.instance().select(Motor.class).get();
        final DistanceSensor distanceSensor = container.instance().select(DistanceSensor.class).get();

        final Thread t = new Thread() {
            @Override
            public void run() {
                while (readSensors) {
                    System.out.println("Starting Motor ...");
                    motor.start(120, Motor.DIRECTION.FORWARD);
                    try {
                        Thread.sleep(defaultLatency);
                        int readDistance = distanceSensor.readDistance();
                        System.out.println("Distance = " + readDistance);
                        Thread.sleep(defaultLatency);
                        System.out.println("Stopping Motor ...");
                        motor.stop();
                        Thread.sleep(defaultLatency);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(RoloMain.class.getName()).log(Level.SEVERE, null, ex);
                    }


                }
            }
        };
        t.start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Shutdown Hook is running !");
                readSensors = false;
                motor.stop();
            }
        });
    }
}
