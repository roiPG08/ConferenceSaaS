/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.konferencjaservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author roiPG
 */
public class User {

    private final int login;
    private String mail;
    private boolean takingPart1; // taking part in 1st preelection
    private boolean takingPart2;
    private boolean takingPart3;
    //private ArrayList<Preelection> booked;
    private HashMap<String, Preelection> booked; // String represents
    // time slots written in english: First, Second, Third 

    public User(int login, String mail) {
        this.login = login;
        this.mail = mail;
        this.takingPart1 = false;
        this.takingPart2 = false;
        this.takingPart3 = false;
        this.booked = new HashMap<>();
    }

    public void reservePreelection(Conference c, int numberOfPreelection) throws ClassNotFoundException, SQLException {
        switch (numberOfPreelection) {
            case (1):
                if (takingPart1 == true && c.getFirst().getCurrentCapacity() < Preelection.getMAX_CAPACITY()) {
                    System.out.println("You cannot reserve it twice.");
                    break;
                }
                takingPart1 = true;
                c.getFirst().increaseCurrentCapacity();
                System.out.println(c.getFirst().getCurrentCapacity() + " increased capacity.");
                booked.put("First", c.getFirst());
                System.out.println(getBooked() + " booked preelection by the user " + mail);
                break;
            case (2):
                if (takingPart2 == true && c.getSecond().getCurrentCapacity() < Preelection.getMAX_CAPACITY()) {
                    System.out.println("You cannot reserve it twice.");

                    break;
                }
                takingPart2 = true;
                c.getSecond().increaseCurrentCapacity();
                booked.put("Second", c.getSecond());
                break;
            case (3):
                if (takingPart3 == true && c.getThird().getCurrentCapacity() < Preelection.getMAX_CAPACITY()) {
                    System.out.println("You cannot reserve it twice.");
                    break;
                }
                takingPart3 = true;
                c.getThird().increaseCurrentCapacity();
                booked.put("Third", c.getThird());
                break;
            default:
                System.out.println("Something went wrong");
                break;
        }

        String url = "jdbc:mysql://localhost:3306/conference";
        String user = "root";
        String password = "";
        Class.forName("com.mysql.jdbc.Driver");
        try ( Connection con = DriverManager.getConnection(url, user, password)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Users");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + "  sql 1st query.");
            }
        }
    }

    public void cancelPreelection(Preelection p, int timeSlot) {
        if (booked.containsValue(p) == false) {
            System.out.println("User doesn't have following preelection reserved.");
        } else {
            switch (timeSlot) {
                case 1:
                    if (p == booked.get("First")) {
                        booked.remove("First", p);
                        System.out.println("Preeleciton " + p.getNameOfPreelection() + " has been removed at " + p.getPreelectionStart());
                    } else {
                        System.out.println("You don't have the following prelection reserved on this time slot!");
                    }
                    break;
                case 2:
                    if (p == booked.get("Second")) {
                        booked.remove("Second");
                        System.out.println("Preeleciton " + p.getNameOfPreelection() + " has been removed at " + p.getPreelectionStart());
                    } else {
                        System.out.println("You don't have the following prelection reserved on this time slot!");
                    }
                    break;
                case 3:
                    if (p == booked.get("Third")) {
                        booked.remove("Third");
                        System.out.println("Preeleciton " + p.getNameOfPreelection() + " has been removed at " + p.getPreelectionStart());
                    } else {
                        System.out.println("You don't have the following prelection reserved on this time slot!");
                    }
                    break;
                default:
                    System.out.println("The following timeSlot is invalid.");
                    break;
            }
            
            /*
            SQL query for deleting booking record from database.
            */
        }
    }

    /**
     * @return the mail
     */
    public int getLogin() {
        return login;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the takingPart1
     */
    public boolean isTakingPart1() {
        return takingPart1;
    }

    /**
     * @param takingPart1 the takingPart1 to set
     */
    public void setTakingPart1(boolean takingPart1) {
        this.takingPart1 = takingPart1;
    }

    /**
     * @return the takingPart2
     */
    public boolean isTakingPart2() {
        return takingPart2;
    }

    /**
     * @param takingPart2 the takingPart2 to set
     */
    public void setTakingPart2(boolean takingPart2) {
        this.takingPart2 = takingPart2;
    }

    /**
     * @return the takingPart3
     */
    public boolean isTakingPart3() {
        return takingPart3;
    }

    /**
     * @param takingPart3 the takingPart3 to set
     */
    public void setTakingPart3(boolean takingPart3) {
        this.takingPart3 = takingPart3;
    }

    /**
     * @return the booked
     */
    public HashMap<String, Preelection> getBooked() {
        return booked;
    }

    public void setBooked(HashMap<String, Preelection> newBooked) {
        this.booked = newBooked;
    }
}
