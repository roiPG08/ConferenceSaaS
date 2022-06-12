/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.konferencjaservice;

import java.time.LocalDateTime;

/**
 * Class represents an implementation of preelections, We have 3 preelections
 * that runs on the each time slot
 *
 * @author roiPG
 */
public class Preelection {

    private final static int MAX_CAPACITY = 5;
    private int currentCapacity = 0;
    private final String nameOfPreelection;
    private final LocalDateTime preelectionStart;
    private final int preelectionNumber;

    public Preelection(String name, int number, LocalDateTime starts) {
        this.nameOfPreelection = name;
        this.preelectionStart = starts;
        this.preelectionNumber = number;
    }

    public void increaseCurrentCapacity() {
        System.out.println("User added to current capacity of " + getNameOfPreelection());
        currentCapacity++;
    }

        /**
     * @return the MAX_CAPACITY
     */
    public static int getMAX_CAPACITY() {
        return MAX_CAPACITY;
    }

    /**
     * @return the current_capacity
     */
    public int getCurrentCapacity() {
        return currentCapacity;
    }

    /**
     * @return the nameOfPreelection
     */
    public String getNameOfPreelection() {
        return nameOfPreelection;
    }

    /**
     * @return the preelectionStart
     */
    public LocalDateTime getPreelectionStart() {
        return preelectionStart;
    }

    /**
     * @return the preelectionNumber
     */
    public int getPreelectionNumber() {
        return preelectionNumber;
    }
}
