# IOT-Based-Door-Unlocking-System
Introduction
============
This project proposes a remote access door unlocking system for homes and office buildings. This system comprises of the internet to unlock the Door at home or office with the person controlling them from anywhere around the globe. In our proposed system, a Raspberry Pi board is used as the platform for monitoring the visitors who pressed the doorbell and controlling the door lock. The door unlocking system proposed here consists of a Push Button(Doorbell)  for guest monitoring, camera for guest authentication, solenoid actuator for locking/unlocking of the door and a speaker for making the system intimate the responses to the guest. Push Button(Doorbell), speakers and camera for interaction with the guest are mounted at appropriate places at the door. Status of the Push Button can be monitored by the Raspberry Pi. As the guest presses the Push Buton on arriving at the door, Raspberrypi captures the Multiple images and form GIF and send it to the database . The main advantage of our proposed system is that it can be easily used in home without requirement of any new software installation and configuration.

Hardware Requirement
===================
* Raspberrypi
* Solenoid Lock
* Relay 5v
* Push Button
* PiCamera
* Power Adapter (5V/2A & 12v/1A)

Software Requirement
===================
* Raspbian
* Apache Server
* VNC
* PHP

# Installation
============

Raspbian
========
Download Link: https://www.raspberrypi.org/downloads/raspbian/
Unzip the file
Write the disc image to your microSD card using Eatcher : https://www.balena.io/etcher/
Put the microSD card in your Pi and boot up


Apache & PHP
===========
sudo apt-get update

sudo apt-get install apache2 -y

sudo apt-get install php libapache2-mod-php -y

sudo rm index.html //Remove index.html file

sudo leafpad index.php  //Create index.php file

Test Web Server
==============
http://localhost/ or http://192.168.1.10 (whatever the Pi's IP address is)

Video Link
===========
https://drive.google.com/file/d/1-6yvX_FHhISnEQYHFQGhtkl9OxrurznH/view?usp=drivesdk
