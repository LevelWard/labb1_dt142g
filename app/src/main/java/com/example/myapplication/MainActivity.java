package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView;

import org.json.JSONArray;
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
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                TextView temperature = findViewById(R.id.Temperature);
                TextView windspeed = findViewById(R.id.WindSpeed);
                TextView cloudiness = findViewById(R.id.Cloudiness);
                TextView precipitation = findViewById(R.id.Precipitation);
                ImageView currentWeather = findViewById(R.id.current_weather);
                try {
                    URL url = new URL("https://api.met.no/weatherapi/locationforecast/2.0/?lat=63.39088;lon=17.28709");
                    URLConnection urlConn = null;
                    BufferedReader bufferedReader = null;
                    urlConn = url.openConnection();
                    urlConn.addRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:109.0) Gecko/20100101 Firefox/119.0");
                    bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                    String line = bufferedReader.readLine();
                    JSONObject obj = new JSONObject(line);

                    String air_temp = obj.getJSONObject("properties").getJSONArray("timeseries").getJSONObject(0).getJSONObject("data").getJSONObject("instant").getJSONObject("details").getString("air_temperature");
                    String wind_speed = obj.getJSONObject("properties").getJSONArray("timeseries").getJSONObject(0).getJSONObject("data").getJSONObject("instant").getJSONObject("details").getString("wind_speed");
                    String wind_direction = obj.getJSONObject("properties").getJSONArray("timeseries").getJSONObject(0).getJSONObject("data").getJSONObject("instant").getJSONObject("details").getString("wind_from_direction");
                    String cloud = obj.getJSONObject("properties").getJSONArray("timeseries").getJSONObject(0).getJSONObject("data").getJSONObject("instant").getJSONObject("details").getString("cloud_area_fraction");
                    String precipi_amount_min = obj.getJSONObject("properties").getJSONArray("timeseries").getJSONObject(0).getJSONObject("data").getJSONObject("next_1_hours").getJSONObject("details").getString("precipitation_amount_min");
                    String precipi_amount_max = obj.getJSONObject("properties").getJSONArray("timeseries").getJSONObject(0).getJSONObject("data").getJSONObject("next_1_hours").getJSONObject("details").getString("precipitation_amount_max");

                    //TODO: Remove these? They aren't used
                    JSONObject test = obj.getJSONObject("properties").getJSONArray("timeseries").getJSONObject(1);
                    /*String precipi_amount =*/ obj.getJSONObject("properties").getJSONObject("meta").getJSONObject("units").getString("precipitation_amount");
                    /*JSONArray arr =*/ obj.getJSONObject("geometry").getJSONArray("coordinates"); // always throws exception

                            Float   windDirection = Float.parseFloat(wind_direction),
                                    temperatureValue = Float.parseFloat(air_temp),
                                    windspeedValue = Float.parseFloat(wind_speed),
                                    cloudinessValue = Float.parseFloat(cloud),
                                    precipitationMinValue = Float.parseFloat(precipi_amount_min),
                                    precipitationMaxValue = Float.parseFloat(precipi_amount_max);


                    String direction = "";
                    //Temp for getting wind direction where N = North, E = East, S = South, W = West
                    if (windDirection > 330 || windDirection < 30) {
                        direction = "N";
                    } else if (windDirection > 30 && windDirection < 60) {
                        direction = "NE";
                    } else if (windDirection > 60 && windDirection < 120) {
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
                } catch (Exception e) {

                }
            }
        });

        thread.start();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        TextView temperature = findViewById(R.id.Temperature);
                        TextView windspeed = findViewById(R.id.WindSpeed);
                        TextView cloudiness = findViewById(R.id.Cloudiness);
                        TextView precipitation = findViewById(R.id.Precipitation);
                        ImageView currentWeather = findViewById(R.id.current_weather);
                        try {
                            URL url = new URL("https://api.met.no/weatherapi/locationforecast/2.0/?lat=63.39088;lon=17.28709");
                            URLConnection urlConn = null;
                            BufferedReader bufferedReader = null;
                            urlConn = url.openConnection();
                            urlConn.addRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:109.0) Gecko/20100101 Firefox/119.0");
                            bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                            String line = bufferedReader.readLine();
                            JSONObject obj = new JSONObject(line);

                            String air_temp = obj.getJSONObject("properties").getJSONArray("timeseries").getJSONObject(0).getJSONObject("data").getJSONObject("instant").getJSONObject("details").getString("air_temperature");
                            String wind_speed = obj.getJSONObject("properties").getJSONArray("timeseries").getJSONObject(0).getJSONObject("data").getJSONObject("instant").getJSONObject("details").getString("wind_speed");
                            String wind_direction = obj.getJSONObject("properties").getJSONArray("timeseries").getJSONObject(0).getJSONObject("data").getJSONObject("instant").getJSONObject("details").getString("wind_from_direction");
                            String cloud = obj.getJSONObject("properties").getJSONArray("timeseries").getJSONObject(0).getJSONObject("data").getJSONObject("instant").getJSONObject("details").getString("cloud_area_fraction");
                            String precipi_amount_min = obj.getJSONObject("properties").getJSONArray("timeseries").getJSONObject(0).getJSONObject("data").getJSONObject("next_1_hours").getJSONObject("details").getString("precipitation_amount_min");
                            String precipi_amount_max = obj.getJSONObject("properties").getJSONArray("timeseries").getJSONObject(0).getJSONObject("data").getJSONObject("next_1_hours").getJSONObject("details").getString("precipitation_amount_max");

                            //TODO: Remove these? They aren't used
                            JSONObject test = obj.getJSONObject("properties").getJSONArray("timeseries").getJSONObject(1);
                            /*String precipi_amount =*/ obj.getJSONObject("properties").getJSONObject("meta").getJSONObject("units").getString("precipitation_amount");
                            /*JSONArray arr =*/ obj.getJSONObject("geometry").getJSONArray("coordinates"); // always throws exception

                            Float   windDirection = Float.parseFloat(wind_direction),
                                    temperatureValue = Float.parseFloat(air_temp),
                                    windspeedValue = Float.parseFloat(wind_speed),
                                    cloudinessValue = Float.parseFloat(cloud),
                                    precipitationMinValue = Float.parseFloat(precipi_amount_min),
                                    precipitationMaxValue = Float.parseFloat(precipi_amount_max);


                            String direction = "";
                            //Temp for getting wind direction where N = North, E = East, S = South, W = West
                            if (windDirection > 330 || windDirection < 30) {
                                direction = "N";
                            } else if (windDirection > 30 && windDirection < 60) {
                                direction = "NE";
                            } else if (windDirection > 60 && windDirection < 120) {
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
                        } catch (Exception e) {

                        }
                    }
                });
                thread.start();
            }
        });
    }
}