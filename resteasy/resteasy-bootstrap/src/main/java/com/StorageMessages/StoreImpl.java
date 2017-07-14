package com.StorageMessages;

import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.*;

//import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser;
import javax.ws.rs.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;
import java.lang.System;
import java.util.Random;
/**
 * Created by pavel on 13.07.17.
 */

@Path("/store")
public class StoreImpl implements Store {
    List<MessageImpl> ListMes = new ArrayList<>();

    @Override
    @POST
    @Path("/add")

    public void AddMessage(String mes) {
        //добавляем сообщение в БД или ещё файл, но т.к. БД нет, то нет смысла
        //здесь писать пока что ничего, т.к. данные пусть хранятся в List в AppMessages

        try {
            Random random = new Random();
            String filename = String.valueOf(random.nextInt())+".txt";
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

        JSONObject obj = new JSONObject(mes);

        String caption = obj.getString("caption");
        String messagestr = obj.getString("message");
        JSONArray users = obj.getJSONArray("users");
        bw.write(caption);
        bw.newLine();
            bw.flush();
        bw.write(messagestr);
            bw.newLine();
            bw.flush();
        List<SystemImpl> user = new ArrayList<>();
        for (int i=0;i<users.length();i++) {

            user.add(new SystemImpl(users.getString(i)));//users.getString(i));
            bw.write(users.getString(i));
            bw.newLine();
            bw.flush();
        }
        SystemImpl owner = new SystemImpl(obj.getString("owner"));
        bw.write(obj.getString("owner"));
            bw.newLine();
            bw.flush();
        OptionsImpl option = new OptionsImpl(obj.getBoolean("delivery"), obj.getBoolean("reading"));
        MessageImpl message = new MessageImpl(caption,messagestr,user,owner,option);

        int n = ListMes.size();
        message.setId(n);
        ListMes.add(message);
    bw.write(String.valueOf(n));
            bw.newLine();
            bw.flush();
    bw.close();


    }catch (Exception e){
        System.out.println(e);
    }finally {
        System.out.println("УРА УРА АРУ");
    }
    }

    @Override
    @GET
    @Path("/getmeslogin/{login}")

    public List<MessageImpl> GetMessageForSystem(@PathParam("login") String login) {
        // на этапе когда данные хрантся не в БД, а в List, необходимо передавать этот лист в этот
        //метод, что бы получить данные, при использовании БД lm не нужен
        List<MessageImpl> message = new ArrayList<>();
        for(int i=0;i<ListMes.size();i++){
            MessageImpl m=ListMes.get(i);
            List<SystemImpl> f = m.getAddressees();
            for(int j=0;j<f.size();j++){
                if(f.get(j).getLogin().equals(login)){
                    message.add(m);
                }
                break;
            }
        }
        return message;
    }

    @Override
    @GET
    @Path("/getmes")

    public List<MessageImpl> GetMessage() {
        List<MessageImpl> lm = new ArrayList<>();
        for(int i=0;i<ListMes.size();i++){
            if(ListMes.get(i).getConfirm().isDeliveryConfirm()==false){
                lm.add(ListMes.get(i));
            }

        }
        return lm;
    }

    @Override
    @PUT
    @Path("/condel/{n}")
    public void ConfirmDelivery(@PathParam("n") int n) {
        /**/
        MessageImpl mes = ListMes.get(n);
        mes.getConfirm().setDeliveryConfirm(true);
        ListMes.set(n,mes);
    }

    @Override
    @PUT
    @Path("/conread/{n}")
    public void ConfirmReading(@PathParam("n") int n) {
        MessageImpl mes = ListMes.get(n);
        mes.getConfirm().setReadingConfirm(true);
        ListMes.set(n,mes);
        /**/
    }
}
