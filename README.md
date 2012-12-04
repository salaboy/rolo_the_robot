rolo_the_robot
==============

Experimental tests with Drools/jBPM + Processing + Raspberry Pi + Arduino + Lego

Install

On Raspberry Pi:

Install Java, Maven and Git

sudo apt-get install maven2 git

Clone sources:

git clone https://github.com/Salaboy/rolo_the_robot.git


Clone JAVAHIDAPI with hg. We need to do this because the HIDAPI needs to be compiled for the arm7 platform

Install HG

hg clone https://code.google.com/p/javahidapi/

Modify build.xml to point to linux

ant compile

Plug an USB device and run

ant run

You should see the device being listed. The test is waiting for a PS3 controller

ant package -> to get a jar, we need to manually install it inside the maven repo, so it can be picked up by maven
