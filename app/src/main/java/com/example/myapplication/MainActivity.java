package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private double lat = 60.10;
    private double lon = 9.58;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadWeatherData();
            }
        });
    }

    public void loadWeatherData(){
        findViewById(R.id.refresh).setVisibility(View.VISIBLE);

        TextView tempLabel= findViewById(R.id.Temperature);

        TextView windSpeed = findViewById(R.id.WindSpeed);

        TextView cloudiness = findViewById(R.id.Cloudiness);

        TextView precipi = findViewById(R.id.Precipitation);

        ImageView Icon = findViewById(R.id.current_weather);

    }
}