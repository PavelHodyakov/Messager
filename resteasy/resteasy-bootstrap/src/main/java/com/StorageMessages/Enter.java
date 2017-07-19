package com.StorageMessages;

import com.Tables.HibernateSessionFactory;
import com.Tables.SystemEntity;
import org.hibernate.Session;
import org.hibernate.loader.custom.sql.SQLQueryParser;
import org.json.JSONObject;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.lang.*;

/**
 * Created by pavel on 18.07.17.
 */
@Path("/enter")
public class Enter {

    public EntityManager em= Persistence.createEntityManagerFactory("Entities").createEntityManager();

    @GET
    @Path("/login")
    public boolean Login(@QueryParam("name") String name, @QueryParam("password") String password){
        String r = "Select idSystem from SystemEntity where(name="+name+" AND password="+password+")";
        //em.getTransaction().begin();
        if(em.createQuery(r)!=null){
            return true;
          } else return false;
    }

    @POST
    @Path("/registry")
    public void Registry(String reg){
        try {
            JSONObject obj = new JSONObject(reg);
            String name = obj.getString("login");
            String password = obj.getString("password");
            em.getTransaction().begin();
            em.merge(new SystemEntity(name,password));
            em.getTransaction().commit();
        }catch(Exception e){
            java.lang.System.out.println(e.getMessage());
        }
        //em.close();
    }

}
