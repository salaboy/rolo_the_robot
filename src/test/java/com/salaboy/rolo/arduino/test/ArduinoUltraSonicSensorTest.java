/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.arduino.test;

import com.salaboy.rolo.api.UltraSonicSensor;
import com.salaboy.rolo.arduino.Arduino;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

/**
 *
 * @author salaboy
 */
@RunWith(Arquillian.class)
public class ArduinoUltraSonicSensorTest {

    @Deployment()
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "sim.jar")
                .addPackage("com.salaboy.rolo.api")
                .addPackage("com.salaboy.rolo.arduino")
                .addPackage("com.salaboy.rolo.internals")
                .addPackage("com.salaboy.rolo.arduino.serial")
                .addPackage(" com.salaboy.rolo.model")
                .addAsManifestResource("META-INF/beans.xml", ArchivePaths.create("beans.xml"));

    }
    
    @Inject
    @Arduino
    private UltraSonicSensor ultraSonic;

    @Test
    public void distanceTest() throws InterruptedException {
        assertNotNull(ultraSonic);
        
        while(true){
            Thread.sleep(100);
            System.out.println(" Distance - > "+ultraSonic.readDistance());
        }

    }

    
}
