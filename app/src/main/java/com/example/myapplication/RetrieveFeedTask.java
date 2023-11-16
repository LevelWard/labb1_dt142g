package com.example.myapplication;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

class RetrieveFeedTask extends AsyncTask<String, Void, Void> {

    private Exception exception;

    protected Void doInBackground(String... urls) {
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

        } catch (Exception e) {
            this.exception = e;

        } finally {
        }
        return null;
    }

    protected void onPostExecute(Void feed) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }
}
