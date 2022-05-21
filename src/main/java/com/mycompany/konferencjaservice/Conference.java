/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.konferencjaservice;

import java.time.*;
import java.util.ArrayList;

/**
 *
 * @author roiPG
 */
public class Conference {

    

    private static final int LECTURE_TIME = 105;
    private static final int BREAK_TIME = 15;
    private static final LocalDateTime START_DATE = LocalDateTime.of(2022, 6, 1, 10, 00);
    private static final LocalDateTime END_DATE = LocalDateTime.of(2022, 6, 1, 15, 45);
    private static final LocalDateTime SECOND_PREELECTION_TIME = START_DATE.plusMinutes(LECTURE_TIME + BREAK_TIME);
    private static final LocalDateTime THIRD_PREELECTION_TIME = START_DATE.plusMinutes(2 * (LECTURE_TIME + BREAK_TIME));
    private final String EVENT_NAME;
    private static Duration duration = Duration.between(START_DATE, END_DATE);
    private Preelection firstPreelection;
    private Preelection secondPreelection;
    private Preelection thirdPreelection;

    public Conference(String name, Preelection first, Preelection second, Preelection third) {
        this.EVENT_NAME = name;
        this.firstPreelection = first;
        this.secondPreelection = second;
        this.thirdPreelection = third;
    }

    public static LocalDateTime getStartDate() {
        return START_DATE;
    }

    public static LocalDateTime getEndDate() {
        return END_DATE;
    }

    public static LocalDateTime getSecondPreelectionTime() {
        return SECOND_PREELECTION_TIME;
    }

    public static LocalDateTime getThirdPreelectionTime() {
        return THIRD_PREELECTION_TIME;
    }

    public String getName() {
        return EVENT_NAME;
    }

    public Preelection getFirst() {
        return firstPreelection;
    }

    public Preelection getSecond() {
        return secondPreelection;
    }

    public Preelection getThird() {
        return thirdPreelection;
    }

    public static Duration getDuration() {
        return duration;
    }

    public String getConferencePlan() {
        String plan = "";
        return plan;
    }
}
