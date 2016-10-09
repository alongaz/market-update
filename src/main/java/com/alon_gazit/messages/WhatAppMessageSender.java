package com.alon_gazit.messages;

import com.alon_gazit.model.SymbolMessage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by alon.g on 10/8/2016.
 */
public class WhatAppMessageSender {
    // No need to change the following two lines unless you have a Premium Account
    private static final String CLIENT_ID = "FREE_TRIAL_ACCOUNT";
    private static final String CLIENT_SECRET = "PUBLIC_SECRET";
    private static final String WA_GATEWAY_URL = "http://api.whatsmate.net/v1/whatsapp/queue/message";
    private static final String number = "+972523295574";

    /**
     * Entry Point
     */
    public static void main(String[] args) throws Exception {
        SymbolMessage message = new SymbolMessage();
        message.setSymbol("SPY");
        message.setText(" Pass 40 days highest high.");
        message.setPrice(200.15);
        WhatAppMessageSender.sendMessage( message);
    }

    /**
     * Sends out a WhatsApp message via WhatsMate WA Gateway.
     */
    public static void sendMessage(SymbolMessage message) throws Exception {
        // TODO: Should have used a 3rd party library to make a JSON string from an object
        String jsonPayload = new StringBuilder()
                .append("{")
                .append("\"number\":\"")
                .append(number)
                .append("\",")
                .append("\"message\":\"")
                .append(message.getMessage())
                .append("\"")
                .append("}")
                .toString();

        URL url = new URL(WA_GATEWAY_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("X-WM-CLIENT-ID", CLIENT_ID);
        conn.setRequestProperty("X-WM-CLIENT-SECRET", CLIENT_SECRET);
        conn.setRequestProperty("Content-Type", "application/json");

        OutputStream os = conn.getOutputStream();
        os.write(jsonPayload.getBytes());
        os.flush();
        os.close();

        int statusCode = conn.getResponseCode();
        System.out.println("Response from WA Gateway: \n");
        System.out.println("Status Code: " + statusCode);
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (statusCode == 200) ? conn.getInputStream() : conn.getErrorStream()
        ));
        String output;
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }
        conn.disconnect();
    }
}
