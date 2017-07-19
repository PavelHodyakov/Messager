package com.messager;

import com.Tables.SystemEntity;
import org.hibernate.loader.custom.sql.SQLQueryParser;
import org.json.JSONObject;
import org.postgresql.core.Query;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.lang.*;
import java.lang.System;
import java.util.List;

/**
 * Created by pavel on 18.07.17.
 */
@Path("/enter")
public class Enter {

    public EntityManager em= Persistence.createEntityManagerFactory("Entities").createEntityManager();

    @GET
    @Path("/login")
    public String Login(@QueryParam("name") String name, @QueryParam("password") String password){
        //String r = "Select idSystem from SystemEntity where(name="+name+" AND password="+password+")";
        List<Integer> s = em.createQuery("Select idSystem from SystemEntity where(name= :name AND password= :password)").setParameter("name",name).setParameter("password",password).getResultList();

        //em.getTransaction().begin();
        if(s.size()!=0){
            return "true";
          } else return "false";
    }

    @POST
    @Path("/registry")
    public  String Registry(String reg){
        boolean f=false;
        try {
            JSONObject obj = new JSONObject(reg);
            String name = obj.getString("login");
            String password = obj.getString("password");
            em.getTransaction().begin();
             f = CheckExistSystem(name);
            if (f) {
                List<Integer> c =em.createQuery("select idSystem from SystemEntity").getResultList();
                int myid;
                if(c.size()!=0) {
                    myid = c.get(c.size() - 1) + 1;//вот так вот мы получили id
                } else{
                    myid = 0;
                }
                em.persist(new SystemEntity(name, password, myid));
                //em.flush();
                em.getTransaction().commit();
            }
        }catch(Exception e){
            java.lang.System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        //em.close();
        return String.valueOf(f);//флаг на успешность/не успешность выполнения регитсрации
    }

    public boolean CheckExistSystem(String name){
        List<Integer> l=em.createQuery("select idSystem from SystemEntity where(name=:name)").setParameter("name",name).getResultList();
            if(l.size()!=0){
                return false;
            } return true;
    }

}
