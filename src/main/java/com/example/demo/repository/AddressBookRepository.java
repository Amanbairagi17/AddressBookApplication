package com.example.demo.repository;

import com.example.demo.model.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class AddressBookRepository {
    Scanner sc = new Scanner(System.in);
    public void addContact() {
        try {
            Connection connection = DBConnection.getConnection();

            System.out.println("Enter First Name:");
            String firstName = sc.nextLine();

            System.out.println("Enter Last Name:");
            String lastName = sc.nextLine();

            System.out.println("Enter Address:");
            String address = sc.nextLine();

            System.out.println("Enter City:");
            String city = sc.nextLine();

            System.out.println("Enter State:");
            String state = sc.nextLine();

            System.out.println("Enter Zip:");
            String zip = sc.nextLine();

            System.out.println("Enter Phone Number:");
            String phonenumber = sc.nextLine();

            System.out.println("Enter Email:");
            String email = sc.nextLine();

            String query = "INSERT INTO contacts(firstname,lastname,address,city,state,zip,phonenumber,email,date_added) VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, address);
            statement.setString(4, city);
            statement.setString(5, state);
            statement.setString(6, zip);
            statement.setString(7, phonenumber);
            statement.setString(8, email);

            statement.setDate(9, new java.sql.Date(System.currentTimeMillis()));

            statement.executeUpdate();
            System.out.println("Contact added to database.");
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int retrieveContacts() {
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT * FROM contacts";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int totalContacts = rs.getFetchSize();
            
            while (rs.next()) {
                System.out.println(
                        rs.getString("firstName") + " " +
                        rs.getString("lastName") + ", " +
                        rs.getString("address") + ", " +
                        rs.getString("city") + ", " +
                        rs.getString("state") + ", " +
                        rs.getString("zip") + ", " +
                        rs.getString("phonenumber") + ", " +
                        rs.getString("email")
                );
            }
            connection.close();
            return totalContacts;
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public Contact updateContact() {
        try {
            Connection connection = DBConnection.getConnection();

            System.out.println("Enter First Name:");
            String firstName = sc.nextLine();

            System.out.println("Enter Last Name:");
            String lastName = sc.nextLine();

            // find id basesd on the first and last name because we have to find id and id is a primary key
            String findIdQuery = "SELECT id FROM contacts WHERE firstname=? AND lastname=?";
            PreparedStatement findStmt = connection.prepareStatement(findIdQuery);
            findStmt.setString(1, firstName);
            findStmt.setString(2, lastName);

            ResultSet rs = findStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Contact not found.");
                return null;
            }

            int id = rs.getInt("id");

            //  take updated values
            System.out.println("Enter new Address:");
            String address = sc.nextLine();

            System.out.println("Enter new City:");
            String city = sc.nextLine();

            System.out.println("Enter new State:");
            String state = sc.nextLine();

            System.out.println("Enter new Zip:");
            String zip = sc.nextLine();

            System.out.println("Enter new Phone Number:");
            String phoneNumber = sc.nextLine();

            System.out.println("Enter new Email:");
            String email = sc.nextLine();

            //  Update using ID
            String updateQuery = "UPDATE contacts SET address=?, city=?, state=?, zip=?, phonenumber=?, email=? WHERE id=?";
            PreparedStatement updateStmt = connection.prepareStatement(updateQuery);

            updateStmt.setString(1, address);
            updateStmt.setString(2, city);
            updateStmt.setString(3, state);
            updateStmt.setString(4, zip);
            updateStmt.setString(5, phoneNumber);
            updateStmt.setString(6, email);
            updateStmt.setInt(7, id);

            int rows = updateStmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Contact updated successfully.");

                Contact contact = new Contact(
                        firstName,
                        lastName,
                        address,
                        city,
                        state,
                        zip,
                        phoneNumber,
                        email
                );

                connection.close();
                return contact;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Contact getContact(String firstName, String lastName) {

        try {
            Connection connection = DBConnection.getConnection();

            String query = "SELECT * FROM contacts WHERE firstname=? AND lastname=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, firstName);
            statement.setString(2, lastName);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                Contact contact = new Contact(
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("zip"),
                        rs.getString("phonenumber"),
                        rs.getString("email")
                );

                connection.close();
                return contact;
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    
    public void getContactsByDateRange(String startDate, String endDate) {

        try {
            Connection connection = DBConnection.getConnection();

            String query = "SELECT * FROM contacts WHERE date_added BETWEEN ? AND ?";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setDate(1, java.sql.Date.valueOf(startDate));
            statement.setDate(2, java.sql.Date.valueOf(endDate));

            ResultSet rs = statement.executeQuery();

            while(rs.next()){

                System.out.println(
                        rs.getString("firstname") + " " +
                        rs.getString("lastname") + ", " +
                        rs.getString("city") + ", " +
                        rs.getDate("date_added")
                );
            }

            connection.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public int getContactCountByCity(String city) {

        int count = 0;

        try {
            Connection connection = DBConnection.getConnection();

            String query = "SELECT getContactsByCity(?)";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, city);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                count = rs.getInt(1);
            }

            connection.close();

        } catch(Exception e){
            e.printStackTrace();
        }

        return count;
    }
    
    public int getContactCountByState(String state) {

        int count = 0;

        try {
            Connection connection = DBConnection.getConnection();

            String query = "SELECT getContactsByState(?)";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, state);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                count = rs.getInt(1);
            }

            connection.close();

        } catch(Exception e){
            e.printStackTrace();
        }

        return count;
    }
}