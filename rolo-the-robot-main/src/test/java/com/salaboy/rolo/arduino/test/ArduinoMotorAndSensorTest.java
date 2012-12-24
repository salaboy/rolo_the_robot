///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.salaboy.rolo.arduino.test;
//
//import com.salaboy.rolo.RoloMain;
//import com.salaboy.rolo.api.LightSensor;
//import com.salaboy.rolo.api.Motor;
//import com.salaboy.rolo.api.UltraSonicSensor;
//import com.salaboy.rolo.arduino.Arduino;
//import com.salaboy.rolo.arduino.ArduinoMotor;
//import com.salaboy.rolo.model.DistanceReport;
//import com.salaboy.rolo.model.RoloTheRobot;
//import java.util.logging.Level;
//import java.util.logging.Logger;
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
// * mvn -Dtest=com.salaboy.rolo.arduino.test.ArduinoMotorAndSensorTest test-compile surefire:test
// * @author salaboy
// */
//@RunWith(Arquillian.class)
//public class ArduinoMotorAndSensorTest {
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
//    @Inject
//    @Arduino
//    private ArduinoMotor motorA;
//    @Inject
//    @Arduino
//    private LightSensor lightSensor;
//    @Inject
//    @Arduino
//    private UltraSonicSensor ultraSonicSensor;
//
//    
//    static boolean readSensors = true;
//    
//    static long defaultLatency = 100;
//    
//    
//    @Test
//    @Ignore
//    public void motorAndSensorTest() throws InterruptedException {
//        assertNotNull(motorA);
//        motorA.setupMotor(7, 11, 5);
//
//
//        final Thread t = new Thread() {
//            @Override
//            public void run() {
//                while (true) {
//                    int readDistance = ultraSonicSensor.readDistance();
//                    System.out.println("readDistance -> " + readDistance);
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(RoloMain.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                }
//            }
//        };
//        t.start();
//
//        final Thread t2 = new Thread() {
//            @Override
//            public void run() {
//                while (true) {
//                    motorA.start(126, Motor.DIRECTION.FORWARD);
//
//                    try {
//                        Thread.sleep(2000);
//                        motorA.stop();
//                        Thread.sleep(2000);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(RoloMain.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                }
//            }
//        };
//        t2.start();
//
//        Thread.sleep(50000);
//    }
//
//    @Test
//    
//    public void motorAndSensorRulesTest() throws InterruptedException {
//        final StatefulKnowledgeSession ksession = createSession();
//
//
//        assertNotNull(motorA);
//        motorA.setupMotor(7, 11, 5);
//        motorA.setName("MotorA");
//        ksession.insert(motorA);
//        
//        ultraSonicSensor.setName("distance-sensor");
//        ksession.insert(ultraSonicSensor);
//        ksession.insert(new RoloTheRobot("rolo"));
//        
//        final Thread t = new Thread() {
//            @Override
//            public void run() {
//                while (readSensors) {
//                    int readDistance = ultraSonicSensor.readDistance();
//                    ksession.getEntryPoint("distance-sensor").insert(new DistanceReport(ultraSonicSensor.getName(), readDistance));
//                    ksession.fireAllRules();
//                    try {
//                        Thread.sleep(defaultLatency);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(RoloMain.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                }
//            }
//        };
//        t.start();
//        Thread.sleep(50000);
//
//
//    }
//
//    private StatefulKnowledgeSession createSession() {
//
//
//        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
//        kbuilder.add(ResourceFactory.newClassPathResource("rolo-main.drl"), ResourceType.DRL);
//
//        if (kbuilder.getErrors().size() > 0) {
//            throw new IllegalStateException(kbuilder.getErrors().toString());
//        }
//        KieBaseConfiguration kBaseConfig = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
//        kBaseConfig.setOption(EventProcessingOption.STREAM);
//        org.kie.KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(kBaseConfig);
//        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
//        //KieSessionConfiguration config = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
//        //config.setOption(ClockTypeOption.get("pseudo"));
//        //StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession(config, null);
//        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
//
//        KnowledgeRuntimeLoggerFactory.newConsoleLogger(ksession);
//
//
//        return ksession;
//
//    }
//}
