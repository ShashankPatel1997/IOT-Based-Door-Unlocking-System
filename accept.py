import RPi.GPIO as GPIO
import time
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(15,GPIO.OUT)
GPIO.output(15,GPIO.LOW)
time.sleep(5)
GPIO.output(15,GPIO.HIGH)
GPIO.cleanup()
