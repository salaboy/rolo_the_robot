///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.salaboy.rolo.arduino.test;
//
//import com.salaboy.rolo.api.LightSensor;
//import com.salaboy.rolo.api.Motor;
//import com.salaboy.rolo.arduino.Arduino;
//import com.salaboy.rolo.arduino.ArduinoMotor;
//import com.salaboy.rolo.model.DistanceReport;
//import javax.inject.Inject;
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.Archive;
//import org.jboss.shrinkwrap.api.ArchivePaths;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import static org.junit.Assert.*;
//import org.junit.Ignore;
//import org.kie.KieBaseConfiguration;
//import org.kie.KnowledgeBaseFactory;
//import org.kie.builder.KnowledgeBuilder;
//import org.kie.builder.KnowledgeBuilderFactory;
//import org.kie.conf.EventProcessingOption;
//import org.kie.io.ResourceFactory;
//import org.kie.io.ResourceType;
//import org.kie.logger.KnowledgeRuntimeLoggerFactory;
//import org.kie.runtime.StatefulKnowledgeSession;
//
///**
// *
// * @author salaboy
// */
//@RunWith(Arquillian.class)
//public class ArduinoLightSensorTest {
//
//    @Deployment()
//    public static Archive<?> createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class, "sim.jar")
//                .addPackage("com.salaboy.rolo.api")
//                .addPackage("com.salaboy.rolo.arduino")
//                .addPackage("com.salaboy.rolo.internals")
//                .addPackage("com.salaboy.rolo.arduino.serial")
//                .addPackage(" com.salaboy.rolo.model")
//                .addAsManifestResource("META-INF/beans.xml", ArchivePaths.create("beans.xml"));
//
//    }
//    
//    @Inject
//    @Arduino
//    private LightSensor lightSensor;
//
//    @Test
//    public void motorTest() throws InterruptedException {
//        assertNotNull(lightSensor);
//        
//        while(true){
//            Thread.sleep(100);
//            System.out.println(" Light - > "+lightSensor.readLight());
//        }
//
//    }
//
//    
//}
