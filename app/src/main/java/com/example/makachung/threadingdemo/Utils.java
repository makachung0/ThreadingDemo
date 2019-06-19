package com.example.makachung.threadingdemo;

import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {
    public static String sendHttpRequest() {
        Log.d("log", "sendHttpRequest");
        HttpURLConnection connection = null;
        String responseCode = null;
        try {
            connection = (HttpURLConnection) new URL("https://google.com").openConnection();
            connection.setReadTimeout(3000);
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("GET");
            connection.connect();
            Thread.sleep(5000);
            responseCode = String.valueOf(connection.getResponseCode());
            //rightApproach(responseCode);
        } catch (IOException |InterruptedException e) {
            Log.e("Error", e.getMessage(), e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return responseCode;
    }
}
