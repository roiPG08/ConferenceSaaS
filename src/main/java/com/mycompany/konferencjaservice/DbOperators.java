/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.konferencjaservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author roiPG
 */
public class DbOperators {

    private static final String URL = "jdbc:mysql://localhost:3306/conference";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DB_CLASS_NAME = "com.mysql.jdbc.Driver";

    public void connectToDb() throws SQLException, ClassNotFoundException {
        Class.forName(DB_CLASS_NAME);
        try ( Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Users");

            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " sqllllll woooorks");
            }
            
            con.close();
        }
    }
    
    public static void insertReservation(User a, Conference c, int num) throws SQLException, ClassNotFoundException {
        Class.forName(DB_CLASS_NAME);
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "insert into bookings (preelectionID, preelectionName, time_slot, login)" + " values (?, ?, ?, ?)";
            
            // Creating the mysql insert preparedstatement
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, num);
            switch (num) {
                case 1:
                    ps.setString(2, c.getFirst().getNameOfPreelection());
                    break;
                case 2:
                    ps.setString(2, c.getSecond().getNameOfPreelection());
                    break;
                case 3:
                    ps.setString(2, c.getThird().getNameOfPreelection());
                    break;
                default:
                    System.out.println("There is no such a preelection slot");
                    break;
            }
            
            ps.setDate(3, c.getFirst().getPreelectionStart());
            ps.setInt(4, a.getLogin());
            
            
            ps.execute();
            
            con.close();
        }
    }
    
    public static void addUser(int login, String mail) throws SQLException, ClassNotFoundException {
        Class.forName(DB_CLASS_NAME);
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "insert into users (login, email)" + " values (?, ?)";
            
            // Creating the mysql insert preparedstatement
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, login);
            ps.setString(2, mail);
            
            ps.execute();
            
            con.close();
        }
        
    }
    
    public static ArrayList<Integer> getAllLogins() throws SQLException, ClassNotFoundException{
        ArrayList<Integer> list = new ArrayList<>();
        Class.forName(DB_CLASS_NAME);
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement stmt = con.createStatement();
            String query = "select login from users";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getInt(1) + " adding to list");
                list.add(rs.getInt(1));
            }
            
            con.close();
        }
        return list;
    }
    
    public static void dropAll() throws SQLException, ClassNotFoundException{
        Class.forName(DB_CLASS_NAME);
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "delete from users";
            
            // Creating the mysql insert preparedstatement
            PreparedStatement ps = con.prepareStatement(query);
            ps.execute();
            
            con.close();
        }
    }
}
