package com.example.myapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class WeatherData {
    public String direction;
    public String air_temp;
    public String wind_speed;
    public String cloud;
    public String percipitationMin;
    public String percipitationMax;
    public int currentWeather;

    public WeatherData(JSONObject obj){
        try {

            //TODO: Current grabbing of data below gives incorrect values.
            air_temp = obj.getJSONObject("properties").getJSONArray("timeseries").getJSONObject(0).getJSONObject("data").getJSONObject("instant").getJSONObject("details").getString("air_temperature");
            wind_speed = obj.getJSONObject("properties").getJSONArray("timeseries").getJSONObject(0).getJSONObject("data").getJSONObject("instant").getJSONObject("details").getString("wind_speed");
            cloud = obj.getJSONObject("properties").getJSONArray("timeseries").getJSONObject(0).getJSONObject("data").getJSONObject("instant").getJSONObject("details").getString("cloud_area_fraction");
            percipitationMin = obj.getJSONObject("properties").getJSONArray("timeseries").getJSONObject(0).getJSONObject("data").getJSONObject("next_1_hours").getJSONObject("details").getString("precipitation_amount_min");
            percipitationMax = obj.getJSONObject("properties").getJSONArray("timeseries").getJSONObject(0).getJSONObject("data").getJSONObject("next_1_hours").getJSONObject("details").getString("precipitation_amount_max");
            currentWeather = getImageResourceId(obj);



            float windDirection = Float.parseFloat(obj.getJSONObject("properties").getJSONArray("timeseries").getJSONObject(0).getJSONObject("data").getJSONObject("instant").getJSONObject("details").getString("wind_from_direction"));
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

    public int getImageResourceId(JSONObject obj) throws JSONException {
        double cloudiness = Double.parseDouble(cloud);
        double temp = Double.parseDouble(air_temp);
        double wind = Double.parseDouble(wind_speed);
        double precipitationAmountMin = Double.parseDouble(percipitationMin);
        double precipitationAmountMax = Double.parseDouble(percipitationMax);



        if (cloudiness > 0.5) {
            return R.drawable.cloudy;
        } else if (temp < 0.0 && wind > 10.0) {
            return R.drawable.snow;
        } else if(precipitationAmountMax >= 2.5){
            return R.drawable.rain;
        } else if (precipitationAmountMax >= 2.5 && precipitationAmountMax < 10.0){
            return R.drawable.heavyrain;
        } else if (precipitationAmountMin > 0.0 && temp <= 0.0 ){
            return R.drawable.sleet;
        } else if (precipitationAmountMin > 0.0 && precipitationAmountMax < 2.5){
            return R.drawable.lightrain;
        } else if ( temp >= -10 && temp < 0.0){
            return R.drawable.heavysnow;
        } else if (temp < 5.0 && wind < 5.0 && (precipitationAmountMin + precipitationAmountMax) < 1.0){
            return R.drawable.fog;
        } else if (cloudiness > 0.2 && cloudiness < 0.8){
            return R.drawable.partlycloudy_day;
        }
        else {
            return R.drawable.clearsky_day;
        }
    }

}
