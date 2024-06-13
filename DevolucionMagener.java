package Programacion.PROGRAMACION.manager;
import java.sql.Connection;
import java.math.BigDecimal;
import java.math.BigInteger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import Programacion.PROGRAMACION.ConectionDB;
import Programacion.PROGRAMACION.Model.Devoluciones;

import Programacion.PROGRAMACION.Model.jsonResult;
public class DevolucionMagener {
	
	public jsonResult insDevoluciones(Devoluciones devoluciones) throws Exception {
	    jsonResult salida = new jsonResult();
	    Class.forName("com.mysql.cj.jdbc.Driver"); // Actualiza el nombre del driver si es necesario
	    Connection conexion = ConectionDB.getConnection();
	    
	    String sql = "INSERT INTO registro_devoluciones (Dpi, Devolvio_cantidad, Estado, Codigo_libro, Fecha_devolucion1 ) VALUES (?, ?, ?, ?,?)";
	    PreparedStatement preparedStatement = conexion.prepareStatement(sql);
	    preparedStatement.setBigDecimal(1, new java.math.BigDecimal(devoluciones.Dpi));
	    preparedStatement.setInt(2, devoluciones.Devolvio_cantidad);
	    preparedStatement.setInt(3, devoluciones.Estado);
	    preparedStatement.setInt(4, devoluciones.Codigo_libro);
	    preparedStatement.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis())); // Establecer la fecha y hora actual
	    
	     
	    
	    try {
	        int filasAfectadas = preparedStatement.executeUpdate();
	        
	        if (filasAfectadas > 0) {
	            salida.id = 1;
	            salida.msj = "Devolución insertada correctamente";
	            salida.result = "ok";
	        } else {
	            salida.id = -1;
	            salida.msj = "Error, no se pudo insertar la devolución";
	        }
	    } catch (SQLException e) {
	        if (e.getSQLState().equals("23000")) { // Código SQL de error de restricción de clave foránea
	            salida.id = -1;
	            salida.msj = "Error, no se pudo insertar la devolución. El Dpi o el Codigo_libro no existen en registro_prestamo.";
	        } else {
	            throw e;
	        }
	    }
	    
	    preparedStatement.close();
	    conexion.close();
	    return salida;
	}

	

    public jsonResult buscarPorDpi(String Devoluciones) throws Exception{
		jsonResult resultado = new jsonResult();
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion = ConectionDB.getConnection();
		// Paso 2: Construir la consulta SQL
        String sql = "select * from registro_devoluciones where Dpi = ?";
        
        // Paso 3: Crear una sentencia preparada
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
        preparedStatement.setString(1, Devoluciones);
        
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
