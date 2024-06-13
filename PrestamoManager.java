package Programacion.PROGRAMACION.manager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import Programacion.PROGRAMACION.ConectionDB;
import Programacion.PROGRAMACION.Model.Devoluciones;
import Programacion.PROGRAMACION.Model.Libros;
import Programacion.PROGRAMACION.Model.Prestamo;
import Programacion.PROGRAMACION.Model.jsonResult;


public class PrestamoManager {
	public jsonResult insPrestamo(Prestamo prestamo) throws Exception {
	    jsonResult salida = new jsonResult();
	    Class.forName("com.mysql.cj.jdbc.Driver"); // Actualiza el nombre del driver si es necesario
	    Connection conexion = ConectionDB.getConnection();
	    
	    String sql = "INSERT INTO registro_prestamo (Total_pago, Dpi, Codigo_libro, Prestamo_cantidad, Fecha_venta1 ) VALUES (?, ?, ?, ?,?)";
	    PreparedStatement preparedStatement = conexion.prepareStatement(sql);
	    preparedStatement.setInt(1, prestamo.Total_pago);
	    preparedStatement.setBigDecimal(2, new java.math.BigDecimal(prestamo.Dpi));
	    preparedStatement.setInt(3, prestamo.Codigo_libro);
	    preparedStatement.setInt(4, prestamo.Prestamo_cantidad);
	    preparedStatement.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis())); // Establecer la fecha y hora actual
	    
	     
	    
	    try {
	        int filasAfectadas = preparedStatement.executeUpdate();
	        
	        if (filasAfectadas > 0) {
	            salida.id = 1;
	            salida.msj = "prestamo insertada correctamente";
	            salida.result = "ok";
	        } else {
	            salida.id = -1;
	            salida.msj = "Error, no se pudo insertar la devolución";
	        }
	    } catch (SQLException e) {
	        if (e.getSQLState().equals("23000")) { // Código SQL de error de restricción de clave foránea
	            salida.id = -1;
	            salida.msj = "Error, no se pudo insertar el prestamo. El Dpi o el Codigo_libro no existen en registro_prestamo.";
	        } else {
	            throw e;
	        }
	    }
	    
	    preparedStatement.close();
	    conexion.close();
	    return salida;
	}

	

    public jsonResult buscarPrestamo(String Prestamo) throws Exception{
		jsonResult resultado = new jsonResult();
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion = ConectionDB.getConnection();
		// Paso 2: Construir la consulta SQL
        String sql = "select * from registro_prestamo where Dpi = ?";
        
        // Paso 3: Crear una sentencia preparada
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
        preparedStatement.setString(1, Prestamo);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        List<Map<String, Object>> salida = new ArrayList<Map<String, Object>>();
		ResultSetMetaData meta = resultSet.getMetaData();
		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				String key = meta.getColumnName(i).toString();
				String value = Objects.toString(resultSet.getString(key), "");
				map.put(key, value);
			}
			salida.add(map);
		}
        resultado.data = salida;
        
		return resultado;
	}
}
