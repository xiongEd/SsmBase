package com.study.DataCollection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyCollection {

    public static String analysisUrl(String url){
        HttpURLConnection httpConnection = null;
        String output = "";
        try {
            URL targetUrl = new URL(url);
            httpConnection = (HttpURLConnection) targetUrl.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Content-Type",
                    "application/json");
            InputStreamReader isr = new InputStreamReader(httpConnection
                    .getInputStream(),"utf-8");
            BufferedReader responseBuffer = new BufferedReader(isr);
            output = responseBuffer.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpConnection.disconnect();
        }
        return output;
    }

    public static void main(String[] args) {
        String url = "http://guolin.tech/api/china";
        String result = analysisUrl(url);
        System.out.println(result);
    }
}
