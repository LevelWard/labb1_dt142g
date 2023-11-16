package com.example.myapplication;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherData {
    public String direction;
    public String air_temp;
    public String wind_speed;
    public String cloud;
    public String percipitationMin;
    public String percipitationMax;
    public String currentWeather;

    public WeatherData(JSONObject obj){
        try {

            //TODO: Current grabbing of data below gives incorrect values.
            air_temp = obj.getJSONObject("properties").getJSONObject("meta").getJSONObject("units").getString("air_temperature");
            wind_speed = obj.getJSONObject("properties").getJSONObject("meta").getJSONObject("units").getString("wind_speed");
            cloud = obj.getJSONObject("properties").getJSONObject("meta").getJSONObject("units").getString("cloud_area_fraction");
            percipitationMin = obj.getJSONObject("properties").getJSONObject("meta").getJSONObject("units").getString("precipitation_amount_min");
            percipitationMax = obj.getJSONObject("properties").getJSONObject("meta").getJSONObject("units").getString("precipitation_amount_max");

            float windDirection = Float.parseFloat(obj.getJSONObject("properties").getJSONObject("meta").getJSONObject("units").getString("wind_from_direction"));
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


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }


}
