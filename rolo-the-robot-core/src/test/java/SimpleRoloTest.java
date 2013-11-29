/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.salaboy.rolo.body.api.Robot;
import com.salaboy.rolo.body.api.RobotSonar;
import com.salaboy.rolo.body.api.RobotFrontWheels;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author salaboy
 */
@RunWith(Arquillian.class)
public class SimpleRoloTest {
    
    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "hello-rolo.jar")
                .addPackages(true, "com.salaboy.rolo")
                .addAsManifestResource("META-INF/beans.xml", ArchivePaths.create("beans.xml")); 

    }
   @Inject
   private Robot rolo;
   
   @Inject
   private RobotFrontWheels roloFrontWheels;
   
   @Inject 
   private RobotSonar roloDistanceSensor1;
   
   
    @Test
    public void helloRolo() throws InterruptedException {
        rolo.setName("Rolo!");
        
        rolo.addRobotPart(roloFrontWheels);
        
        roloDistanceSensor1.setName("sonar1");
        rolo.addRobotPart(roloDistanceSensor1);
        
        //roloFrontWheels.move("backward", 100);
        
        roloDistanceSensor1.readDistance();
        
        Thread.sleep(500);
        
        roloDistanceSensor1.readDistance();
        
        Thread.sleep(500);
        
        roloDistanceSensor1.readDistance();
        
        Thread.sleep(500);
        
        roloDistanceSensor1.readDistance();
        
        Thread.sleep(500);
        
        roloDistanceSensor1.readDistance();
        
       // roloFrontWheels.move("forward", 100);
       
        
        Thread.sleep(5000);
        
        
    }
}
