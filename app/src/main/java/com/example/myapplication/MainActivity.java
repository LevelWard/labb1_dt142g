package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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



                //TODO: Make this into a standalone object with dedicated class members & methods.
                try {
                    URL url = new URL("http://api.met.no/weatherapi/locationforecast/1.9/?lat=60.10;lon=9.58");
                    URLConnection urlConn = null;
                    BufferedReader bufferedReader = null;
                    urlConn = url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                    String line = bufferedReader.readLine();
                    JSONObject obj = new JSONObject(line);
                    String longitude = obj.getJSONObject("geometry").getJSONObject("coordinates").getString("0");
                    String latitude = obj.getJSONObject("geometry").getJSONObject("coordinates").getString("1");
                    String air_temp = obj.getJSONObject("properties").getJSONObject("meta").getJSONObject("units").getString("air_temperature");
                    String wind_speed = obj.getJSONObject("properties").getJSONObject("meta").getJSONObject("units").getString("wind_speed");
                    String wind_direction = obj.getJSONObject("properties").getJSONObject("meta").getJSONObject("units").getString("wind_from_direction");
                    String cloud = obj.getJSONObject("properties").getJSONObject("meta").getJSONObject("units").getString("cloud_area_fraction");
                    String precipi_amount = obj.getJSONObject("properties").getJSONObject("meta").getJSONObject("units").getString("precipitation_amount");
                    String precipi_amount_min = obj.getJSONObject("properties").getJSONObject("meta").getJSONObject("units").getString("precipitation_amount_min");
                    String precipi_amount_max = obj.getJSONObject("properties").getJSONObject("meta").getJSONObject("units").getString("precipitation_amount_max");
                } catch (IOException | JSONException e) {
                    throw new RuntimeException(e);
                }

                //Needs to get value from parser
                    //Parser is ready! Merging contents ...
                float windDirection = 0, temperatureValue = 0, windspeedValue = 0,
                        cloudinessValue = 0, precipitationMinValue = 0, precipitationMaxValue = 0;
                String direction = "";

                //Temp for getting wind direction where N = North, E = East, S = South, W = West
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

