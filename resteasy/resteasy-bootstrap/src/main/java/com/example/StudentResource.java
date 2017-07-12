package com.example;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.*;

/**
 * Created by pavel on 11.07.17.
 */
@Path("/people")
public class StudentResource {
    Map<Integer,List<People>> MPeople = new HashMap<>();

    public StudentResource(){
        try {
            BufferedReader bf = new BufferedReader(new FileReader("people.txt"));
            int i=0;
            String line=bf.readLine();
            while(line!=null){
                String[] f = line.split(" ");
                List<People> list = new ArrayList<>();
                list.add(new People(f[0],f[1]));
                MPeople.put(i++,list);
                line = bf.readLine();
            }
            bf.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        } finally {

        }

    }

    @GET
    @Path("/seek/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String SfindByID(@PathParam("id") int id){
       // StudentResource sr = new StudentResource();
        try {
            return MPeople.get(id).get(0).getFName() + " " + MPeople.get(id).get(0).getSName();
        }catch (Exception e){
            return "Incorrect Id";
        }
    }


    @PUT
    @Path("/set")
    public void putPeople(@QueryParam("fname") String fname, @QueryParam("sname") String sname){
        //int s = MPeople.size();
        try {
//            System.out.print("asdfghjzxcvbnwertyuizesdxfgvhbjndxgfchgvjhk");
//            BufferedWriter bw = new BufferedWriter(new FileWriter("people.txt"));
//            bw.write(name);
//            bw.close();
//            Formatter x= new Formatter(new BufferedWriter(new FileWriter("people.txt")));
//            x.format("\n"+name);
//            x.flush();
//            x.close();
            PrintStream printStream = new PrintStream(new FileOutputStream("people.txt", true), true);
            printStream.println(sname+" "+fname);
            printStream.close();
        }catch (Exception e){
          System.out.println(e);
        }

    }
}
