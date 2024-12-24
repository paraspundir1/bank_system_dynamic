package logicforvalidation;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class CheckDataandReturn {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static boolean isAdhaarExist(Long adhaar) {
		
	
	   
	        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project_servlets", "postgres", "1234");
	             PreparedStatement preparedStatement = connection.prepareStatement("SELECT 1 FROM customers WHERE adhaar = ?")) {

	            // Set the Aadhaar number in the query
	            preparedStatement.setLong(1, adhaar);

	            // Execute the query
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                // Return true if a record is found
	                return resultSet.next();
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        // Return false if an exception occurs or no record is found
	        return false;
	}
	
	
	public static boolean isIdPassRight(String id,String password) {
		
		
		   
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project_servlets", "postgres", "1234");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customers WHERE email = ? and password=?")) {

            // Set the Aadhaar number in the query
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            // Execute the query
            
                // Return true if a record is found
            while(resultSet.next()) {
            	
            	
            	
            }
            
            } catch (Exception e) {
            e.printStackTrace();
        }

        // Return false if an exception occurs or no record is found
        return false;
}

	
	
	
	
}
