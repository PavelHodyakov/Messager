package com.messager;

import com.Tables.BasemessageEntity;
import com.Tables.HibernateSessionFactory;
import com.Tables.MessagesEntity;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import java.lang.System;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.messager.Enter;

//import javax.json.stream.JsonParser;

/**
 * Created by pavel on 13.07.17.
 */

@Path("/store")
public class StoreImpl implements Store {
    List<MessageImpl> ListMes = new ArrayList<>();
    public EntityManager em= Persistence.createEntityManagerFactory("Entities").createEntityManager();



    @Override
    @POST
    @Path("/add")
    public void AddMessage(String mes) {
        //добавляем сообщение в БД или ещё файл, но т.к. БД нет, то нет смысла
        //здесь писать пока что ничего, т.к. данные пусть хранятся в List в AppMessages

        try {
        JSONObject obj = new JSONObject(mes);
        String caption = obj.getString("caption");
        String messagestr = obj.getString("message");
        boolean read= obj.getBoolean("reading");
        boolean deliv=obj.getBoolean("delivery");
        em.getTransaction().begin();
        List<Integer> c =em.createQuery("select idMessage from MessagesEntity").getResultList();
            int myid;
        if(c.size()!=0) {
            myid = c.get(c.size() - 1) + 1;//вот так вот мы получили id
        } else{
            myid = 0;
        }
            //т.к. не работает автоинкремент, то пришлось таким образом реализовывать
            //эта система даст сбой втом случае, если удалить последний элемент
        //int id = em.createQuery("select idSystem from SystemEntity").getResultList().size();
        em.persist(new MessagesEntity(caption,messagestr,deliv,read,myid));
        //em.flush();
            //em.getTransaction().commit();
            //em.close();
            //em.getTransaction().begin();
            String owner = obj.getString("owner");
        JSONArray users = obj.getJSONArray("users");
        //List<SystemImpl> user = new ArrayList<>();
        for (int i=0;i<users.length();i++) {
            //user.add(new SystemImpl(users.getString(i)));//users.getString(i));
            if (CheckExistSystem(users.getString(i))) {
                c=em.createQuery("select idBase from BasemessageEntity").getResultList();
                int id;
                if(c.size()!=0) {
                    id = c.get(c.size() - 1) + 1;//вот так вот мы получили id
                } else{
                    id = 0;
                }
                Integer se=GetID(owner);
                Integer re=GetID(users.getString(i));
                em.persist(new BasemessageEntity(id,myid,re,se,false,false));

            }
        }
            em.flush();
            em.getTransaction().commit();
    }catch (Exception e){
        System.out.println(e);
    }finally {
        em.close();
        //System.out.println("УРА УРА АРУ");
    }
    }

    private int GetID(String name){
        List<Integer> l=em.createQuery("select idSystem from SystemEntity where(name=:name)").setParameter("name",name).getResultList();
        return l.get(0);
    }

    @Override
    @GET
    @Path("/getmesid")
    public String GetMessageById(@QueryParam("id") String id){
        JSONObject obj=new JSONObject();
        int g=Integer.parseInt(id);
        try {
            List<String> mes = em.createQuery("select content from MessagesEntity where(idMessage=:id)").setParameter("id", g).getResultList();
            obj.put("content", mes.get(0));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return obj.toString();
    }


    @Override
    @GET
    @Path("/getmeslogin")
    public String GetMessageForSystem(@QueryParam("login") String login) {
        em.getTransaction().begin();
        List<Integer> ownerid = em.createQuery("select idSystem from SystemEntity where(name=:login)").setParameter("login",login).getResultList();
        int recipientid = ownerid.get(0);
        List<Integer> idMesList=em.createQuery("select messageId from BasemessageEntity where (recipientId=:recipientid)").setParameter("recipientid",recipientid).getResultList();
        List<String> captionMes= new ArrayList<>();
        List<Integer> getterId = em.createQuery("select senderId from BasemessageEntity where (recipientId=:recipientid)").setParameter("recipientid",recipientid).getResultList();
        List<String> namesender = new ArrayList<>();
        for(int i=0;i<idMesList.size();i++) {
            int idmes = idMesList.get(i);
            int idsend = getterId.get(i);
            List<String> r =em.createQuery("select caption from MessagesEntity where(idMessage=:idmes)").setParameter("idmes",idmes).getResultList();
            captionMes.add(r.get(0));//(em.createQuery("select caption from MessagesEntity where(idMessage=:idmes)").setParameter("idmes",idmes));
            //List<MessagesEntity> t = em.find(MessagesEntity.class,idmes);
            List<String> na =em.createQuery("select name from SystemEntity where(idSystem=:idsend)").setParameter("idsend",idsend).getResultList();
            namesender.add(na.get(0));//(em.createQuery("select name from SystemEntity where(idSystem=:idsend)").setParameter("idsend",idsend).toString());
        }
        String outputstring = makeJson(idMesList,captionMes,namesender);
        return outputstring;
    }

    private String makeJson(List<Integer> idMes, List<String> captionMes, List<String> namesender){
        JSONObject json = new JSONObject();
        JSONArray idmessage = new JSONArray();
        JSONArray captionmessage = new JSONArray();
        JSONArray namesend = new JSONArray();
        int size = idMes.size();
        try {
            for (int i = 0; i < size; i++) {
                idmessage.put(idMes.get(i));
                captionmessage.put(captionMes.get(i));
                namesend.put(namesender.get(i));
            }
            json.put("idmessage", idmessage);
            json.put("caption",captionmessage);
            json.put("namesender",namesend);
        }catch(Exception e){
            System.out.println("Что-то с JSON          "+e.getMessage());
        }
        System.out.println(json.toString());
        return json.toString();
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
    }

    public boolean CheckExistSystem(String name){
        List<Integer> l=em.createQuery("select idSystem from SystemEntity where(name=:name)").setParameter("name",name).getResultList();
        if(l.size()!=0){
            return true;
        } return false;
    }
}
