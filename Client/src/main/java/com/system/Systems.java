package com.system;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import java.net.URL;
import org.json.*;
import java.lang.System;
import java.util.*;

/**
 * Created by pavel on 14.07.17.
 */
public class Systems {



    public static void main(String[] args) {
        Map<Integer,Map<Integer,List<String>>> mesinfo = new HashMap<>();
        boolean enter=false;
        boolean action=false;
        int answer=0;
        Methods method = new Methods();
        String login = null;
        String password = null;
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (!enter) {
                System.out.println("1. Вход");
                System.out.println("2. Регистрация");
                answer = sc.nextInt();
                switch (answer) {
                    case 1:
                        System.out.println("Введите логин:");
                        login = br.readLine();
                        System.out.println("Введите пароль:");
                        password = br.readLine();
                        if(!method.Enter(login,password).equals("false"))
                            enter=true;
                        break;
                    case 2: if (!method.Registry().equals("false"))
                                enter=true;
                        break;
                }
            }
            while(!action){
                System.out.println("1. Отправить письмо");
                System.out.println("2. Получить сообщения");
                //System.out.println("3. Посмотреть сообщения");
                System.out.println("0. Выход");
                answer=sc.nextInt();
                switch(answer){
                    case 0: return;
                    case 1: method.AddMessage(login); break;
                    case 2: {
                        mesinfo=method.getMessage(login);
                        //Map<Integer,List<String>> message = new HashMap<>();
                        //List<String> info = new ArrayList<>();
                        method.showMessages(mesinfo);
                    break;}
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
