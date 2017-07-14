package com.system;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import java.net.URL;
import org.json.*;
import java.lang.System;

/**
 * Created by pavel on 14.07.17.
 */
public class Systems {

    public static void main(String[] args) {

        try {
            URL url = new URL("http://localhost:8080/base/store/add");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            //JSONObject df;
            String urlParameters = makeJSON();

            httpCon.setRequestMethod("POST");// или PUT
            DataOutputStream out = new DataOutputStream(
                    httpCon.getOutputStream());
            out.writeBytes(urlParameters);
            out.flush();
            out.close();
            httpCon.getInputStream();
        } catch (Exception e) {

        }
    }

    private static String makeJSON(){
        String caption = null;
        String message = null;
        String users=null;
        String []user;
        JSONObject json = new JSONObject();
        JSONArray arr = new JSONArray();
        String answer;
        boolean delivery=true, reading=true;
        try {
        caption = "f";
        System.out.println("Введите заголовок сообщения:");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        caption = bf.readLine();
        System.out.println("Введите сообщение: ");
        message = bf.readLine();
        System.out.println("Введите получателей: ");
        users = bf.readLine();
        user=users.split(" ");
        for (int i=0;i<user.length;i++)
            arr.put(user[i]);
        System.out.println("Уведомлять о доставке сообщения? [Y/N]");
            answer=bf.readLine();
            switch(answer.toUpperCase()){
                case "Y": delivery=true; break;
                case "N": delivery=false;break;
            }
        System.out.println("Уведомлять о прочтении сообщения? [Y/N]");
            answer=bf.readLine();
            switch(answer.toUpperCase()){
                case "Y": reading=true; break;
                case "N": reading=false;break;
            }
        json.put("caption", caption);
        json.put("message",message);
        json.put("users", arr);
        json.put("owner", "user");
        json.put("delivery", new Boolean(delivery));
        json.put("reading", new Boolean(reading));
        //json.put("id",new Integer(-2));

    }catch (Exception e){

    }
    return json.toString();
    }

}
