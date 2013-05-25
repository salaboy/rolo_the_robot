/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.sim;

import com.salaboy.rolo.api.DistanceSensor;
import com.salaboy.rolo.mock.Mock;
import com.salaboy.rolo.model.DistanceReport;
import com.salaboy.rolo.model.RoloTheRobot;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.drools.core.time.SessionPseudoClock;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;


/**
 *
 * @author salaboy
 */
@RunWith(Arquillian.class)
public class SensorsTest {

   
    
    @Deployment()
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "sim.jar")
                .addPackage("com.salaboy.rolo.api")
                .addPackage("com.salaboy.rolo.mock")
                .addPackage("com.salaboy.rolo.internals")
                .addPackage("com.salaboy.rolo.model")
                .addPackage("org.kie.api.cdi")
                .addPackage("org.kie.api.runtime")
                .addAsManifestResource("META-INF/beans.xml", ArchivePaths.create("beans.xml"))
                .addAsManifestResource("META-INF/kmodule.xml", ArchivePaths.create("kmodule.xml"));

    }
    
    
    @Inject
    @Mock
    private DistanceSensor distanceSensor;

    @Inject
    @KSession("sensors")
    private KieSession ksession;
    
    @Test
    public void oneSensorTest() throws InterruptedException {
        
        
        
        SessionPseudoClock sessionClock = ksession.getSessionClock();
        
        distanceSensor.setName("front");

        final RoloTheRobot rolo = new RoloTheRobot("Simple Rolo");
        
        ksession.insert(rolo);
        ksession.insert(distanceSensor);
        ksession.fireAllRules();

        sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("front", 148));
        ksession.fireAllRules();
        
        sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("front", 138));
        ksession.fireAllRules();
        
        sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("front", 128));
        ksession.fireAllRules();
        
        sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("front", 118));
        ksession.fireAllRules();
        
         sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("front", 108));
        ksession.fireAllRules();
        
        sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("front", 98));
        ksession.fireAllRules();
        
        
        sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("front", 50));
        ksession.fireAllRules();
        
        sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("front", 75));
        ksession.fireAllRules();
        
        sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("front", 100));
        ksession.fireAllRules();
        
        sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("front", 120));
        ksession.fireAllRules();
        
        sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("front", 140));
        ksession.fireAllRules();
        
        sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("front", 148));
        ksession.fireAllRules();

    }
    
}
