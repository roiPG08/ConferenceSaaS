package com.mycompany.konferencjaservice;

import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.time.LocalDateTime;

/**
 * Root resource (exposed at "myresource" path)
 */
@Singleton
@Path("myresource")
public class MyResource {
    
    int LECTURE_TIME = 105;
    private int BREAK_TIME = 15;
    private LocalDateTime START_DATE = LocalDateTime.of(2022, 6, 1, 10, 00);
    private LocalDateTime END_DATE = LocalDateTime.of(2022, 6, 1, 15, 45);
    private LocalDateTime SECOND_PREELECTION_TIME = START_DATE.plusMinutes(LECTURE_TIME + BREAK_TIME);
    private LocalDateTime THIRD_PREELECTION_TIME = START_DATE.plusMinutes(2 * (LECTURE_TIME + BREAK_TIME));
    private String text = "Siema";
    private Preelection preelection1 = new Preelection("AI", 1, START_DATE);
    private Preelection preelection2 = new Preelection("Big Data", 2, SECOND_PREELECTION_TIME);
    private Preelection preelection3 = new Preelection("IT Security", 3, THIRD_PREELECTION_TIME);
    //private Conference konferencja = new Conference("Konferencja Sii", preelection1, preelection2, preelection3);
    
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
        //User newUser = new User(login, email);
    }
    public void makeReservation(int login, String email, int preelectionNumber){
        //User toReserve = new User(login, email);
        
        //toReserve.reservePreelection(konferencja, preelectionNumber);
    }
}
