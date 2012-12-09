rolo_the_robot
==============

Experimental tests with Drools/jBPM + Processing + Raspberry Pi + Arduino + Lego

Install

On Raspberry Pi:

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
