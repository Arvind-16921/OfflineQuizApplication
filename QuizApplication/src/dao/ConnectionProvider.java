package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionProvider {
	public static Connection getConnection() {
		Connection cn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost/quiz3","root","kumar");
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		return cn;
	}
}
