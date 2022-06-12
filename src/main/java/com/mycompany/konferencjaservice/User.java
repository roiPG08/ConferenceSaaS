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
    //private ArrayList<Preelection> booked;
    private HashMap<Integer, Preelection> booked; // String represents
    // time slots written numerically 

    public User(int login, String mail) throws SQLException, ClassNotFoundException {
        this.login = login;
        this.mail = mail;
        this.isTakingPart = new ArrayList<>();
        Collections.fill(isTakingPart, false);
        this.booked = new HashMap<>();
        DbOperators.addUser(login, mail);
    }

    public User(int login, String mail, int numberOfPreelections) throws SQLException, ClassNotFoundException {
        this.login = login;
        this.mail = mail;
        this.isTakingPart = new ArrayList<>(numberOfPreelections);
        isTakingPart.addAll(Collections.nCopies(numberOfPreelections, Boolean.FALSE));
        Collections.fill(isTakingPart, false);
        this.booked = new HashMap<>();
        DbOperators.addUser(login, mail);
    }

    public void reservePreelection(Conference c, int numberOfPreelection) throws ClassNotFoundException, SQLException {
        numberOfPreelection--;

        if (c.getPreelection(numberOfPreelection).getCurrentCapacity() < Preelection.getMAX_CAPACITY()
                && isTakingPart.get(numberOfPreelection) == true) {
            System.out.println("You cannot reserve it twice.");
            return;
        }
        isTakingPart.set(numberOfPreelection, true);
        c.getPreelection(numberOfPreelection).increaseCurrentCapacity();
        System.out.println(c.getPreelection(numberOfPreelection).getCurrentCapacity() + " increased capacity.");
        booked.put(numberOfPreelection, c.getPreelection(numberOfPreelection));
        System.out.println(getBooked() + " booked preelection by the user " + mail);

        DbOperators.insertReservation(getLogin(), c, ++numberOfPreelection);
    }

    // Have to add on which time slot the user wants to delete the preelection!!!
    public void cancelPreelection(Preelection p) throws SQLException, ClassNotFoundException {
        int preNum = p.getPreelectionNumber();

        if (booked.containsValue(p) == false) {
            System.out.println("User doesn't have following preelection reserved.");
            return;
        }
        if (p == booked.get(preNum)) {
            booked.remove(preNum, p);
            System.out.println("Preeleciton " + p.getNameOfPreelection() + " has been removed at " + p.getPreelectionStart());
            /*
            SQL query for deleting booking record from database.
             */
            DbOperators.cancelReservation(getLogin(), p);
        } else {
            System.out.println("You don't have the following prelection or the time slot which you selected is invalid!");
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

        if (currentLoginList.contains(login)) {
            System.out.println("User with that name already exists.");
            return true;
        }
        return false;
    }

}
