package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Entire api request process: Data is found in "url.openStream()"
        try {
            //URL we will connect to
            URL url = new URL("https://api.met.no/weatherapi/locationforecast/2.0/?lat=63.39;lon=17.28");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                System.out.println("Vi har klarat det!\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}