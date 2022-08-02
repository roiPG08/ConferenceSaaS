package com.mycompany.konferencjaservice;

import java.sql.SQLException;
import java.time.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roiPG
 */
public class Conference {

    
    private ArrayList<Preelection> preelections;
    private static final int LECTURE_TIME = 105;
    private static final int BREAK_TIME = 15;
    private static final LocalDateTime START_DATE = LocalDateTime.of(2022, 6, 1, 10, 00);
    private static final LocalDateTime END_DATE = LocalDateTime.of(2022, 6, 1, 15, 45);
    private static final LocalDateTime SECOND_PREELECTION_TIME = START_DATE.plusMinutes(LECTURE_TIME + BREAK_TIME);
    private static final LocalDateTime THIRD_PREELECTION_TIME = START_DATE.plusMinutes(2 * (LECTURE_TIME + BREAK_TIME));
    private final String EVENT_NAME;
    private static Duration duration = Duration.between(START_DATE, END_DATE);

    public Conference(String name, ArrayList<Preelection> preelections) {
        this.EVENT_NAME = name;
        if(preelections.size() != 0){
            this.preelections = new ArrayList<>(preelections);
        }
        
        
        // To be deleted after testing, as it deletes after each creating of Conference, 
        //but it still might have some sense to leave it here
        try {
            DbOperators.dropAll();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Conference.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public Preelection getPreelection(int n) {
        return preelections.get(n);
    }
    
    public ArrayList<Preelection> getPreelectionList() {
        return preelections;
    }

    public static Duration getDuration() {
        return duration;
    }

    public String getConferencePlan() {
        StringBuilder plan = new StringBuilder("***Conference Plan - " + getName() + "*** \n"
                    + " This conference is build up of following preelections: \n");
        for(int i = 0; i < preelections.size(); i++){
            int index = 1;
            plan.append(String.format("%d. %s at %s. \n", index, preelections.get(i).getNameOfPreelection(), preelections.get(i).getPreelectionStart()));
            index++;
        }
        return plan.toString();
    }
}
