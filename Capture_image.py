#import modules
import pyrebase
import RPi.GPIO as GPIO
from time import sleep
import os
import picamera
from fractions import Fraction
import time
import datetime
config ={   # Copy config details from your firebase account
    "apiKey": "",
    "authDomain": "",
    "databaseURL": "",
    "projectId": "",
    "storageBucket": "",
    "messagingSenderId": ""
  };
GPIO.setmode(GPIO.BCM)
GPIO.setup(25, GPIO.IN, pull_up_down=GPIO.PUD_UP)
try: 
  while True:
    press = GPIO.input(25)
    if (press == 0):
            print("Button is pressed")
            with picamera.PiCamera() as camera: #initiate picamera module and class
                date= time.strftime("%d_%b_%Y(%H:%M:%S)")
                startdate=int(datetime.datetime.now().strftime("%s")) * 1000
                enddate=2551648920000
                finaldate=enddate-startdate
                camera.resolution = (360,240) #set resolution of picture here
                camera.brightness = 60 #set brightness settings to help with dark photos
                camera.saturation = -30
                camera.annotate_foreground = picamera.Color(y=0.2, u=0, v=0) #set color of annotation 
                camera.start_preview()
                camera.annotate_text = 'Get Ready!'
                time.sleep(1)
                camera.annotate_text = ''
                for i, filename in enumerate(camera.capture_continuous('image{counter:02d}.jpg')):    
                    if i == 1:
                        camera.annotate_text = ''
                    elif i == 2:
                        camera.annotate_text = ''
                    elif i == 3:
                        camera.annotate_text = ''
                    elif i == 4:
                        camera.annotate_text = ''
                    elif i == 5:
                        camera.annotate_text = ''
                    elif i == 6:
                        camera.annotate_text = ''
                    elif i == 7:
                        camera.annotate_text = ''
                    elif i == 8:
                        camera.annotate_text = ''
                    elif i == 9:
                        camera.annotate_text = ''
                    elif i == 10:
                        camera.annotate_text = ''
                    elif i == 11:
                        camera.annotate_text = ''
                    elif i == 12:
                        camera.annotate_text = ''
                    if i == 13:
                        break
                camera.stop_preview() #stop preview
            os.system('convert -delay 30 image*.jpg animation.gif') #send command to convert images to GIF
            print("done"); #let us know photo is about to start uploading
            
            print("Initializing connection..")
            firebase = pyrebase.initialize_app(config)
            storage = firebase.storage()
            db = firebase.database()
            db.child("notification").child("notify_status").set(0)
            db.child("notification").child("notify_status").set(1)
            db.child("notification").child("notify_status").set(0)
            print("Uploading GIF..")
            storage.child("Visitor_Record/"+date+"animation.gif").put("animation.gif")
            print("GIF uploaded!");
            url = storage.child("Visitor_Record/"+date+"animation.gif").get_url("none")
            print(url)

            print("Saving URL to Database..")
            
            db.child("chat").child(finaldate).child("imageUrl").set(url)
            db.child("chat").child(finaldate).child("name").set("Raspberry Pi")
            db.child("chat").child(finaldate).child("description").set("Raspberry Pi"+" "+date)
            print("URL saved!")
            print("\n")
            
          
finally:
    GPIO.cleanup()
