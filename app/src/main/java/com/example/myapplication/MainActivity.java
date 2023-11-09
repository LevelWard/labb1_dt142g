package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.refresh).setVisibility(View.VISIBLE);

                TextView tempLabel= findViewById(R.id.Temperature);
                tempLabel.setText("Temperature" + " varibel 1");
                TextView windSpeed = findViewById(R.id.WindSpeed);
                windSpeed.setText("Windspeed" + " variabel 2");

                TextView cloudiness = findViewById(R.id.Cloudiness);
                cloudiness.setText("cloudiness" + " varibale 1");
                TextView precipitation = findViewById(R.id.Precipitation);
                precipitation.setText("Precipitation" + " hej");
            }
        });
    }


}