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
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;



import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;


/**
 *
 * @author salaboy
 */
@RunWith(Arquillian.class)
public class RoloLevelsTest {
    
    @Deployment()
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "sim.jar")
                .addPackage("com.salaboy.rolo.api")
                .addPackage("com.salaboy.rolo.mock")
                .addPackage("com.salaboy.rolo.internals")
                .addPackage(" com.salaboy.rolo.model")
                .addAsManifestResource("META-INF/beans.xml", ArchivePaths.create("beans.xml"))
                .addAsManifestResource("META-INF/kmodule.xml", ArchivePaths.create("kmodule.xml"));

    }
    
    @Inject
    @KSession("levels")
    private KieSession levels;

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

        
        RoloTheRobot roloTheRobot = new RoloTheRobot("rolo1");

        FactHandle roloFactHandle = levels.insert(roloTheRobot);
        
        roloTheRobot.setPositionX(10);
        
        levels.update(roloFactHandle, roloTheRobot);
        Wall wall = new Wall();
        
        levels.insert(wall);
        
        roloTheRobot.setOrientation(90);
        roloTheRobot.setPositionY(20);
        levels.update(roloFactHandle, roloTheRobot);
        Wall wall2 = new Wall();
        levels.insert(wall2);
        
        
        
        

        QueryResults queryResults = levels.getQueryResults("getAllWorldShapes");
        Iterator<QueryResultsRow> iterator = queryResults.iterator();
        List<WorldShape> shapes = new ArrayList<WorldShape>(queryResults.size());
        while (iterator.hasNext()) {
            //shapes.add((WorldShape)iterator.next().get("$ws"));
            System.out.println("Shape: " + (WorldShape) iterator.next().get("$ws"));
        }



    }
}
