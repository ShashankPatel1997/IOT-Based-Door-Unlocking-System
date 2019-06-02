import pyrebase
from pusher_push_notifications import PushNotifications

config ={
    "apiKey": "AIzaSyB36ZzOR77egxe7iUWkR9DC6-jZDEplNqI",
    "authDomain": "smartdoorfinal-a7750.firebaseapp.com",
    "databaseURL": "https://smartdoorfinal-a7750.firebaseio.com",
    "projectId": "smartdoorfinal-a7750",
    "storageBucket": "smartdoorfinal-a7750.appspot.com",
    "messagingSenderId": "1008216095743"
  };

firebase = pyrebase.initialize_app(config)
db=firebase.database()
pn_client = PushNotifications(
    instance_id='7079dca0-843c-49fd-9479-defbc306ecd2',
    secret_key='4B882BD902AEE83ADFC91935E01F3CD04D6EE026FE63BEACDC1CD879B4AE42B9',
)

def stream_handler(message):
    print(message)
    if(message['data'] is 1):
        response = pn_client.publish_to_interests( interests=['hello'],publish_body={ 'apns': {'aps': {'alert': 'Hello!'}},'fcm': {'notification': {'title': 'Alert!!','sound':'mySound','body': 'Someone Pressed the Doorbell !'}}})
        print(response['publishId'])
    
my_stream = db.child("notification").child("notify_status").stream(stream_handler,None)
    