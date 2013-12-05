/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.salaboy.rolo.body.api.Robot;
import com.salaboy.rolo.body.api.RobotFrontWheels;
import com.salaboy.rolo.body.api.RobotSonars;
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
   private RobotSonars roloSonars;
   
   
    @Test
    public void helloRolo() throws InterruptedException {
        rolo.setName("Rolo!");
        
        rolo.addRobotPart(roloFrontWheels);
        
        rolo.addRobotPart(roloSonars);
        
        //roloFrontWheels.move("backward", 100);
        
        roloSonars.readAll();
        
        Thread.sleep(500);
        
        roloSonars.readAll();
        
        Thread.sleep(500);
        
        roloSonars.readAll();
        
        Thread.sleep(500);
        
       
     
        
        
    }
}
