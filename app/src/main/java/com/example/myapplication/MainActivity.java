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

                //Buttons:
                TextView temperature = findViewById(R.id.Temperature);
                TextView windspeed = findViewById(R.id.WindSpeed);
                TextView cloudiness = findViewById(R.id.Cloudiness);
                TextView precipitation = findViewById(R.id.Precipitation);
                ImageView currentWeather = findViewById(R.id.current_weather);

                //Async task to make API calls:
                RetrieveFeedTask rft = new RetrieveFeedTask();
                rft.temperatureButton = temperature;
                rft.precipitationButton = precipitation;
                rft.cloudinessButton = cloudiness;
                rft.windspeedButton = windspeed;
                //TODO: Add current weather button. Exists in WeatherData object but not given value
                rft.execute("https://api.met.no/weatherapi/locationforecast/2.0/?lat=63.39;lon=17.28");

            }
        });

    }

}

