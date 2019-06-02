package iot.com.iotbaseddoorunlockingsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.bumptech.glide.Glide;
import com.pusher.pushnotifications.PushNotifications;

import iot.com.iotbaseddoorunlockingsystem.R;
public class DetailsActivity extends AppCompatActivity {

    TextView nameDetailTextView,descriptionDetailTextView;
    ImageView DetailImageView;
    ProgressBar progressBar;

    private void initializeWidgets(){
        nameDetailTextView= findViewById(R.id.nameDetailTextView);
        descriptionDetailTextView= findViewById(R.id.descriptionDetailTextView);
        DetailImageView=findViewById(R.id.DetailImageView);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        final Button lock = (Button) findViewById(R.id.accept);
        Button alert = (Button) findViewById(R.id.deny);
        initializeWidgets();

        //RECEIVE DATA FROM ITEMSACTIVITY VIA INTENT

        Intent i = this.getIntent();
        String name = i.getExtras().getString("NAME_KEY");
        String description = i.getExtras().getString("DESCRIPTION_KEY");
        String imageURL = i.getExtras().getString("IMAGE_KEY");

        //SET RECEIVED DATA TO TEXTVIEWS AND IMAGEVIEWS

        nameDetailTextView.setText(name);
        descriptionDetailTextView.setText(description);

        Glide
                .with(this)
                .asGif()
                .load(imageURL)
                .into(DetailImageView);


        lock.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

               if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    /* accept is button 1 */
                    new Background_get().execute("btn1=1");
                }
                return true;
            }
        });

        alert.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    /* deny is button 2 */
                    new Background_get().execute("btn2=1");
                }
                return true;
            }
        });


    }

    private class Background_get extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                /* Change the IP to the IP you set in the arduino sketch */
                URL url = new URL("Your Raspberrypi IP Address/?" + params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null)
                    result.append(inputLine).append("\n");

                in.close();
                connection.disconnect();
                return result.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}

