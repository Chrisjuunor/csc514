package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

public class student {
    public student(){}
    
    public boolean create_account(String Firstname, String Lastname, String Gender, String Department, 
            String Email, String Password) {
        try (Connection conn = getDBConnection()){
            String query = "INSERT INTO student (Firstname, Lastname, Gender, Department, Email, Password) VALUES (?, ?, ?, ?, ?, ?)";
            try(PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setString(1, Firstname);
                stmt.setString(2, Lastname);
                stmt.setString(3, Gender);
                stmt.setString(4, Department);
                stmt.setString(5, Email);
                stmt.setString(6, Password);
                stmt.execute();
                return true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean login(String Email, String Password) {
        try (Connection conn = getDBConnection()){
            String query = "SELECT * FROM student WHERE Email = ? AND Password = ?";
            try(PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setString(1, Email);
                stmt.setString(2, Password);
                stmt.execute();
                return true;
            }  
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Connection getDBConnection() throws ClassNotFoundException, SQLException {
        String username = "root";
        String password = "Dhnonny23";
        String url = "jdbc:mysql://localhost:3306/csc514?useSSL=false";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }
}
