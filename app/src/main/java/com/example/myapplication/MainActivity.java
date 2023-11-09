package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
    }
    protected void doInBackground() throws IOException {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}