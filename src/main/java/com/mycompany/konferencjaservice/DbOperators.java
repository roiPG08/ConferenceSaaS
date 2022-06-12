package com.mycompany.konferencjaservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Timestamp;
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
    
    public static void insertReservation(int login, Conference c, int num) throws SQLException, ClassNotFoundException {
        Class.forName(DB_CLASS_NAME);
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "insert into bookings (preelectionID, preelectionName, time_slot, login)" + " values (?, ?, ?, ?)";
            Timestamp timestamp = Timestamp.valueOf(c.getPreelection(num).getPreelectionStart());
            //System.out.println(timestamp + " date made with timestamp");
            
            // Creating the mysql insert preparedstatement
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, num);
            ps.setString(2, c.getPreelection(num).getNameOfPreelection());
            ps.setTimestamp(3, timestamp);
            ps.setInt(4, login); 
                        
            ps.execute();
            
            con.close();
        }
    }
    
    public static void cancelReservation(int login, Preelection p) throws SQLException, ClassNotFoundException {
        Class.forName(DB_CLASS_NAME);
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "delete from bookings where login = ? and preelectionID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, login);
            ps.setInt(2, p.getPreelectionNumber());
            
            int rowsAffected = ps.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            
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
            String query1 = "delete from users";
            String query2 = "delete from bookings";
            // Creating the mysql insert preparedstatement
            PreparedStatement ps1 = con.prepareStatement(query2);
            ps1.execute();
            
            PreparedStatement ps2 = con.prepareStatement(query1);
            ps2.execute();
            con.close();
        }
    }
}
