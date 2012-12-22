/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.arduino.test;

import com.salaboy.rolo.api.LightSensor;
import com.salaboy.rolo.api.Motor;
import com.salaboy.rolo.arduino.Arduino;
import com.salaboy.rolo.arduino.ArduinoMotor;
import com.salaboy.rolo.model.DistanceReport;
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
import org.junit.Ignore;
import org.kie.KieBaseConfiguration;
import org.kie.KnowledgeBaseFactory;
import org.kie.builder.KnowledgeBuilder;
import org.kie.builder.KnowledgeBuilderFactory;
import org.kie.conf.EventProcessingOption;
import org.kie.io.ResourceFactory;
import org.kie.io.ResourceType;
import org.kie.logger.KnowledgeRuntimeLoggerFactory;
import org.kie.runtime.StatefulKnowledgeSession;

/**
 *
 * @author salaboy
 */
@RunWith(Arquillian.class)
public class ArduinoMotorTest {

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
    private ArduinoMotor motorA;
    @Inject
    @Arduino
    private ArduinoMotor motorB;
    
    @Inject
    @Arduino
    private LightSensor lightSensor;

    @Test
    
    public void motorTest() throws InterruptedException {
        assertNotNull(motorA);
        motorA.setupMotor(7, 11, 9);
        assertNotNull(motorB);
        motorB.setupMotor(8, 12, 10);
        
        

        motorA.start(126, Motor.DIRECTION.FORWARD);
        Thread.sleep(2000);
        motorA.stop();

        motorB.start(126, Motor.DIRECTION.BACKWARD);
        Thread.sleep(2000);
        motorB.stop();


        motorA.start(126, Motor.DIRECTION.BACKWARD);
        Thread.sleep(2000);
        motorA.stop();


        motorB.start(126, Motor.DIRECTION.FORWARD);
        Thread.sleep(2000);
        motorB.stop();

    }

    @Test
    @Ignore
    public void motorRulesTest() throws InterruptedException {
        final StatefulKnowledgeSession ksession = createSession();
       
        
        assertNotNull(motorA);
        motorA.setupMotor(7, 11, 9);
        motorA.setName("MotorA");
        ksession.insert(motorA);
        assertNotNull(motorB);
        motorB.setName("MotorB");
        motorB.setupMotor(8, 12, 10);
        ksession.insert(motorB);

        ksession.fireAllRules();
        
        Thread.sleep(1000);
     
        Thread.sleep(200);
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 110));
        
        ksession.fireAllRules();
        Thread.sleep(200);
        
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 110));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 110));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        
        Thread.sleep(200);
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 110));
        
        ksession.fireAllRules();
        Thread.sleep(200);
        
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 110));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 110));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        Thread.sleep(200);
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 110));
        
        ksession.fireAllRules();
        Thread.sleep(200);
        
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 110));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 110));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 60));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 60));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 60));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 60));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 60));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        
         ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 60));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 60));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 60));
        ksession.fireAllRules();
        Thread.sleep(200);
        
         ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 60));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 60));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 60));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        
        
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 30));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 30));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 30));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 30));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 30));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 30));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        ksession.getEntryPoint("distance-sensor").insert(new DistanceReport("distance", 30));
        ksession.fireAllRules();
        Thread.sleep(200);
        
        
        

    }

    private StatefulKnowledgeSession createSession() {


        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("rolo-motors.drl"), ResourceType.DRL);

        if (kbuilder.getErrors().size() > 0) {
            throw new IllegalStateException(kbuilder.getErrors().toString());
        }
        KieBaseConfiguration kBaseConfig = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
        kBaseConfig.setOption(EventProcessingOption.STREAM);
        org.kie.KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(kBaseConfig);
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        //KieSessionConfiguration config = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
        //config.setOption(ClockTypeOption.get("pseudo"));
        //StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession(config, null);
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

        KnowledgeRuntimeLoggerFactory.newConsoleLogger(ksession);


        return ksession;

    }
}
