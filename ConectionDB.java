package Programacion.PROGRAMACION;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionDB {
//	
	 public static Connection getConnection() throws SQLException {
	        String url = "jdbc:mysql://localhost:3306/TUBASEDEDATOS";
	        String usuario = "root";
	        String contraseña = "1234";
	        
	        Connection conexión = DriverManager.getConnection(url, usuario, contraseña);
	        return conexión;
	    }
}
