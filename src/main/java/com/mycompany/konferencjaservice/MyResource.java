package com.mycompany.konferencjaservice;

import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Singleton
@Path("myresource")
public class MyResource {
    
    private String text = "Siema";
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return text;
    }
    
    @POST
    public void setString(String s) {
        text = s;
    }
    public void createUser(int login, String email){
        /*
        Try to find the same login in database
        
        if(login exists) {
            text = "User cannot be created. Inputed login already in use."
        }
        */
        User newUser = new User(login, email);
    }
    public void makeReservation(int login, String email, int preelectionNumber){
        User toReserve = new User(login, email);
        
        toReserve.reservePreelection(c);
    }
}
