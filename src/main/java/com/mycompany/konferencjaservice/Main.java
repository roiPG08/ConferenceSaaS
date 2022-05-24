/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.konferencjaservice;

import static com.mycompany.konferencjaservice.Conference.getStartDate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 *
 * @author roiPG
 */
public class Main {

    int LECTURE_TIME = 105;
    int BREAK_TIME = 15;
    LocalDateTime START_DATE = LocalDateTime.of(2022, 6, 1, 10, 00);
    LocalDateTime END_DATE = LocalDateTime.of(2022, 6, 1, 15, 45);
    LocalDateTime SECOND_PREELECTION_TIME = START_DATE.plusMinutes(LECTURE_TIME + BREAK_TIME);
    LocalDateTime THIRD_PREELECTION_TIME = START_DATE.plusMinutes(2 * (LECTURE_TIME + BREAK_TIME));
    String EVENT_NAME;
    Duration duration = Duration.between(START_DATE, END_DATE);

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //System.out.println("Hello World! The conference happens on " + getDate());
        //System.out.println("The duration of event is " + duration);
        int LECTURE_TIME = 105;
        int BREAK_TIME = 15;
        LocalDateTime START_DATE = LocalDateTime.of(2022, 6, 1, 10, 00);
        LocalDateTime END_DATE = LocalDateTime.of(2022, 6, 1, 15, 45);
        LocalDateTime SECOND_PREELECTION_TIME = START_DATE.plusMinutes(LECTURE_TIME + BREAK_TIME);
        LocalDateTime THIRD_PREELECTION_TIME = START_DATE.plusMinutes(2 * (LECTURE_TIME + BREAK_TIME));
        String EVENT_NAME;
        Duration duration = Duration.between(START_DATE, END_DATE);

        Preelection preelection1 = new Preelection("AI", 1, START_DATE);
        Preelection preelection2 = new Preelection("Big Data", 2, SECOND_PREELECTION_TIME);
        Preelection preelection3 = new Preelection("IT Security", 3, THIRD_PREELECTION_TIME);

        Conference konferencja = new Conference("Konferencja Sii", preelection1, preelection2, preelection3);

        User a = new User(1, "aaa@wp.pl");
        User b = new User(2, "bbb@gmail.com");
        User c = new User(3, "ccc@o2.pl");
        User d = new User(4, "ddd@gmail.com");
        User e = new User(5, "eee@gmail.com");
        User f = new User(6, "fff@wp.pl");

        String url = "jdbc:mysql://localhost:3306/conference";
        String user = "root";
        String password = "";
        Class.forName("com.mysql.jdbc.Driver");
        try ( Connection con = DriverManager.getConnection(url, user, password)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Users");

            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " sqllllll woooorks");
            }
        }

        a.reservePreelection(konferencja, 1);
        

    }
}
