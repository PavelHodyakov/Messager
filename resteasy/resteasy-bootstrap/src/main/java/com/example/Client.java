package com.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by pavel on 12.07.17.
 */
public class Client {
  public static void main(String[] argv) {
    // Please, do not remove this line from file template, here invocation of web service will be inserted
    System.out.println("Выберите операцию:");
    System.out.println("1.Get");
    System.out.println("2.Put");
    Scanner sc = new Scanner(System.in);
    int t=sc.nextInt();
    switch(t){
      case 1: {
        try {
          String r = "5";
          URL url = new URL("http://localhost:8080/api/people/seek/"+r);
          HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
          httpCon.setDoOutput(true);
          httpCon.setRequestMethod("GET");
          BufferedReader rd= new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
          //InputStreamReader in = new InputStreamReader(httpCon.getInputStream());
          String line;
          while((line = rd.readLine())!=null){
            System.out.println(line);
          }
          rd.close();
        }catch(Exception e){

        }
        break;
      }
      case 2:{
        try {
          String fname="Zdg";
          /*StringBuilder gf = new StringBuilder();
          gf.append("Zdg dgsf");*/
          String sname = "sdf";
          URL url = new URL("http://localhost:8080/api/people/set?sname="+sname+"&fname="+fname);
          HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
          httpCon.setDoOutput(true);
          httpCon.setRequestMethod("PUT");
          OutputStreamWriter out = new OutputStreamWriter(
                  httpCon.getOutputStream());
          out.write("Resource content");
          out.close();
          System.out.println(httpCon.getOutputStream());
          httpCon.getInputStream();
          System.out.println(httpCon);
        }catch(Exception e){

        }
        break;
      }
    }
  }
}
