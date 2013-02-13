/**
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.salaboy.rolo.hardware.test;

import com.salaboy.rolo.api.Motor;
import com.salaboy.rolo.api.Servo180;
import com.salaboy.rolo.api.UltraSonicSensor;
import com.salaboy.rolo.arduino.Arduino;
import com.salaboy.rolo.arduino.ArduinoLightSensor;
import com.salaboy.rolo.arduino.ArduinoMotor;
import com.salaboy.rolo.arduino.ArduinoTouchSensor;
import com.salaboy.rolo.arduino.behavior.RoloHead;
import com.salaboy.rolo.arduino.behavior.RoloTractionMotorPair;
import com.salaboy.rolo.arduino.behavior.RoloTractionMotorPair.ROTATION;
import com.salaboy.rolo.transport.HornetQSessionWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;
import javax.inject.Inject;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

import org.hornetq.api.core.HornetQException;
import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.core.client.ClientConsumer;
import org.hornetq.api.core.client.ClientMessage;
import org.hornetq.api.core.client.ClientProducer;
import org.hornetq.api.core.client.ClientSession;
import org.hornetq.api.core.client.ClientSessionFactory;
import org.hornetq.api.core.client.HornetQClient;
import org.hornetq.api.core.client.ServerLocator;
import org.hornetq.core.config.Configuration;
import org.hornetq.core.config.impl.ConfigurationImpl;
import org.hornetq.core.remoting.impl.netty.NettyAcceptorFactory;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.hornetq.core.remoting.impl.netty.TransportConstants;
import org.hornetq.core.server.HornetQServer;
import org.hornetq.core.server.HornetQServers;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * java -jar rolo_the_robot-hardware-test-1.0-SNAPSHOT.jar -t 100 -ip 192.168.0.x -port 5445
 */
public class HardwareTestCommandServer implements Runnable {

  public static final String SERVER_TASK_COMMANDS_QUEUE = "commandsQueue";
  private static final Logger logger = LoggerFactory.getLogger(HardwareTestCommandServer.class);
  private ServerLocator serverLocator;
  private HornetQServer server;
  @Inject
  @Arduino
  private ArduinoMotor motorA;
  @Inject
  @Arduino
  private ArduinoMotor motorB;
  @Inject
  @Arduino
  private ArduinoMotor motorC;
  @Inject
  @Arduino
  private UltraSonicSensor ultraSonicSensor;
  @Inject
  @Arduino
  private Servo180 servo180;
  @Inject
  @Arduino
  private ArduinoLightSensor lightSensor;
  @Inject
  @Arduino
  private ArduinoTouchSensor touchSensor;
  @Inject
  @Arduino
  private RoloTractionMotorPair tractionPair;
  @Inject
  private RoloHead roloHead;
  private Configuration configuration;
  private boolean standalone = false;
  private String host;
  private int port;
  volatile boolean embeddedServerRunning;
  private boolean running;
  private ClientSession session;
  private ClientConsumer consumer;
  static boolean readDistanceSensors = false;
  static boolean readLightSensors = false;
  static boolean readTouchSensors = false;
  static boolean monitorMotorA = false;
  static boolean monitorMotorB = false;
  static boolean monitorMotorC = false;
  static long defaultLatency = 100;

