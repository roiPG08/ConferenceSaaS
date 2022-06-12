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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author roiPG
 */
public class User {

    private final int login;
    private String mail;
    private ArrayList<Boolean> isTakingPart;
    private boolean takingPart1; // taking part in 1st preelection
    private boolean takingPart2;
    private boolean takingPart3;
    //private ArrayList<Preelection> booked;
    private HashMap<Integer, Preelection> booked; // String represents
    // time slots written in english: First, Second, Third 

    public User(int login, String mail) throws SQLException, ClassNotFoundException {
        this.login = login;
        this.mail = mail;
        this.isTakingPart = new ArrayList<>();
        Collections.fill(isTakingPart, false);
        this.takingPart1 = false;
        this.takingPart2 = false;
        this.takingPart3 = false;
        this.booked = new HashMap<>();
        DbOperators.addUser(login, mail);
    }
    
    public User(int login, String mail, int numberOfPreelections) throws SQLException, ClassNotFoundException {
        this.login = login;
        this.mail = mail;
        this.isTakingPart = new ArrayList<>(numberOfPreelections);
        isTakingPart.addAll(Collections.nCopies(numberOfPreelections, Boolean.FALSE));        
        //Collections.fill(isTakingPart, false);
        this.booked = new HashMap<>();
        DbOperators.addUser(login, mail);
    }

    public void reservePreelection(Conference c, int numberOfPreelection) throws ClassNotFoundException, SQLException {
        numberOfPreelection--;

        if(c.getPreelection(numberOfPreelection).getCurrentCapacity() < Preelection.getMAX_CAPACITY() && 
            isTakingPart.get(numberOfPreelection) == true){
            System.out.println("You cannot reserve it twice.");
            return;
        }
        isTakingPart.set(numberOfPreelection, true);
        c.getPreelection(numberOfPreelection).increaseCurrentCapacity();
        System.out.println(c.getPreelection(numberOfPreelection).getCurrentCapacity() + " increased capacity.");
        booked.put(numberOfPreelection, c.getPreelection(numberOfPreelection));
        System.out.println(getBooked() + " booked preelection by the user " + mail);

        DbOperators.insertReservation(getLogin(), c, numberOfPreelection);
    }

    public void cancelPreelection(Preelection p, int timeSlot) {
        if (booked.containsValue(p) == false) {
            System.out.println("User doesn't have following preelection reserved.");
        } else {
            switch (timeSlot) {
                case 1:
                    if (p == booked.get(1)) {
                        booked.remove("First", p);
                        System.out.println("Preeleciton " + p.getNameOfPreelection() + " has been removed at " + p.getPreelectionStart());
                    } else {
                        System.out.println("You don't have the following prelection reserved on this time slot!");
                    }
                    break;
                case 2:
                    if (p == booked.get(2)) {
                        booked.remove(2);
                        System.out.println("Preeleciton " + p.getNameOfPreelection() + " has been removed at " + p.getPreelectionStart());
                    } else {
                        System.out.println("You don't have the following prelection reserved on this time slot!");
                    }
                    break;
                case 3:
                    if (p == booked.get(3)) {
                        booked.remove(3);
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
    public HashMap<Integer, Preelection> getBooked() {
        return booked;
    }

    public void setBooked(HashMap<Integer, Preelection> newBooked) {
        this.booked = newBooked;
    }

    private boolean checkLogin(int login) throws SQLException, ClassNotFoundException {
        ArrayList<Integer> currentLoginList = new ArrayList<>(DbOperators.getAllLogins());
        
        if(currentLoginList.contains(login)){
            System.out.println("User with that name already exists.");
            return true;
        }
        return false;
    }

}
