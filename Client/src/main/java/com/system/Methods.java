package com.system;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Created by pavel on 18.07.17.
 */
public class Methods {

    public void showMessages(Map<Integer,Map<Integer,List<String>>> mesinfo){
        while(true) {
            for (Map.Entry<Integer, Map<Integer, List<String>>> entry : mesinfo.entrySet()) {
                Map<Integer, List<String>> message = entry.getValue();
                for (Map.Entry<Integer, List<String>> entry1 : message.entrySet()) {
                    List<String> info = entry1.getValue();
                    System.out.println(entry.getKey() + 1 + ")" + " Sender: " + info.get(1) + " Caption: " + info.get(0));
                }
                //System.out.println(entry.getKey() + "/" + entry.getValue());
            }

            System.out.println("Выберите номер сообщения для просмотра, для выхода нажмите \"0\"");
            Scanner sc = new Scanner(System.in);
            int answer = sc.nextInt();
            if (answer <= 0 || answer > mesinfo.size()) return;
            Map<Integer, List<String>> me = mesinfo.get(answer-1);
            int idMes=0;
            for (Map.Entry<Integer, List<String>> en : me.entrySet()) {
                idMes = en.getKey();
            }
            GetMessageById(idMes);
        }
    }

    public void GetMessageById(int id){
        String line = "";
        try{
            URL url = new URL("http://localhost:8080/base/store/getmesid?id="+String.valueOf(id));
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("GET");
            BufferedReader rd= new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
            line=rd.readLine();
            //System.out.println(line);
            rd.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            System.out.println(line);
            parseJsonbyID(line);
        }
    }

    private void parseJsonbyID(String line){
        try {
            JSONObject json = new JSONObject(line);
            System.out.println("Content: \n" + json.getString("content"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }



    public Map<Integer,Map<Integer,List<String>>> getMessage(String myname){
        String line="";
        try{
            URL url = new URL("http://localhost:8080/base/store/getmeslogin?login="+myname);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("GET");
            BufferedReader rd= new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
            line=rd.readLine();
            //System.out.println(line);
            rd.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            System.out.println(line);
            return parseJson(line);
        }
    }

    private Map<Integer,Map<Integer,List<String>>> parseJson(String info){
        Map<Integer,Map<Integer,List<String>>> allinfo = new HashMap<>();
        try {
            JSONObject json = new JSONObject(info);
            JSONArray idM = json.getJSONArray("idmessage");
            JSONArray cap = json.getJSONArray("caption");
            JSONArray nameS = json.getJSONArray("namesender");
            for(int i=0;i<cap.length();i++){
                List<String> capSys =new ArrayList<>();
                Map<Integer,List<String>> mesinfo = new HashMap<>();
                capSys.add(cap.getString(i));
                capSys.add(nameS.getString(i));
                mesinfo.put(idM.getInt(i),capSys);
                allinfo.put(i,mesinfo);
            }
        }catch (Exception e){
            System.out.println("Exception:"+ e.getMessage());
        }
        return allinfo;

    }

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
        System.out.println(json.toString());
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
