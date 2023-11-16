package com.example.myapplication;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class WeatherData {
    private String bURL = "https://api.met.no/weatherapi/locationforecast/2.0/?";
    public String direction;
    public String air_temp;
    public String wind_speed;
    public String cloud;
    public String percipitationMin;
    public String percipitationMax;

    public String currentWeather;
    private double lon;
    private double lat;

    WeatherData(double longitude, double latitude){
        this.lon = longitude;
        this.lat = latitude;
    }

    public interface Response{
        void finish();
    }
    public void refresh(final Response gate){
        String urlString = bURL + "lat=" + lat + ";lon=" + lon;

        System.out.println("Sending Request");

        new RetrieveFeedTask(output -> {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();

                InputStream is = new ByteArrayInputStream(output.getBytes("UTF-8"));
                Document doc = builder.parse(is);
                is.close();

                air_temp= doc.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("value").getTextContent();
                wind_speed = doc.getElementsByTagName("windSpeed").item(0).getAttributes().getNamedItem("mps").getTextContent();
                cloud = doc.getElementsByTagName("cloudiness").item(0).getAttributes().getNamedItem("percent").getTextContent();
                direction = doc.getElementsByTagName("windDirection").item(0).getAttributes().getNamedItem("name").getTextContent();

                NamedNodeMap nodeMap = doc.getElementsByTagName("precipitation").item(0).getAttributes();
                percipitationMin = nodeMap.getNamedItem("minvalue").getTextContent();
                percipitationMax = nodeMap.getNamedItem("maxvalue").getTextContent();
            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
                gate.finish();
            }
        }).execute(urlString);
    }



    public String getTemperature(){
        return air_temp;
    }
    public String getWindSpeed(){
        return wind_speed;
    }

    public String getDirection(){
        return direction;
    }

    public String getCloudinessPercentage(){
        return cloud;
    }

    public  String getPrecipitationmin(){
        return percipitationMin;
    }
    public String getprecipitationmax(){
        return percipitationMax;
    }





}