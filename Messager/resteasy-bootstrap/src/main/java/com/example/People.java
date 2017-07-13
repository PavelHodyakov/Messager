package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pavel on 11.07.17.
 */
public class People {
    //private Map<Integer,List<String>> people = new HashMap<Integer, List<String>>();

    private String SName;
    private String FName;

    public People() {

    }

    public People(String fname, String sname){
        this.SName=sname;
        this.FName=fname;
    }
    /*@GET
    @Path("/{id}")
    public String getPeople(@PathParam("id") int id){
        return people.get(id).get(0)+" "+people.get(id).get(1);
    }*/

  //  public List<String>



    public String getSName() {
        return SName;
    }

    public String getFName() {
        return FName;
    }



    public void setSName(String SName) {
        this.SName = SName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }
}
