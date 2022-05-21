/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.konferencjaservice;

import java.util.ArrayList;

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
    private ArrayList<Preelection> booked;

    public User(int login, String mail) {
        this.login = login;
        this.mail = mail;
        this.takingPart1 = false;
        this.takingPart2 = false;
        this.takingPart3 = false;
    }

    // 1 slot = 10 - 11.45
    // 2 slot = 12 - 13.45
    // 3 slot = 14 - 15,.45
    public void reservePreelection(Conference c) {
        //Preelection selected = new Preelection(c);s
        switch (c.getFirst().getPreelectionNumber()) {
            case (1):
                if (takingPart1 == true && c.getFirst().getCurrentCapacity() < Preelection.getMAX_CAPACITY()) {
                    System.out.println("You cannot reserve it twice.");
                    break;
                }
                takingPart1 = true;
                c.getFirst().increaseCurrentCapacity();
                booked.add(c.getFirst());
                break;
            case (2):
                if (takingPart2 == true && c.getSecond().getCurrentCapacity() < Preelection.getMAX_CAPACITY()) {
                    System.out.println("You cannot reserve it twice.");

                    break;
                }
                takingPart2 = true;
                c.getSecond().increaseCurrentCapacity();
                booked.add(c.getSecond());
                break;
            case (3):
                if (takingPart3 == true && c.getThird().getCurrentCapacity() < Preelection.getMAX_CAPACITY()) {
                    System.out.println("You cannot reserve it twice.");
                    break;
                }
                takingPart3 = true;
                c.getThird().increaseCurrentCapacity();
                booked.add(c.getThird());
                break;
            default:
                System.out.println("Something went wrong");
        }
        /*
        Should send this data to sql database.
         */
    }

    public void cancelPreelection(Preelection p) {

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
    public ArrayList<Preelection> getBooked() {
        return booked;
    }

    /**
     * @param booked the booked to set
     */
    public void setBooked(ArrayList<Preelection> booked) {
        this.booked = booked;
    }
}