  public static void main(String[] args) throws Exception {
    Weld weld = new Weld();

    WeldContainer container = weld.initialize();

    HardwareTestCommandServer roloCommandServer = container.instance().select(HardwareTestCommandServer.class).get();


    // create Options object
    Options options = new Options();

    // add t option
    options.addOption("t", true, "sensors latency");
    options.addOption("ip", true, "host");
    options.addOption("port", true, "port");
    CommandLineParser parser = new PosixParser();
    CommandLine cmd = parser.parse(options, args);

    String sensorLatency = cmd.getOptionValue("t");
    if (sensorLatency == null) {
      System.out.println(" The Default Latency will be used: " + defaultLatency);
    } else {
      System.out.println(" The Latency will be set to: " + sensorLatency);
      defaultLatency = new Long(sensorLatency);
    }

    String ip = cmd.getOptionValue("ip");
    if (ip == null) {
      System.out.println(" The Default IP will be used: 127.0.0.1");
      roloCommandServer.setHost("127.0.0.1");

    } else {
      System.out.println(" The IP will be set to: " + ip);
      roloCommandServer.setHost(ip);
    }

    String port = cmd.getOptionValue("port");
    if (port == null) {
      System.out.println(" The Default Port will be used: 5445");
      roloCommandServer.setPort(5445);

    } else {
      System.out.println(" The Port will be set to: " + port);
      roloCommandServer.setPort(Integer.parseInt(port));
    }

    System.out.println("Starting Rolo ...");


    Thread thread = new Thread(roloCommandServer);
    thread.start();


    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run() {
        System.out.println("Shutdown Hook is running !");
        readDistanceSensors = false;
        readLightSensors = false;
        readTouchSensors = false;
      }
    });

  }

  public HardwareTestCommandServer() {
  }

  public HardwareTestCommandServer(String host, int port, Configuration configuration, boolean standalone) {

    this.port = port;
    this.configuration = configuration;
    this.standalone = standalone;
    this.host = host;
  }

  public void run() {

    try {
      start();
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(HardwareTestCommandServer.class.getName()).log(Level.SEVERE, null, ex);
    }
    ClientProducer producer = null;
    try {
      producer = session.createProducer("rolo-ui");
    } catch (HornetQException ex) {
      java.util.logging.Logger.getLogger(HardwareTestCommandServer.class.getName()).log(Level.SEVERE, null, ex);
    }
    final HornetQSessionWriter notifications = new HornetQSessionWriter(session, producer);

    try {

      motorA.setupMotor(7, 11, 5);
      motorA.setName("MotorA");
      motorB.setupMotor(4, 12, 10);
      motorB.setName("MotorB");
      motorC.setupMotor(2, 13, 6);
      motorC.setName("MotorC");
      servo180.setName("Head");
      servo180.setPin(3); // there is no need to do this.. just for clarity

      lightSensor.setName("light-master");

      touchSensor.setName("touch-master");
      ultraSonicSensor.setName("distance-sensor");

      roloHead.setDistanceSensor(ultraSonicSensor);
      roloHead.setNeck(servo180);

      tractionPair.setRightMotor(motorA);
      tractionPair.setLeftMotor(motorB);


      final Thread t = new Thread() {
        @Override
        public void run() {
          while (true) {
            while (readDistanceSensors) {
              int readDistance = ultraSonicSensor.readDistance();

              try {
                notifications.write("DISTANCE_REPORT:" + readDistance);
                Thread.sleep(defaultLatency);
              } catch (Exception ex) {
                java.util.logging.Logger.getLogger(HardwareTestCommandServer.class.getName()).log(Level.SEVERE, null, ex);
              }

            }
            try {
              Thread.sleep(defaultLatency);
            } catch (InterruptedException ex) {
              java.util.logging.Logger.getLogger(HardwareTestCommandServer.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
        }
      };
      t.start();



      final Thread t2 = new Thread() {
        @Override
        public void run() {
          while (true) {
            while (readLightSensors) {
              int readLight = lightSensor.readLight();

              try {
                notifications.write("LIGHT_REPORT: " + readLight);
                Thread.sleep(defaultLatency * 5);
              } catch (Exception ex) {
                java.util.logging.Logger.getLogger(HardwareTestCommandServer.class.getName()).log(Level.SEVERE, null, ex);
              }

            }
            try {
              Thread.sleep(defaultLatency * 5);
            } catch (InterruptedException ex) {
              java.util.logging.Logger.getLogger(HardwareTestCommandServer.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
        }
      };
      t2.start();

      final Thread t3 = new Thread() {
        @Override
        public void run() {
          while (true) {
            while (readTouchSensors) {
              boolean pressed = touchSensor.readTouch();

              try {
                notifications.write("TOUCH_REPORT: " + pressed);
                Thread.sleep(defaultLatency * 5);
              } catch (Exception ex) {
                java.util.logging.Logger.getLogger(HardwareTestCommandServer.class.getName()).log(Level.SEVERE, null, ex);
              }

            }
            try {
              Thread.sleep(defaultLatency * 5);
            } catch (InterruptedException ex) {
              java.util.logging.Logger.getLogger(HardwareTestCommandServer.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
        }
      };
      t3.start();

      final Thread t4 = new Thread() {
        @Override
        public void run() {
          while (true) {
            while (monitorMotorA) {
              try {
                notifications.write("MOTOR (A) REPORT: \n"
                        + "\t Running: " + motorA.isRunning() +" \n"
                        + "\t Direction: "+ motorA.getCurrentDirection());
                Thread.sleep(defaultLatency * 5);
              } catch (Exception ex) {
                java.util.logging.Logger.getLogger(HardwareTestCommandServer.class.getName()).log(Level.SEVERE, null, ex);
              }
            }
            try {
              Thread.sleep(defaultLatency * 5);
            } catch (InterruptedException ex) {
              java.util.logging.Logger.getLogger(HardwareTestCommandServer.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
        }
      };
      t4.start();




    } catch (Exception e) {
      //throw new RuntimeException(" + Server Exception with class " + getClass() + " using port " + port, e);
      logger.error(" + Server Exception with class " + getClass() + " using port " + port + " E: " + e.getMessage());
    }
    while (running && !consumer.isClosed()) {
      System.out.println("Reading.... ");
      try {
        ClientMessage message = consumer.receive();
        System.out.println("Message Recieved.... ");
        if (message != null) {

          Object object = readMessage(message);
          System.out.println("Message Parsed.... " + object);
          String[] values = object.toString().split(":");
          if (values.length == 2) {
            System.out.println("Value 0 = " + values[0] + "Value 1 = " + values[1]);
            if (values[0].equals("FORWARD-A")) {
              motorA.start(Integer.valueOf(values[1]), Motor.DIRECTION.FORWARD);
            } else if (values[0].equals("BACKWARD-A")) {
              motorA.start(Integer.valueOf(values[1]), Motor.DIRECTION.BACKWARD);
            } else if (values[0].equals("FORWARD-B")) {
              motorB.start(Integer.valueOf(values[1]), Motor.DIRECTION.FORWARD);
            } else if (values[0].equals("BACKWARD-B")) {
              motorB.start(Integer.valueOf(values[1]), Motor.DIRECTION.BACKWARD);
            } else if (values[0].equals("FORWARD-C")) {
              motorC.start(Integer.valueOf(values[1]), Motor.DIRECTION.FORWARD);
            } else if (values[0].equals("BACKWARD-C")) {
              motorC.start(Integer.valueOf(values[1]), Motor.DIRECTION.BACKWARD);
            }
            if (values[0].equals("FORWARD-GLOBAL")) {
              motorA.start(Integer.valueOf(values[1]), Motor.DIRECTION.FORWARD);
              motorB.start(Integer.valueOf(values[1]), Motor.DIRECTION.FORWARD);
              motorC.start(Integer.valueOf(values[1]), Motor.DIRECTION.FORWARD);
            } else if (values[0].equals("BACKWARD-GLOBAL")) {
              motorA.start(Integer.valueOf(values[1]), Motor.DIRECTION.BACKWARD);
              motorB.start(Integer.valueOf(values[1]), Motor.DIRECTION.BACKWARD);
              motorC.start(Integer.valueOf(values[1]), Motor.DIRECTION.BACKWARD);
            } else if(values[0].equals("MONITOR-ON")) {
                if (values[1].equals("MOTORA")) {
                  monitorMotorA = true;
                }
            } else if(values[0].equals("MONITOR-OFF")) {
                if (values[1].equals("MOTORA")) {
                  monitorMotorA = false;
                }
            }
            
            
            else if (values[0].equals("MULTI")) {
              if (values[1].equals("ROTATE-LEFT")) {
                tractionPair.rotate(ROTATION.LEFT);
              } else if (values[1].equals("ROTATE-RIGHT")) {
                tractionPair.rotate(ROTATION.RIGHT);
              } else if (values[1].equals("SCAN")) {
                roloHead.scan();
              } else if (values[1].equals("SCAN-RESULTS")) {
                Map<Integer, Integer> lastScan = roloHead.getLastScan();
                String report = "";
                for (Integer degree : lastScan.keySet()) {
                  report += "Degree: " + degree + " - Distance: " + lastScan.get(degree) + "\n";
                }
                notifications.write("SCAN-RESULTS: \n\t" + report);

              } else if (values[1].equals("SCAN-STOP")) {
                roloHead.stopScanning();
              } else if (values[1].equals("SCAN-CLEAN")) {
                roloHead.cleanLastScan();
              }


            } else if (values[0].equals("SENSOR-ON")) {
              if (values[1].equals("ALL")) {
                readDistanceSensors = true;
                readLightSensors = true;
                readTouchSensors = true;
              } else if (values[1].equals("DISTANCE")) {
                readDistanceSensors = true;
              } else if (values[1].equals("LIGHT")) {
                readLightSensors = true;
              } else if (values[1].equals("TOUCH")) {
                readTouchSensors = true;
              }
            } else if (values[0].equals("SENSOR-OFF")) {
              if (values[1].equals("ALL")) {
                readDistanceSensors = false;
                readLightSensors = false;
                readTouchSensors = false;
              } else if (values[1].equals("DISTANCE")) {
                readDistanceSensors = false;
              } else if (values[1].equals("LIGHT")) {
                readLightSensors = false;
              } else if (values[1].equals("TOUCH")) {
                readTouchSensors = false;
              }

            } else if (values[0].equals("SERVO180")) {
              if (values[1].equals("CENTER")) {
                servo180.rotate(90);
              } else if (values[1].equals("LEFT")) {
                int angle = servo180.getCurrentDegree() - 10;
                if(angle < 0){
                  angle = 1;
                }
                servo180.rotate(angle);
              } else if (values[1].equals("RIGHT")) {
                int angle = servo180.getCurrentDegree() + 10;
                if(angle > 180){
                  angle = 179;
                }
                servo180.rotate(angle);
              } else {
                servo180.rotate(Integer.parseInt(values[1]));
              }

            }
          } else if (values.length == 1) {

            System.out.println("Value 0 = " + values[0]);
            if (values[0].equals("STOP-A")) {
              motorA.stop();
            } else if (values[0].equals("STOP-B")) {
              motorB.stop();
            } else if (values[0].equals("STOP-C")) {
              motorC.stop();
            } else if (values[0].equals("STOP-GLOBAL")) {
              motorA.stop();
              motorB.stop();
              motorC.stop();
            }

          }

          notifications.write(object);
        }
      } catch (HornetQException e) {
        switch (e.getCode()) {
          case HornetQException.OBJECT_CLOSED:
            logger.warn("Rolo Server: HornetQ object closed error encountered: " + getClass() + " using port " + port, e);
            break;
          default:
            logger.error(" +++ " + e.getMessage());
            break;
        }
      } catch (Exception e) {
        logger.error("Server Exception with class " + getClass() + " using port " + port + " E: " + e.getMessage(), e);

      }
    }

  }

  private Object readMessage(ClientMessage msgReceived) throws IOException {
    int bodySize = msgReceived.getBodySize();
    byte[] message = new byte[bodySize];
    msgReceived.getBodyBuffer().readBytes(message);
    ByteArrayInputStream bais = new ByteArrayInputStream(message);
    try {
      ObjectInputStream ois = new ObjectInputStream(bais);
      return ois.readObject();
    } catch (IOException e) {
      throw new IOException("Error reading message", e);
    } catch (ClassNotFoundException e) {
      throw new IOException("Error creating message", e);
    }
  }

  public void start() throws Exception {

    Map<String, Object> connectionParams = new HashMap<String, Object>();
    connectionParams.put(TransportConstants.PORT_PROP_NAME, port);
    connectionParams.put(TransportConstants.HOST_PROP_NAME, host);

    if (!standalone) {
      if (configuration == null) {
        configuration = new ConfigurationImpl();
        configuration.setPersistenceEnabled(false);
        configuration.setSecurityEnabled(false);
        configuration.setClustered(false);
      }

      TransportConfiguration transpConf = new TransportConfiguration(NettyAcceptorFactory.class.getName(), connectionParams);

      HashSet<TransportConfiguration> setTransp = new HashSet<TransportConfiguration>();
      setTransp.add(transpConf);

      configuration.setAcceptorConfigurations(setTransp);

      server = HornetQServers.newHornetQServer(configuration);
      server.start();
      embeddedServerRunning = true;
    }

    TransportConfiguration transportConfiguration = new TransportConfiguration(NettyConnectorFactory.class.getCanonicalName(), connectionParams);
    serverLocator = HornetQClient.createServerLocatorWithoutHA(transportConfiguration);
    ClientSessionFactory factory = serverLocator.createSessionFactory(transportConfiguration);
    session = factory.createSession();
    try {
      session.createQueue(SERVER_TASK_COMMANDS_QUEUE, SERVER_TASK_COMMANDS_QUEUE, true);
    } catch (HornetQException e) {
      if (e.getCode() != HornetQException.QUEUE_EXISTS) {
        logger.info(e.getMessage());
        throw new RuntimeException("Server Exception with class " + getClass() + " using port " + port, e);
      }
    }
    consumer = session.createConsumer(SERVER_TASK_COMMANDS_QUEUE);
    session.start();
    running = true;
  }

  public void stop() throws Exception {
    if (running) {
      running = false;
      closeAll();
    }
    if (embeddedServerRunning) {
      embeddedServerRunning = false;
      closeAll();
      server.stop();
      serverLocator.close();
    }
  }

  private void closeAll() throws HornetQException {
    if (!session.isClosed()) {
      session.close();
    }
    if (!consumer.isClosed()) {
      consumer.close();
    }
  }

  public boolean isRunning() {
    return running;
  }

  public Configuration getConfiguration() {
    return configuration;
  }

  public void setConfiguration(Configuration configuration) {
    this.configuration = configuration;
  }

  public boolean isStandalone() {
    return standalone;
  }

  public void setStandalone(boolean standalone) {
    this.standalone = standalone;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }
}