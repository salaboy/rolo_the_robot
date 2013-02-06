Rolo The Robot
==============

Experimental tests with Drools/jBPM + Processing + Raspberry Pi + Arduino + Lego
To build a scalable platform for Rules/Process driven Robots to conquer the galaxy

There are three main modules in the project at this point:

1) rolo-the-robot-main:
	This project contains the main java project which contains all the core logic. This project is prepared to run 
	on your computer or inside the Raspberry Pi. All the test were done in both a Mac Book Pro and the Raspberry Pi, and the primary 
	intention is to keep compatibilty for both. The main goal is to provide an autonomous platform to run the rule engine, and for that 
	reason, running this project in a laptop or regular computer doesn't allow the project to be small, portable and autonomous. 
2) rolo-the-robot-client:
    This project is a simple Swing application which allow us to monitor and interact with Rolo. This first version 
    uses HornetQ for sending messages back and forth with the main project.

3) rolo-the-robot-arduino:
	This project contains a modified version of the Firmata project (StandardFirmata -> firmata.org) for working with a HC_SR04 UltraSonicSensor.
	In order to provide that feature, the StandardFirmata sketch was modified to include the sensor reading using another open source 
	library which provide the support for ultrasonic sensors. In order to compile this scketch you need to have the Ultrasonic library, also provided
	installed inside the Arduino IDE. The sources for this library can be found here: https://github.com/elrodri/Ultrasonic-HC-SR04/

	(TODO: analyze new ping -> http://code.google.com/p/arduino-new-ping/ and it's integration with firmata)

Install 
=======

(TODO: Write a tutorial without compiling on the Raspberry Pi board and just use the compiled bins.)

On Raspberry Pi: (Running raspbian)

Install Java: sudo apt-get install openjdk-6-jdk

Install Maven and Git

sudo apt-get install maven git // be careful to not install maven2 because we need maven 3.0.3+

Clone sources using git:

git clone https://github.com/Salaboy/rolo_the_robot.git

We need to install extra libs which are not in maven repositories: 

In order to work with RXTX in the PI we need to install the following package:
sudo apt-get install librxtx-java

Create a simbolic link to the real RXTXcomm.jar from your default jvm:
from: /usr/lib/jvm/java-6-openjdk-armhf/jre/lib/ext


sudo ln -s /usr/share/java/RXTX-comm.jar RXTX-comm.jar

Then it will look like this: RXTXcomm.jar -> ../../../../java-6-openjdk-common/jre/lib/ext/RXTXcomm.jar



To run the Rolo Server (inside the rolo-the-robot-main/target dir, to avoid using maven on the Pi)

java -jar rolo_the_robot-main-1.0-SNAPSHOT.jar -t 100 -ip 192.168.0.x 

(-port 5445 - used by default) 


For compiling and testing on a regular PC/Laptop

RXTX-com on windows: http://rxtx.qbang.org/wiki/index.php/Installation_for_Windows

RXTX-com on Mac: http://rxtx.qbang.org/wiki/index.php/Installation_on_MacOS_X

Because I'm working with Arduino, I've installed the RXTX Libs from the Arduino IDE distribution into the 
/Library/Java/Extensions/ directory on my Mac and everything is working fine with that. 

But for Mac you will probably need: http://blog.iharder.net/2009/08/18/rxtx-java-6-and-librxtxserial-jnilib-on-intel-mac-os-x/

