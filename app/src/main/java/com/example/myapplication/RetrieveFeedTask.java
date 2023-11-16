package com.example.myapplication;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RetrieveFeedTask extends AsyncTask<String, Void, String> {

    public interface Response{
        void finish(String output);
    }

    public Response gate;

    public RetrieveFeedTask(Response gate){
        this.gate = gate;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    protected String doInBackground(String... urls){
        String content = "", line;

        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int i = 0;

            while(connection.getResponseCode() != 200 && i++ != 10){
                connection = (HttpURLConnection) url.openConnection();
                System.out.println("Failed to connect");
            }
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                while((line = rd.readLine()) != null){
                    content += line + "\n";
                }
                rd.close();
            }catch (Exception ex){
                ex.printStackTrace();
                System.out.println("Failed");
            }
            finally {
                connection.disconnect();
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return content;
    }
    protected void onProgressUpdate(Integer... progress){

    }

    @Override
    protected void onPostExecute(String result){
        gate.finish(result);
    }
}