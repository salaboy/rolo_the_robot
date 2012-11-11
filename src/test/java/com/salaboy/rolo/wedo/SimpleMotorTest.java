/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.wedo;

import com.salaboy.rolo.wedo.api.Motor;
import com.salaboy.rolo.wedo.api.Motor.DIRECTION;
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

/**
 *
 * @author salaboy
 */
@RunWith(Arquillian.class)
public class SimpleMotorTest {

    @Deployment()
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "we-do.jar")
                .addPackage("com.salaboy.rolo.wedo.api")
                .addPackage("com.salaboy.rolo.wedo.impl")
                .addPackage("com.codeminders.hidapi") // hidapi
                .addAsManifestResource("META-INF/beans.xml", ArchivePaths.create("beans.xml"));

    }
    @Inject
    private Motor motor;

    public SimpleMotorTest() {
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
    public void backAndForth() throws InterruptedException {

        motor.backward(120, 2000);

        Thread.sleep(1000);
        
        motor.forward(120, 2000);
    }
    
    @Test
    public void startAndStop() throws InterruptedException {

      

        Thread.sleep(1000);
        System.out.println("Starting ...80");
        motor.start(80, DIRECTION.FORWARD);

        Thread.sleep(2000);
        System.out.println("Starting ...100");
        motor.start(100, DIRECTION.FORWARD);

        Thread.sleep(2000);
        System.out.println("Starting ...120");
        motor.start(120, DIRECTION.FORWARD);

        Thread.sleep(2000);
        
        System.out.println("Starting ...120");
        motor.start(120, DIRECTION.BACKWARD);

        Thread.sleep(2000);

//        motor.start(50, DIRECTION.FORWARD);
//
//        Thread.sleep(3000);

        motor.stop();
    }
}
