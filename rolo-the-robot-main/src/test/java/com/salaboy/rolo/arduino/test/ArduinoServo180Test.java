///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.salaboy.rolo.arduino.test;
//
//import com.salaboy.rolo.api.Motor;
//import com.salaboy.rolo.api.Servo180;
//import com.salaboy.rolo.api.UltraSonicSensor;
//import com.salaboy.rolo.arduino.Arduino;
//import com.salaboy.rolo.arduino.ArduinoLightSensor;
//import com.salaboy.rolo.arduino.ArduinoMotor;
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
//public class ArduinoServo180Test {
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
//    private Servo180 servo;
//    @Inject
//    @Arduino
//    private UltraSonicSensor ultraSonic;
//    @Inject
//    @Arduino
//    private ArduinoMotor motorA;
//    @Inject
//    @Arduino
//    private ArduinoMotor motorB;
//    
//    @Inject
//    @Arduino
//    private ArduinoLightSensor lightSensor;
//
//    @Test
//    public void motorTest() throws InterruptedException {
//        assertNotNull(motorA);
//        motorA.setupMotor(7, 11, 5);
//        assertNotNull(motorB);
//        motorB.setupMotor(6, 12, 10);
//
//        assertNotNull(servo);
//        servo.setPin(3); // It's defaulted to 3 but it can be configured to another pin
//
//        assertNotNull(ultraSonic);
//
//        assertNotNull(lightSensor);
//        lightSensor.setPin(0);
//        
//        
//
////        for (int i = 0; i < 200; i = i + 20) {
////            System.out.println("I = " + i);
////            servo.rotate(i);
////            Thread.sleep(1000);
////        }
//
//        while (true) {
//            for (int i = 0; i < 190; i = i + 10) {
//                if(i > 70){
//                    motorB.stop();
//                    motorA.start(200, Motor.DIRECTION.FORWARD);
//                }
//                
//                if(i > 130){
//                    motorA.stop();
//                    motorB.start(200, Motor.DIRECTION.FORWARD);
//                }
//                servo.rotate(i);
//                System.out.println("###############");
//                System.out.println("Degree = " + i + " -  Distance = " + ultraSonic.readDistance());
//                System.out.println("Degree = " + i + " -  Distance = " + ultraSonic.readDistance());
//                System.out.println("Degree = " + i + " -  Distance = " + ultraSonic.readDistance());
//                System.out.println("Degree = " + i + " -  Distance = " + ultraSonic.readDistance());
//                System.out.println("Degree = " + i + " -  Distance = " + ultraSonic.readDistance());
//                System.out.println("Light = "+lightSensor.readLight());
//                System.out.println("###############");
//                Thread.sleep(1000);
//
//            }
//        }
//
//
//
//
//
//
//    }
//
//    private StatefulKnowledgeSession createSession() {
//
//
//        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
//        kbuilder.add(ResourceFactory.newClassPathResource("rolo-motors.drl"), ResourceType.DRL);
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
