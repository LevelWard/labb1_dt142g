package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private double lat = 63.39;
    private double lon =17.28;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.refresh);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final WeatherData weatherData = new WeatherData(lon, lat);

                weatherData.refresh(new WeatherData.Response() {
                    @Override
                    public void finish() {
                        TextView temperature = findViewById(R.id.Temperature);
                        TextView windspeed = findViewById(R.id.WindSpeed);
                        TextView cloudiness = findViewById(R.id.Cloudiness);
                        TextView precipitation = findViewById(R.id.Precipitation);



                        temperature.setText("Temperature: " + weatherData.getTemperature() + "°C");
                        windspeed.setText("WindSpeed: " + weatherData.getWindSpeed() + " mps, " + "toward " + weatherData.getDirection());
                        cloudiness.setText("Cloudiness: " +  weatherData.getCloudinessPercentage() + "%");

                        precipitation.setText("Precipitation: Between " + weatherData.getPrecipitationmin() + " mm" + " and " + weatherData.getprecipitationmax()+ " mm");
                    }

                });

            }
        });

    }

}

