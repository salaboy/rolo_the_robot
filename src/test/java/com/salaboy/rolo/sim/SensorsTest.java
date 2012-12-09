/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.sim;

import com.salaboy.rolo.internals.TendencyAccumulateFunction;
import com.salaboy.rolo.wedo.api.DistanceSensor;
import com.salaboy.rolo.model.DistanceReport;
import com.salaboy.rolo.model.RoloTheRobot;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.drools.compiler.PackageBuilderConfiguration;
import org.drools.io.impl.ClassPathResource;
import org.drools.marshalling.impl.ProtobufMessages.KnowledgeBase;
import org.drools.time.SessionPseudoClock;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.KnowledgeBaseConfiguration;
import org.kie.KnowledgeBaseFactory;
import org.kie.builder.KnowledgeBuilder;
import org.kie.builder.KnowledgeBuilderFactory;
import org.kie.conf.EventProcessingOption;
import org.kie.io.ResourceType;
import org.kie.logger.KnowledgeRuntimeLoggerFactory;
import org.kie.runtime.KnowledgeSessionConfiguration;
import org.kie.runtime.StatefulKnowledgeSession;
import org.kie.runtime.conf.ClockTypeOption;

/**
 *
 * @author salaboy
 */
@RunWith(Arquillian.class)
public class SensorsTest {

    @Deployment()
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "sim.jar")
                .addPackage("com.salaboy.rolo.wedo.api")
                .addPackage("com.salaboy.rolo.wedo.impl")
                .addPackage("com.codeminders.hidapi") // hidapi
                .addAsManifestResource("META-INF/beans.xml", ArchivePaths.create("beans.xml"));

    }
    @Inject
    private DistanceSensor distanceSensor;

    @Test
    public void oneSensorTest() throws InterruptedException {
        PackageBuilderConfiguration pkgConf = new PackageBuilderConfiguration();
        pkgConf.addAccumulateFunction("tendency", TendencyAccumulateFunction.class);
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(pkgConf);
        
        kbuilder.add(new ClassPathResource("rolo-sensors.drl"), ResourceType.DRL);

        if (kbuilder.getErrors().size() > 0) {
            throw new IllegalStateException(kbuilder.getErrors().toString());
        }
        KnowledgeBaseConfiguration kBaseConfig = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
        kBaseConfig.setOption(EventProcessingOption.STREAM);
        org.kie.KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(kBaseConfig);
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        KnowledgeSessionConfiguration config = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
        config.setOption(ClockTypeOption.get("pseudo"));
        final StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession(config, null);

        KnowledgeRuntimeLoggerFactory.newConsoleLogger(ksession);
        final RoloTheRobot rolo = new RoloTheRobot("Simple Rolo");
        SessionPseudoClock sessionClock = ksession.getSessionClock();

        distanceSensor.setName("front");

        ksession.insert(rolo);
        ksession.insert(distanceSensor);
        ksession.fireAllRules();

        sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getWorkingMemoryEntryPoint("distance-sensor").insert(new DistanceReport("front", 148));
        ksession.fireAllRules();
        
        sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getWorkingMemoryEntryPoint("distance-sensor").insert(new DistanceReport("front", 138));
        ksession.fireAllRules();
        
        sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getWorkingMemoryEntryPoint("distance-sensor").insert(new DistanceReport("front", 128));
        ksession.fireAllRules();
        
        sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getWorkingMemoryEntryPoint("distance-sensor").insert(new DistanceReport("front", 118));
        ksession.fireAllRules();
        
         sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getWorkingMemoryEntryPoint("distance-sensor").insert(new DistanceReport("front", 108));
        ksession.fireAllRules();
        
        sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getWorkingMemoryEntryPoint("distance-sensor").insert(new DistanceReport("front", 98));
        ksession.fireAllRules();
        
        
        sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getWorkingMemoryEntryPoint("distance-sensor").insert(new DistanceReport("front", 50));
        ksession.fireAllRules();
        
        sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getWorkingMemoryEntryPoint("distance-sensor").insert(new DistanceReport("front", 75));
        ksession.fireAllRules();
        
        sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getWorkingMemoryEntryPoint("distance-sensor").insert(new DistanceReport("front", 100));
        ksession.fireAllRules();
        
        sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getWorkingMemoryEntryPoint("distance-sensor").insert(new DistanceReport("front", 120));
        ksession.fireAllRules();
        
        sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getWorkingMemoryEntryPoint("distance-sensor").insert(new DistanceReport("front", 140));
        ksession.fireAllRules();
        
        sessionClock.advanceTime(50, TimeUnit.MILLISECONDS);
        ksession.getWorkingMemoryEntryPoint("distance-sensor").insert(new DistanceReport("front", 148));
        ksession.fireAllRules();

    }
}
