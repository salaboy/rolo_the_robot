/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.wedo;

import com.salaboy.rolo.wedo.api.DistanceSensor;
import com.salaboy.rolo.wedo.api.Motor;
import com.salaboy.rolo.wedo.model.DistanceReport;
import com.salaboy.rolo.wedo.model.RoloTheRobot;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.drools.io.impl.ClassPathResource;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.KnowledgeBase;
import org.kie.KnowledgeBaseFactory;
import org.kie.builder.KnowledgeBuilder;
import org.kie.builder.KnowledgeBuilderFactory;
import org.kie.builder.ResourceType;
import org.kie.logger.KnowledgeRuntimeLoggerFactory;
import org.kie.runtime.StatefulKnowledgeSession;

/**
 *
 * @author salaboy
 */
@RunWith(Arquillian.class)
public class RoloMainTest {

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
    
    @Test
    public void initialTest() throws InterruptedException {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(new ClassPathResource("rolo-main.drl"), ResourceType.DRL);

        if(kbuilder.getErrors().size() > 0){
            throw new IllegalStateException(kbuilder.getErrors().toString());
        } 
        
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

        final StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        KnowledgeRuntimeLoggerFactory.newConsoleLogger(ksession);
        final RoloTheRobot rolo = new RoloTheRobot("Simple Rolo");
        rolo.addMotor("main", motor);
        rolo.addDistanceSensor("body",distanceSensor);

       
        
        ksession.insert(rolo);
        ksession.fireAllRules();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    int readDistance = rolo.getDistanceSensorByName("body").readDistance();
                    ksession.getWorkingMemoryEntryPoint("distance-sensor").insert(new DistanceReport(readDistance));
                    ksession.fireAllRules();
                    try {
                        this.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(RoloMainTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
        
        
        
        Thread.sleep(20000);
        
        rolo.getMotorByName("main").stop();
    }
}
