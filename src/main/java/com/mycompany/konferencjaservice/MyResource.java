package com.mycompany.konferencjaservice;

import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Root resource (exposed at "myresource" path)
 */

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
    private ArrayList<Preelection> electionList = new ArrayList<>();
    private final Conference konferencja = new Conference("Konferencja Sii", electionList);
    
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return text;
    }
    
    @POST
    public void setString(String s) {
        text = s;
    }
    
//    @GET
//    @Path("/{conference}/plan")
//    @Produces(MediaType.TEXT_PLAIN)
//    public String getConferencePlan(@PathParam("conference") Conference conference) {
//        return konferencja.getConferencePlan();
//    }
   
    /*
    http://localhost:8080/webapi/myresource/body
    Lemon is a fruit
    */
    @POST
    @Path("body")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String body(String param) {
        return param;
    }
    
    /*
    http://localhost:8080/webapi/myresource/path/French/Adventure/The%20Count%20of%20Monte%20Cristo
    */
    @GET
    @Path("path/{language}/{genre}/{title}")
    @Produces(MediaType.TEXT_PLAIN)
    public String path(@PathParam("language") String language,
            @PathParam("genre") String genre,
            @PathParam("title") String title){
        return language + " - " + genre + " - " + title;
    }
    
    /*
    http://localhost:8080/webapi/myresource/path/French/Adventure
    */
    @GET
    @Path("path/{language}/{genre}")
    @Produces(MediaType.TEXT_PLAIN)
    public String path2(@PathParam("language") String language,
            @PathParam("genre") String genre){
        return language + " - " + genre + " - Any";
    }
    
    /*
    http://localhost:8080/webapi/myresource/query?language=French&genre=Adventure&title=The%20Count%20of%20Monte%20Cristo
    params can be omitted
    */
    @GET
    @Path("query")
    @Produces(MediaType.TEXT_HTML)
    public String query(@QueryParam("language") @DefaultValue("Any") String language,
            @QueryParam("genre") @DefaultValue("Any")String genre,
            @QueryParam("title") @DefaultValue("Any") String title){
        return language + " - " + genre + " - " + title;
    }
    
    /*
    http://localhost:8080/webapi/myresource/form
    Content-Type application/x-www-form-urlencoded
    language=French&genre=Adventure&title=The%20Count%20of%20Monte%20Cristo
    params can be omitted
    */
    @POST
    @Path("form")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String form(@FormParam("language") @DefaultValue("Any") String language,
            @FormParam("genre") @DefaultValue("Any")String genre,
            @FormParam("title") @DefaultValue("Any") String title) {
        return language + " - " + genre + " - " + title;
    }
    
    /*
    http://localhost:8080/webapi/myresource/header
    Fruit-Name lemon
    Fruit-Colour yellow
    */
    @GET
    @Path("header")
    @Produces(MediaType.TEXT_PLAIN)
    public String header(@HeaderParam("Fruit-Name") String name,
            @HeaderParam("Fruit-Colour") String colour) {
        return name + " - " + colour;
    }
}
