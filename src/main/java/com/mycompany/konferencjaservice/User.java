package com.mycompany.konferencjaservice;

import java.sql.SQLException;
import java.util.ArrayList;
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

    public void cancelPreelection(Preelection p) throws SQLException, ClassNotFoundException {
        int preNum = p.getPreelectionNumber() - 1;
        for(int i = 0; i < booked.size(); i++){
            System.out.println(i + ", " + booked.get(i));
        }
        System.out.println("Preelection number " + preNum);
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
     * @return the login
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
     * @return the list of booked slots of preelections
     */
    public HashMap<Integer, Preelection> getBooked() {
        return booked;
    }
    
    /**
     * Function used to set new or update booking list
     * @param newBooked is new list of bookings.
     */
    public void setBooked(HashMap<Integer, Preelection> newBooked) {
        this.booked = newBooked;
    }
    
    /**
     * @return true if login already exists in a system, 
     * which will avoid duplication of the same login numbers.
     */
    private boolean checkLogin(int login) throws SQLException, ClassNotFoundException {
        ArrayList<Integer> currentLoginList = new ArrayList<>(DbOperators.getAllLogins());

        if (currentLoginList.contains(login)) {
            System.out.println("User with that name already exists.");
            return true;
        }
        return false;
    }

}
