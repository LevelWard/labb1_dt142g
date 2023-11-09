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

                temperature.setText("Temperature: " + "Variabel");
                windspeed.setText("WindSpeed: " + "Variabel");
                cloudiness.setText("Cloudiness: " + "Variabel");
                precipitation.setText("Precipitation: Between " + "Variable 1" + " and " + "Variabel 2");
                currentWeather.setImageResource(R.drawable.ic_launcher_foreground);
            }
        });
    }
}

