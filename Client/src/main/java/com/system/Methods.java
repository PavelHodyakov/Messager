package com.system;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by pavel on 18.07.17.
 */
public class Methods {

    public void AddMessage(String myname){
        try {
            URL url = new URL("http://localhost:8080/base/store/add");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            //JSONObject df;
            String urlParameters = makeJSONMessage(myname);

            httpCon.setRequestMethod("POST");// или PUT
            DataOutputStream out = new DataOutputStream(
                    httpCon.getOutputStream());
            out.writeBytes(urlParameters);
            out.flush();
            out.close();
            httpCon.getInputStream();
        } catch (Exception e) {
    System.out.println(e);
        }
    }

    private static String makeJSONMessage(String myname){
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
            json.put("owner", myname);
            json.put("delivery", new Boolean(delivery));
            json.put("reading", new Boolean(reading));
            //json.put("id",new Integer(-2));

        }catch (Exception e){

        }
        return json.toString();
    }

    public String Enter(String login, String password){
        String line="";
        try{
            URL url = new URL("http://localhost:8080/base/enter/login?name="+login+"&password="+password);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("GET");
            BufferedReader rd= new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
            line=rd.readLine();
            rd.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            return line;
        }
    }

    public String Registry(){
        String line="";
        try{
        URL url = new URL("http://localhost:8080/base/enter/registry");
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        //JSONObject df;
        String urlParameters = makeJSONRegistry();

        httpCon.setRequestMethod("POST");// или PUT
        DataOutputStream out = new DataOutputStream(
                httpCon.getOutputStream());
        out.writeBytes(urlParameters);
        out.flush();
            BufferedReader rd= new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
            line = rd.readLine();
        out.close();
//        httpCon.getInputStream();
    } catch (Exception e) {
        System.out.println(e);
    }
    finally{
            return line;
        }
    }

    private static String makeJSONRegistry(){

        JSONObject json = new JSONObject();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите имя");
            String name= bf.readLine();

            System.out.println("Введите пароль");
            String password = bf.readLine();
            json.put("login", name);
            json.put("password", password);
        }catch (Exception e){

        }
        return json.toString();
    }


}
