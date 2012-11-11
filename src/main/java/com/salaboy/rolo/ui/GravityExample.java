/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.ui;

import processing.core.PApplet;
import toxi.geom.*;
import toxi.physics2d.*;
import toxi.physics2d.behaviors.*;

/**
 *
 * @author salaboy
 */
public class GravityExample extends PApplet {

    int NUM_PARTICLES = 500;
    VerletPhysics2D physics;
    AttractionBehavior mouseAttractor;
    Vec2D mousePos;

    public void setup() {
        size(680, 382, P3D);
        // setup physics with 10% drag
        physics = new VerletPhysics2D();
        physics.setDrag(0.03f);
        physics.setWorldBounds(new Rect(0, 0, width, height));
        // the NEW way to add gravity to the simulation, using behaviors
        physics.addBehavior(new GravityBehavior(new Vec2D(0, 0.15f)));
        mousePos = new Vec2D(mouseX, mouseY);
        mouseAttractor = new AttractionBehavior(mousePos, 250, -0.9f);
        physics.addBehavior(mouseAttractor);

    }

    public void addParticle() {
        VerletParticle2D p = new VerletParticle2D(Vec2D.randomVector().scaleSelf(5).addSelf(width / 2, 0));
        physics.addParticle(p);
        // add a negative attraction force field around the new particle
        physics.addBehavior(new AttractionBehavior(p, 20, -1.2f, 0.01f));
    }

    public void draw() {
        background(255, 0, 0);
        noStroke();
        fill(255);
        if (physics.particles.size() < NUM_PARTICLES) {
            addParticle();
        }
        mousePos.set(mouseX, mouseY);
        physics.update();
        for (VerletParticle2D p : physics.particles) {
            ellipse(p.x, p.y, 5, 5);
        }
    }

    public void mousePressed() {
        mouseAttractor.setStrength(0.9f);
    }

    public void mouseReleased() {
        mouseAttractor.setStrength(-0.9f);
    }
    
     public static void main(String args[]) {
        PApplet.main(new String[]{"--present", "org.jboss.processingdrools.GravityExample"});
    }
}
