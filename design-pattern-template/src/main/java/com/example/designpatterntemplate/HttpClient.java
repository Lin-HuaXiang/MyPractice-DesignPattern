package com.example.designpatterntemplate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpClient {

    public static String doGet(String httpUrl) {
        HttpURLConnection connection = null;
        String result = null;
        try {
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(60000);
            connection.connect();
            if (connection.getResponseCode() == 200) {
                try (InputStream is = connection.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));) {
                    StringBuilder sbf = new StringBuilder();
                    String temp = null;
                    while ((temp = br.readLine()) != null) {
                        sbf.append(temp).append("\r\n");
                    }
                    result = sbf.toString();
                }
            }
        } catch (Exception e) {
            log.error("", e);
        } finally {
            assert connection != null;
            connection.disconnect();
        }
        return result;
    }

}
