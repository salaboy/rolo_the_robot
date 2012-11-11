/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo;

import com.salaboy.rolo.wedo.api.DistanceSensor;
import com.salaboy.rolo.wedo.api.Motor;
import com.salaboy.rolo.wedo.model.DistanceReport;
import com.salaboy.rolo.wedo.model.RoloTheRobot;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 *
 * @author salaboy
 */
public class RoloMain {
    
    static boolean readSensors = true;
    
    public static void main(String[] args) throws Exception{
        System.out.println("Starting Rolo ...");
        Motor motor = null;
        DistanceSensor distanceSensor = null;
        Weld weld = new Weld();
  	
        WeldContainer container = weld.initialize();
  	
        motor = container.instance().select(Motor.class).get();
        distanceSensor = container.instance().select(DistanceSensor.class).get();
        
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(new ClassPathResource("rolo-main.drl"), ResourceType.DRL);

        if(kbuilder.getErrors().size() > 0){
            throw new IllegalStateException(kbuilder.getErrors().toString());
        } 
        
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

        final StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        //KnowledgeRuntimeLoggerFactory.newConsoleLogger(ksession);
        final RoloTheRobot rolo = new RoloTheRobot("Simple Rolo");
        rolo.addMotor("main", motor);
        rolo.addDistanceSensor("body",distanceSensor);

        ksession.insert(rolo);
        ksession.fireAllRules();
        
        final Thread t = new Thread() {
            @Override
            public void run() {
                while (readSensors) {
                    int readDistance = rolo.getDistanceSensorByName("body").readDistance();
                    ksession.getWorkingMemoryEntryPoint("distance-sensor").insert(new DistanceReport(readDistance));
                    ksession.fireAllRules();
                    try {
                        Thread.sleep(100);
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
            }
        });
    }
}
