package Email;

import DatabaseConnection.connection.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class childEmail {
    private String firstName;
    private String lastName;
    private String Password;
    private String Department;
    private int passwordLength = 10;
    private String alternateEmail;
    private String anyCom = "Jacobes.co.in";

    // Constructor to receive first and last name
    public childEmail(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        System.out.println("Hello " + firstName + " " + lastName);

        // Call a method for asking the department
        this.Department = setDepartment();
        System.out.println("Department is :- " + Department);

        // Generate and print password
        this.Password = genRandomPassword(passwordLength);
        System.out.println("Your password is:- " + Password);

        // Generate and print email
        this.alternateEmail = genEmail();
        System.out.println("Your Email is:- " + alternateEmail + "\n");

        // Insert the generated details into the database
        insertIntoDatabase(firstName, lastName, alternateEmail, Password);
    }

    //this method sets the Department
    private String setDepartment() {
        System.out.println("DEPARTMENT CODES:-\n 1 for Sales\n 2 for Development\n 3 for Accounting\n 0 for None");
        System.out.println("Enter the Department Code:- ");
        Scanner scanner = new Scanner(System.in);
        int deptChoice = scanner.nextInt();
        if (deptChoice == 1) {
            return "sales";
        } else if (deptChoice == 2) {
            return "dev";
        } else if (deptChoice == 3) {
            return "acct";
        } else {
            return "";
        }
    }

    // Method to generate a random password
    private String genRandomPassword(int length) {
        String randomPassword = "ABCDEFGHIJKLMOPQRSTUVWXYZ1234567890@_#$*^";
        char[] password = new char[length];
        for (int i = 0; i < length; i++) {
            int rand = (int) (Math.random() * randomPassword.length());
            password[i] = randomPassword.charAt(rand);
        }
        return new String(password);
    }

    // Method to generate an email
    private String genEmail() {
        return firstName.toLowerCase() + lastName.toLowerCase() + Department.toUpperCase() + "@" + anyCom;
    }

    // Method to insert the data into the database
    private void insertIntoDatabase(String firstName, String lastName, String email, String password) {
        Connection conn = MyConnection.getTheConnnection();
        String name=firstName+" "+lastName;
        String query = "INSERT INTO user (name, email, password) VALUES ( ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
            System.out.println("User details inserted into the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MyConnection.closeConnection();
        }
    }
}
