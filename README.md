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

Install Java, Maven and Git

sudo apt-get install maven2 git

Clone sources:

git clone https://github.com/Salaboy/rolo_the_robot.git

from src/main/resources/lib

mvn install:install-file  -Dfile=verletphysics.jar -DgroupId=toxiclibs -DartifactId=verletphysics -Dversion=1.0.0 -Dpackaging=jar

mvn install:install-file  -Dfile=sprites.jar -DgroupId=processing-sprites -DartifactId=sprites -Dversion=1.0.0 -Dpackaging=jar

mvn install:install-file  -Dfile=promidi.jar -DgroupId=promidi -DartifactId=promidi -Dversion=1.0.0 -Dpackaging=jar

mvn install:install-file  -Dfile=hidapi-1.1.jar   -DgroupId=hid -DartifactId=hid -Dversion=1.0.0 -Dpackaging=jar

mvn install:install-file  -Dfile=toxiclibscore.jar -DgroupId=toxiclibs -DartifactId=core -Dversion=1.0.0 -Dpackaging=jar

Clone JAVAHIDAPI with hg. We need to do this because the HIDAPI needs to be compiled for the arm7 platform

Install HG

hg clone https://code.google.com/p/javahidapi/

sudo apt-get install libusb-1.0-0-dev

sudo apt-get install libudev-dev

cd javahidapi/linux

Modify Makefile to contain the correct pointers for openjdk6-armhf headers

JAVA6HEADERS=-I/usr/lib/jvm/java-6-openjdk-armhf/include/ -I/usr/lib/jvm/java-6-openjdk-armhf/include/linux
cd linux/
make

Run (to compile):

ant 

Test: 
Before modify   <jvmarg value="-Djava.library.path=${basedir}/linux"/> to   <jvmarg value="-Djava.library.path=${basedir}/mac"/>
Also change classpath to build instead of bin
ant run

Plug an USB device and run

ant run

You should see the device being listed. The test is waiting for a PS3 controller

ant dist -> to get a jar, we need to manually install it inside the maven repo, so it can be picked up by maven


In order to work with RXTX in the PI we need to install the following package:
sudo apt-get install librxtx-java

Create a simbolic link to the real RXTXcomm.jar from your default jvm:
from: /usr/lib/jvm/java-6-openjdk-armhf/jre/lib/ext

sudo ln -s ../../../../java-6-openjdk-common/jre/lib/ext/RXTXcomm.jar RXTXcomm.jar

Then it will look like this: RXTXcomm.jar -> ../../../../java-6-openjdk-common/jre/lib/ext/RXTXcomm.jar

In order to use the system installed jars and native extensions we need to export:

export MAVEN_OPTS="-Djava.ext.dirs=/usr/share/java/ -Djava.library.path=/usr/lib/jni/"
