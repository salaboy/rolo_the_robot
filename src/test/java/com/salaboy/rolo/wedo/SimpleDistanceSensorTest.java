/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.wedo;

import com.salaboy.rolo.wedo.api.DistanceSensor;
import com.salaboy.rolo.wedo.api.Motor;
import com.salaboy.rolo.wedo.api.TiltSensor;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author salaboy
 */
@RunWith(Arquillian.class)
public class SimpleDistanceSensorTest {

    @Deployment()
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "we-do.jar")
                .addPackage("com.salaboy.rolo.wedo.api")
                .addPackage("com.salaboy.rolo.wedo.impl")
                .addPackage("com.codeminders.hidapi") // hidapi
                .addAsManifestResource("META-INF/beans.xml", ArchivePaths.create("beans.xml"));

    }
    @Inject
    private DistanceSensor distanceSensor;
    @Inject
    private Motor motor;
    
    @Inject
    private TiltSensor tiltSensor;

    public SimpleDistanceSensorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void farAndNear() throws InterruptedException {
        System.out.println("Reading Distance");
        Thread.sleep(2000);
        
        int readDistance = distanceSensor.readDistance();
        System.out.println("Distance 1= "+readDistance);
        
        Thread.sleep(2000);
        
        int readDistance2 = distanceSensor.readDistance();
        System.out.println("Distance 2 = "+readDistance2);
        
        Thread.sleep(2000);
        
        int readDistance3 = distanceSensor.readDistance();
        System.out.println("Distance 3 = "+readDistance3);
        

        

    }

    @Test
    public void farAndNear2() throws InterruptedException {

        int i = 0;
        boolean motorStarted = false;
        while (true ) {

            distanceSensor.readDistance();
            tiltSensor.readTilt();
            Thread.sleep(1000);

            
//            if(i > 10 && i < 20){
//                if(!motorStarted){
//                    System.out.println("####################################");
//                    System.out.println("#####        Motor Started      ####");
//                    motorStarted = true;
//                    motor.start(120, Motor.DIRECTION.FORWARD);
//                    System.out.println("####################################");
//                }
//            }
//            if(i > 20 && i < 30){
//                if(motorStarted){
//                    System.out.println("####################################");
//                    System.out.println("#####        Motor Stopped      ####");
//                    motor.stop();
//                    motorStarted = false;
//                    System.out.println("####################################");
//                }
//            }
//            if(i > 30 && i < 40){
//                if(!motorStarted){
//                    System.out.println("####################################");
//                    System.out.println("#####        Motor Started      ####");
//                    motor.start(80, Motor.DIRECTION.BACKWARD);
//                    motorStarted = true;
//                    System.out.println("####################################");
//                }
//            }
            i++;
            
        }

    }
}
