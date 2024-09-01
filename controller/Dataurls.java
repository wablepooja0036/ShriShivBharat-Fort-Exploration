
package com.c2w.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.management.RuntimeErrorException;

import org.json.JSONObject;
//unsplash imaage url link getting loaded
public class Dataurls {
    public static String imgurl="";

    public StringBuffer getResponseData(String url) throws IOException {
        HttpURLConnection httpclient = (HttpURLConnection) new URL(url).openConnection();
        httpclient.setRequestMethod("GET");
        StringBuffer response = new StringBuffer();

        int responseCode = httpclient.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpclient.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response;
        } else {
            throw new RuntimeException("GET Request failed with response code " + responseCode);
        }
    }
           public static void imageData(String url) throws IOException {
            Dataurls dataurls = new Dataurls();
            StringBuffer response = dataurls.getResponseData(url);
            if (response != null) {
                JSONObject obj = new JSONObject(response.toString());
                JSONObject urlobj = obj.getJSONObject("urls");
                imgurl = urlobj.getString("full");
            } else {
                System.out.println("Response is Empty");
            }
        }
    
}
