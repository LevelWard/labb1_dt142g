package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FetchWeatherTask().execute();
            }
        });
    }

    private class FetchWeatherTask extends AsyncTask<Void, Void, WeatherData> {

        @Override
        protected WeatherData doInBackground(Void... voids) {
            try {
                URL url = new URL("http://api.met.no/weatherapi/locationforecast/1.9/?lat=60.10;lon=9.58");
                URLConnection urlConn = url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                StringBuilder responseStringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    responseStringBuilder.append(line);
                }

                String jsonResponse = responseStringBuilder.toString();
                Log.d("WeatherApp", "API Response: " + jsonResponse);

                JSONObject obj = new JSONObject(jsonResponse);

                String air_temperature = obj.getJSONObject("properties").getJSONObject("timeseries").getJSONObject("data").getJSONObject("instant").getString("air_temperature");
                String wind_speed = obj.getJSONObject("properties").getJSONObject("timeseries").getJSONObject("data").getJSONObject("instant").getString("wind_speed");
                String cloud = obj.getJSONObject("properties").getJSONObject("timeseries").getJSONObject("data").getJSONObject("instant").getString("cloud_area_fraction");
                String precipi_amount_min = obj.getJSONObject("properties").getJSONObject("timeseries").getJSONObject("data").getJSONObject("instant").getString("precipitation_amount_min");
                String precipi_amount_max = obj.getJSONObject("properties").getJSONObject("timeseries").getJSONObject("data").getJSONObject("instant").getString("precipitation_amount_max");

                float temperatureValue = Float.parseFloat(air_temperature);
                float windspeedValue = Float.parseFloat(wind_speed);
                float cloudinessValue = Float.parseFloat(cloud);
                float precipitationMinValue = Float.parseFloat(precipi_amount_min);
                float precipitationMaxValue = Float.parseFloat(precipi_amount_max);

                return new WeatherData(temperatureValue, windspeedValue, cloudinessValue, precipitationMinValue, precipitationMaxValue);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(WeatherData weatherData) {
            if (weatherData != null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateUI(weatherData);
                    }
                });
            }
        }
    }

    private void updateUI(WeatherData weatherData) {
        TextView temperatureTextView = findViewById(R.id.Temperature);
        temperatureTextView.setText("Temperature: " + weatherData.getTemperature());

        TextView windSpeedTextView = findViewById(R.id.WindSpeed);
        windSpeedTextView.setText("Wind Speed: " + weatherData.getWindSpeed());

        TextView cloudinessTextView = findViewById(R.id.Cloudiness);
        cloudinessTextView.setText("Cloudiness: " + weatherData.getCloudiness() + "%");

        TextView precipitationTextView = findViewById(R.id.Precipitation);
        precipitationTextView.setText("Precipitation: " + weatherData.getPrecipitationMin() + "mm and " + weatherData.getPrecipitationMax() + "mm");
    }

    public class WeatherData {
        private final float temperature;
        private final float windSpeed;
        private final float cloudiness;
        private final float precipitationMin;
        private final float precipitationMax;

        public WeatherData(float temperature, float windSpeed, float cloudiness, float precipitationMin, float precipitationMax) {
            this.temperature = temperature;
            this.windSpeed = windSpeed;
            this.cloudiness = cloudiness;
            this.precipitationMin = precipitationMin;
            this.precipitationMax = precipitationMax;
        }

        public float getTemperature() {
            return temperature;
        }

        public float getWindSpeed() {
            return windSpeed;
        }

        public float getCloudiness() {
            return cloudiness;
        }

        public float getPrecipitationMin() {
            return precipitationMin;
        }

        public float getPrecipitationMax() {
            return precipitationMax;
        }
    }
}