import pyrebase
from pusher_push_notifications import PushNotifications
config ={   # copy config details of firebase here
    "apiKey": "",
    "authDomain": "",
    "databaseURL": "",
    "projectId": "",
    "storageBucket": "",
    "messagingSenderId": ""
  };

firebase = pyrebase.initialize_app(config)
db=firebase.database()
pn_client = PushNotifications(   # Copy id and key from pusher account
    instance_id='',
    secret_key='',
)

def stream_handler(message):
    print(message)
    if(message['data'] is 1):  # data is a child node in firebase database
        response = pn_client.publish_to_interests( interests=['hello'],publish_body={ 'apns': {'aps': {'alert': 'Hello!'}},'fcm': {'notification': {'title': 'Alert!!','sound':'mySound','body': 'Someone Pressed the Doorbell !'}}})
        print(response['publishId'])
    
my_stream = db.child("notification").child("notify_status").stream(stream_handler,None)
    
