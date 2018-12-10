package ui.view;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Properties;

//import domain.db.Secret;
import domain.model.Person;
import domain.model.Role;

import javax.swing.*;

public class DB {
	public static void main(String[] args) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
		String userid = JOptionPane.showInputDialog("username?");
		String email = JOptionPane.showInputDialog("email?");
		String password = JOptionPane.showInputDialog("Password?");
		String firstname = JOptionPane.showInputDialog("first name?");
		String lastname = JOptionPane.showInputDialog("Last name?");
		Role rol = Role.valueOf(JOptionPane.showInputDialog("Role?"));
		Person person = new Person(userid, email, password, firstname, lastname,rol);
		Properties properties = new Properties();
		String url = "jdbc:postgresql://databanken.ucll.be:51819/2TX37?currentSchema=r0703015";
		properties.setProperty("user", "local_r0703015");
		properties.setProperty("password", "V8;5tsraYU;zXehB");
		//Secret.setPass(properties);	// implements line 17 and 18
		properties.setProperty("ssl", "true");
		properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
		properties.setProperty("sslmode","prefer");
		
		Connection connection = DriverManager.getConnection(url,properties);
		Statement statement = connection.createStatement();
		String querry = "INSERT INTO person(userid, email, password, firstname, lastname, rol) VALUES(?,?,?,?,?,?)";
		PreparedStatement pstatement = connection.prepareStatement(querry);
		pstatement.setString(1, userid);
		pstatement.setString(2, email);
		pstatement.setString(3, password);
		pstatement.setString(4, firstname);
		pstatement.setString(5, lastname);
		pstatement.setString(6, rol.toString());
		pstatement.execute();
		ResultSet result = statement.executeQuery( "SELECT * FROM person" );


		while(result.next()){
			String id = result.getString("userid");
			String em = result.getString("email");
			String pass = result.getString("password");
			String firstn = result.getString("firstname");
			String lastn = result.getString("lastname");
			Role role = Role.valueOf(result.getString("role"));
			try {	// validation of data stored in databasePerson
				Person person1 = new Person(id, em, pass, firstn, lastn, role);
				System.out.println(person1.toString());
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		statement.close();
		connection.close();
	}
}
