/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.levels;

import com.salaboy.rolo.model.RoloTheRobot;
import com.salaboy.rolo.model.Wall;
import com.salaboy.rolo.model.WorldShape;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.KieBaseConfiguration;
import org.kie.KnowledgeBase;
import org.kie.KnowledgeBaseFactory;
import org.kie.builder.KnowledgeBuilder;
import org.kie.builder.KnowledgeBuilderFactory;
import org.kie.conf.EventProcessingOption;
import org.kie.io.ResourceFactory;
import org.kie.io.ResourceType;
import org.kie.runtime.StatefulKnowledgeSession;
import org.kie.runtime.rule.FactHandle;
import org.kie.runtime.rule.QueryResults;
import org.kie.runtime.rule.QueryResultsRow;

/**
 *
 * @author salaboy
 */
public class RoloLevelsTest {

    private StatefulKnowledgeSession mind;

    public RoloLevelsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void helloRolo() {

        // This should have the session??
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("levels/level1-rolo.drl"), ResourceType.DRL);

        if (kbuilder.getErrors().size() > 0) {
            throw new IllegalStateException(kbuilder.getErrors().toString());
        }
        KieBaseConfiguration kBaseConfig = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
        kBaseConfig.setOption(EventProcessingOption.STREAM);
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(kBaseConfig);
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

        mind = kbase.newStatefulKnowledgeSession();
        RoloTheRobot roloTheRobot = new RoloTheRobot("rolo1");

        FactHandle roloFactHandle = mind.insert(roloTheRobot);
        
        roloTheRobot.setPositionX(10);
        
        mind.update(roloFactHandle, roloTheRobot);
        

        mind.insert(new Wall(100, 100, 10, 0));
        
        roloTheRobot.setOrientation(90);
        roloTheRobot.setPositionY(20);
        mind.update(roloFactHandle, roloTheRobot);
        
        mind.insert(new Wall(100, 100, 10, 20));
        

        QueryResults queryResults = mind.getQueryResults("getAllWorldShapes");
        Iterator<QueryResultsRow> iterator = queryResults.iterator();
        List<WorldShape> shapes = new ArrayList<WorldShape>(queryResults.size());
        while (iterator.hasNext()) {
            //shapes.add((WorldShape)iterator.next().get("$ws"));
            System.out.println("Shape: " + (WorldShape) iterator.next().get("$ws"));
        }



    }
}
