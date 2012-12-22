//package com.salaboy.rolo.ui;
//
///**
// * Hello world!
// *
// */
//import java.awt.event.KeyEvent;
//import processing.core.*;
//import promidi.*;
//import sprites.utils.*;
//import sprites.*;
//import toxi.physics2d.*;
//import toxi.physics2d.behaviors.*;
//
//import toxi.geom.*;
//import toxi.physics2d.VerletPhysics2D;
//
//public class MyProcessingSketch extends PApplet {
//
//    private MidiIO midiIo;
//    private Sprite tiranocoq;
//    private StopWatch sw = new StopWatch();
//    private float rotWorldx = PI / 4;
//    private float rotWorldy = PI / 4;
//    private float eyex = PI / 4;
//    private float eyey = PI / 4;
//    private float WORLD_RADIUS = 1000;
//    private float coqx = PI;
//    private float a;
//    private float b;
//    private double steps = 200.0f;
//    private int NUM_PARTICLES = 20;
//    private VerletPhysics2D physics;
//    private AttractionBehavior mouseAttractor;
//    private Vec2D mousePos;
//
//    public void setup() {
//        midiIo = MidiIO.getInstance(this);
//        midiIo.printDevices();
//        //midiIo.plug(this, "noteOn", 0, 0);
//        size(1000, 800, P3D);
//        textureMode(NORMAL);
//        registerPre(this);
//        registerKeyEvent(this);
//
//        initTiranoCoq();
//
//        physics = new VerletPhysics2D();
//        physics.setDrag(0.03f);
//        
//        // the NEW way to add gravity to the simulation, using behaviors
//        physics.addBehavior(new GravityBehavior(new Vec2D(0f, 0.15f)));
//        mousePos = new Vec2D(mouseX, mouseY);
//        mouseAttractor = new AttractionBehavior(mousePos, 250f, -0.9f);
//        physics.addBehavior(mouseAttractor);
//
//
//    }
//
//    public void pre() {
//        // Calculate time difference since last call
//        float elapsedTime = (float) sw.getElapsedTime();
//        processCollisions();
//        S4P.updateSprites(elapsedTime);
//    }
//
//    public void addParticle() {
//        VerletParticle2D p = new VerletParticle2D(Vec2D.randomVector().scaleSelf(5).addSelf((float)tiranocoq.getX(), (float)tiranocoq.getY()));
//        physics.addParticle(p);
//        // add a negative attraction force field around the new particle
//        physics.addBehavior(new AttractionBehavior(p, 20, -1.2f, 0.01f));
//    }
//
//    public void processCollisions() {
////        if (fireTruck.oo_collision(tiranocoq, 5)) {
////            tiranocoq.setVelXY(0, 100);
////            fireTruck.setVelXY(100, 0);
////        }
//    }
//
//    public void keyEvent(KeyEvent e) {
//        if (e.isActionKey()) {
//            switch (e.getID()) {
//                case KeyEvent.KEY_PRESSED:
//                    switch (keyCode) {
//                        case DOWN:
//
//                            break;
//                        case UP:
//
//                            break;
//                        case RIGHT:
//                            //System.out.println("before rot"+tiranocoq.getRot()/PI);
//                            double rot = tiranocoq.getRot() + (PI / steps);
//                            tiranocoq.setRot(rot);
//                            //System.out.println("after rot"+rot / PI);
//                            System.out.println("before coqx pos" + coqx / PI);
//                            coqx -= PI / steps;
//                            System.out.println("after coqx pos" + coqx / PI);
//
//
//                            float x0 = map(sin(coqx), -1, 1, (width / 2.0f) - 1200.0f, (width / 2.0f) + 1200.0f);
//                            float y0 = map(cos(coqx), -1, 1, (float) (height + 900.0f) - 1200.0f, (float) (height + 900.0f) + 1200.0f);
//                            tiranocoq.setX(x0);
//                            tiranocoq.setY(y0);
//                            tiranocoq.setFrameSequence(0, 5, 0.05f);
//
//                            break;
//                        case LEFT:
//
//                            break;
//
//                    }
//            }
//        } else {
//            switch (e.getKeyCode()) {
//                case 'q':
//                case 'Q':
//                    // Rotate world 60 degrees left
//                    rotWorldy -= 60;
//                    break;
//                case 'e':
//                case 'E':
//                    // Rotate world 60 degrees right
//                    rotWorldy += 60;
//                    break;
//                case 's':
//                case 'S':
//                    eyey += 80;
//                    break;
//                case 'w':
//                case 'W':
//                    eyey -= 80;
//                    break;
//                case 'd':
//                case 'D':
//
//                    break;
//                case 'a':
//                case 'A':
//
//                    break;
//            }
//        }
//
//
//    }
//
//    void world() {
//        translate((float) (width / 2.0), (float) height + 900, 0f);
//        //rotateX(rotx);
//        rotateY(rotWorldy);
//        //scale(90);
//        sphereDetail(25);
//        lights();
//        sphere(WORLD_RADIUS);
//
//    }
//
//    public void draw() {
//        
//        physics.setWorldBounds(new Rect((float)tiranocoq.getX(), (float)tiranocoq.getY(), (float)tiranocoq.getX(), (float)tiranocoq.getY()));
//        background(255, 0, 0);
//        camera(width / 2.0f, height / 2.0f, ((height / 2.0f) / tan(PI * 60.0f / 360.0f)) + eyey, width / 2.0f, height / 2.0f, 0f, 0f, 1f, 0f);
//        S4P.drawSprites();
//
//        stroke(0);
//
//        strokeWeight(5.0f);
//        rect((float)tiranocoq.getX()-300, (float)tiranocoq.getY()-300, (float)tiranocoq.getX(), (float)tiranocoq.getY());
//        //X-axis
//        line(-(width * 10), (float) height + 900, width * 10, (float) height + 900);
//        //Y-axis
//        line(width / 2.0f, -(height * 10), width / 2.0f, height * 10);
//
//        //Ground
//        line(-(width * 10), (float) height - 100, width * 10, (float) height - 100);
//
//
//        noStroke();
//        fill(255);
//        if (physics.particles.size() < NUM_PARTICLES) {
//            addParticle();
//        }
//        mousePos.set(mouseX, mouseY);
//        physics.update();
//        for (VerletParticle2D p : physics.particles) {
//            ellipse(p.x, p.y, 5, 5);
//        }
//
//
//
//        stroke(0);
//        strokeWeight(10.0f);
//
//        noFill();
//        strokeWeight(5.0f);
//
//        world();
//
//
//
//
//    }
//
//    public void noteOn(Note note) {
//        int vel = note.getVelocity();
//        int pit = note.getPitch();
//
//        fill(255, vel * 2, pit * 2, vel * 2);
//        stroke(255, vel);
//        ellipse(vel * 5, pit * 5, 30, 30);
//    }
//
//    public void initTiranoCoq() {
//
//
//        tiranocoq = new Sprite(this, "data/tiranocoq.png", 5, 1, 0);
//        tiranocoq.setDomain(0, 0, width, height, Sprite.REBOUND);
//        tiranocoq.setXY(width / 2.0f, (float) height - 100);
//        tiranocoq.setVisible(true);
//        tiranocoq.setDead(false);
//
//
//    }
//
//    public void mousePressed() {
//        mouseAttractor.setStrength(0.9f);
//    }
//
//    public void mouseReleased() {
//        mouseAttractor.setStrength(-0.9f);
//    }
//
//    public static void main(String args[]) {
//        PApplet.main(new String[]{"--present", "org.jboss.processingdrools.MyProcessingSketch"});
//    }
//}
