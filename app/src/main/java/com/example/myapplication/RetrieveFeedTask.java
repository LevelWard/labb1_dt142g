package com.example.myapplication;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

class RetrieveFeedTask extends AsyncTask<String, Void, WeatherData> {

    public TextView temperatureButton;
    public TextView windspeedButton;
    public TextView cloudinessButton;
    public TextView precipitationButton;
    private Exception exception;

    protected WeatherData doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            URLConnection urlConn = url.openConnection();
            urlConn.addRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:109.0) Gecko/20100101 Firefox/119.0");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            StringBuilder add = new StringBuilder();
            String line = bufferedReader.readLine();
            while(line != null) {
                add.append(line).append('\n');
                line = bufferedReader.readLine();
            }
            JSONObject obj = new JSONObject(add.toString());
            return new WeatherData(obj);

        } catch (Exception e) {
            this.exception = e;

        } finally {
        }
        return null;
    }

    protected void onPostExecute(WeatherData data) {
        //TODO: data gives null values, something is wrong in WeatherData class. Must control JSON
        //TODO: object calls to ensure correct values.
        temperatureButton.setText("Temperature:" + data.air_temp + " celsius");
        windspeedButton.setText("Wind speed:" + data.wind_speed + " mps");
        cloudinessButton.setText("Cloudines:" + data.cloud + " %");
        String minMax = "Precipitation: " + "Between " + data.percipitationMin + " mm " + " and " + data.percipitationMax + " mm";
        precipitationButton.setText(minMax);

    }
}
