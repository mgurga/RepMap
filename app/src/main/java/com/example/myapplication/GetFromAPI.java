package com.example.myapplication;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class GetFromAPI {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
    public static String getPostal(double[] in) throws IOException, JSONException {
        JSONObject json = readJsonFromUrl("http://open.mapquestapi.com/geocoding/v1/reverse?key=SAd4Ez053FHMazmKomIl1l87zIrtLmFO&location="+in[0]+","+in[1]+"&includeRoadMetadata=false&includeNearestIntersection=false\\\\r\\\\n");
        JsonElement root = new Gson().fromJson(json.toString(), JsonElement.class);
        return root.getAsJsonObject().get("results").getAsJsonArray().get(0).getAsJsonObject().get("locations").getAsJsonArray().get(0).getAsJsonObject().get("postalCode").toString().replaceAll("\"","");
    }
    public static String getState(double[] in) throws IOException, JSONException {
        JSONObject json = readJsonFromUrl("http://open.mapquestapi.com/geocoding/v1/reverse?key=SAd4Ez053FHMazmKomIl1l87zIrtLmFO&location="+in[0]+","+in[1]+"&includeRoadMetadata=false&includeNearestIntersection=false\\\\r\\\\n");
        JsonElement root = new Gson().fromJson(json.toString(), JsonElement.class);
        return root.getAsJsonObject().get("results").getAsJsonArray().get(0).getAsJsonObject().get("locations").getAsJsonArray().get(0).getAsJsonObject().get("adminArea3").toString().replaceAll("\"","");
    }
    public static String testMotiAPI() throws IOException, JSONException {
        JSONObject json = readJsonFromUrl("http://149.28.227.170:5678/");
        JsonElement root = new Gson().fromJson(json.toString(), JsonElement.class);
        return root.getAsJsonObject().get("Alabama").getAsJsonObject().get("districts").getAsJsonArray().get(0).toString().replaceAll("\"","");

    }
}
