package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.refresh);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView temperature = findViewById(R.id.Temperature);
                TextView windspeed = findViewById(R.id.WindSpeed);
                TextView cloudiness = findViewById(R.id.Cloudiness);
                TextView precipitation = findViewById(R.id.Precipitation);
                ImageView currentWeather= findViewById(R.id.current_weather);

<<<<<<< HEAD
                //Temp for getting wind direction where N = North, E = East, S = South, W = West

                //Needs to get value from parser
                float windDirection = 0, temperatureValue = 0, windspeedValue = 0,
                        cloudinessValue = 0, precipitationMinValue = 0, precipitationMaxValue = 0;
                String direction = "";

                if (windDirection > 330 || windDirection < 30){
                    direction = "N";
                }
                else if(windDirection > 30 && windDirection < 60){
                    direction = "NE";
                } else if(windDirection > 60 && windDirection < 120) {
                    direction = "E";
                } else if (windDirection > 120 && windDirection < 150) {
                    direction = "SE";
                } else if (windDirection > 150 && windDirection < 210) {
                    direction = "S";
                } else if (windDirection > 210 && windDirection < 240) {
                    direction = "SW";
                } else if (windDirection > 240 && windDirection < 300) {
                    direction = "W";
                } else if (windDirection > 300 && windDirection < 330) {
                    direction = "NW";
                }


                temperature.setText("Temperature: " + temperatureValue);
                windspeed.setText("WindSpeed: " + windspeedValue);
                cloudiness.setText("Cloudiness: " + cloudinessValue);
                precipitation.setText("Precipitation: Between " + precipitationMinValue +
                        " and " + precipitationMaxValue);
                currentWeather.setImageResource(R.drawable.ic_launcher_foreground);
            }
        });
    }
}

