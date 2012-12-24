/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo;

import com.salaboy.rolo.api.DistanceSensor;
import com.salaboy.rolo.api.Motor;
import com.salaboy.rolo.model.DistanceReport;
import com.salaboy.rolo.model.RoloTheRobot;
import com.salaboy.rolo.wedo.impl.WeDoBlockManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.kie.KieBaseConfiguration;
// 6.0.0-SNAPSHOT
import org.kie.KnowledgeBase;
import org.kie.KnowledgeBaseFactory;
import org.kie.builder.KnowledgeBuilder;
import org.kie.builder.KnowledgeBuilderFactory;
import org.kie.io.ResourceType;
import org.kie.conf.EventProcessingOption;
import org.kie.io.ResourceFactory;
import org.kie.runtime.StatefulKnowledgeSession;

/**
 *
 * @author salaboy java -jar rolo_the_robot-1.0-SNAPSHOT.jar -t 400
 */
public class RoloMain {

    static boolean readSensors = true;
    static long defaultLatency = 100;

    public static void main(String[] args) throws Exception {
        // create Options object
        Options options = new Options();

        // add t option
        options.addOption("t", true, "sensors latency");
        options.addOption("arch", true, "architecture");
        CommandLineParser parser = new PosixParser();
        CommandLine cmd = parser.parse(options, args);

        String sensorLatency = cmd.getOptionValue("t");
        if (sensorLatency == null) {
            System.out.println(" The Default Latency will be used: " + defaultLatency);
        } else {
            System.out.println(" The Latency will be set to: " + sensorLatency);
            defaultLatency = new Long(sensorLatency);
        }
        String arch = cmd.getOptionValue("arch");
        if (arch == null) {
            System.out.println(" The Default Arch will be used: arm7");
        } else {
            System.out.println(" The Arch will be set to: " + arch);
            WeDoBlockManager.arch = arch;
        }

        System.out.println("Starting Rolo ...");

        Weld weld = new Weld();

        WeldContainer container = weld.initialize();

        final Motor motor = container.instance().select(Motor.class).get();
        final DistanceSensor distanceSensor = container.instance().select(DistanceSensor.class).get();
//        PackageBuilderConfiguration pkgConf = new PackageBuilderConfiguration();
//        pkgConf.addAccumulateFunction("tendency", TendencyAccumulateFunction.class);
//        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(pkgConf);
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("rolo-main.drl"), ResourceType.DRL);

        if (kbuilder.getErrors().size() > 0) {
            throw new IllegalStateException(kbuilder.getErrors().toString());
        }
        KieBaseConfiguration kBaseConfig = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
        kBaseConfig.setOption(EventProcessingOption.STREAM);
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(kBaseConfig);
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

        final StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        //KnowledgeRuntimeLoggerFactory.newConsoleLogger(ksession);
        final RoloTheRobot rolo = new RoloTheRobot("Simple Rolo");
        motor.setName("main");
        distanceSensor.setName("front");


        ksession.insert(rolo);
        ksession.insert(motor);
        ksession.insert(distanceSensor);

        ksession.fireAllRules();

        final Thread t = new Thread() {
            @Override
            public void run() {
                while (readSensors) {
                    int readDistance = distanceSensor.readDistance();
                    ksession.getEntryPoint("distance-sensor").insert(new DistanceReport(distanceSensor.getName(), readDistance));
                    ksession.fireAllRules();
                    try {
                        Thread.sleep(defaultLatency);
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
