package Programacion.PROGRAMACION.manager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import Programacion.PROGRAMACION.ConectionDB;
import Programacion.PROGRAMACION.Model.Empleado;
import Programacion.PROGRAMACION.Model.ListaEmpleado;
import Programacion.PROGRAMACION.Model.jsonResult;

public class EmpleadoManager {

	public jsonResult insEmpleado(Empleado nuevoEmpleado) throws Exception{
		
		Random rand = new Random();
		int contra = rand.nextInt(9000) + 1000; // Genera un número entre 1000 y 9999 de 4 digitos
		
		
		jsonResult salida = new jsonResult();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conexion = ConectionDB.getConnection();
		
		// Paso 2: Construir la consulta SQL
        String sql = "INSERT INTO registro_empleado (Nombre, Apellido,Contrasenia,Fecha_nacimiento,Puesto,Salario) VALUES (?,?, ?,?,?,?)";
        
        // Paso 3: Crear una sentencia preparada
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
        preparedStatement.setString(1, nuevoEmpleado.Nombre);
        preparedStatement.setString(2, nuevoEmpleado.Apellido);
        preparedStatement.setInt(3, contra);
        preparedStatement.setDate(4, nuevoEmpleado.Fecha_nacimiento);
        preparedStatement.setString(5, nuevoEmpleado.Puesto);
        preparedStatement.setInt(6, nuevoEmpleado.Salario);
        
       
 
        int filasAfectadas = preparedStatement.executeUpdate();
        
        
         
        String sqlActualizacion = "UPDATE registro_empleado SET Usuario = CONCAT(Apellido, '_', FLOOR(RAND() * 1000)), actualizado = true WHERE actualizado IS NULL";
        PreparedStatement preparedStatement1 = conexion.prepareStatement(sqlActualizacion);
        preparedStatement1.executeUpdate(); 
        
        
        if (filasAfectadas > 0) {
            salida.id = 1;
            salida.msj = "ok";
            salida.result = "Registro Guardado";
        } else {
            salida.id = -1;
            salida.msj = "Error";
            salida.result = "Error al guardar el registro";
        }
        

        // Paso 6: Cerrar la conexión y liberar recursos
        preparedStatement1.close();
        conexion.close();
		return salida;
	}
	
	
	
	
	/*Metodo 1 para registrar una lista de Empleados*/
	public jsonResult insListaEmpleado(ListaEmpleado nuevoListaEmpleado) throws Exception{
		jsonResult salida = new jsonResult();
		
		for (Empleado nuevoEmpleado : nuevoListaEmpleado.LISTAEMPLEADO) {
			salida = insEmpleado(nuevoEmpleado);
		}
		return salida;
	}
	
	
	
	//Ver Empleados REgistrados
	public jsonResult getEmpleadosRegistrados() throws Exception{
		jsonResult resultado = new jsonResult();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conexion = ConectionDB.getConnection();
		// Paso 2: Construir la consulta SQL
	    String sql = "select * from registro_empleado";
	    
	    // Paso 3: Crear una sentencia preparada
	    PreparedStatement preparedStatement = conexion.prepareStatement(sql);
	    
	    ResultSet resultSet = preparedStatement.executeQuery();
	    
	    List<Map<String, Object>> salida = new ArrayList<Map<String, Object>>();
		ResultSetMetaData meta = resultSet.getMetaData();
		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 1; i <= (meta.getColumnCount())-1; i++) {
				String key = meta.getColumnName(i).toString();
				String value = Objects.toString(resultSet.getString(key), "");
				map.put(key, value);
			}
			salida.add(map);
		}
		resultado.id = 1;
		resultado.msj = "ok";
		resultado.result = "Registros consultados correctamente";
	    resultado.data = salida;
	    
		return resultado;
	}
	
	
	//Consultar Empleado por Nickname Usuario
	
	
	
	public jsonResult getEmpleadoUser(String user) throws Exception{
		jsonResult resultado = new jsonResult();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conexion = ConectionDB.getConnection();
		// Paso 2: Construir la consulta SQL
        String sql = "select * from registro_empleado where Usuario like ?";
        
        // Paso 3: Crear una sentencia preparada
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
        preparedStatement.setString(1, "%" + user + "%");
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        List<Map<String, Object>> salida = new ArrayList<Map<String, Object>>();
		ResultSetMetaData meta = resultSet.getMetaData();
		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 1; i <= (meta.getColumnCount())-1; i++) {//Se resta 1 para que no muestre ACTUALIZADO que se usa para validacion
				String key = (meta.getColumnName(i).toString()); 
				String value = Objects.toString(resultSet.getString(key), "");
				map.put(key, value);
			}
			salida.add(map);
		}
		resultado.id = 1;
		resultado.msj = "ok";
		resultado.result = "Registros consultados correctamente";
        resultado.data = salida;
        
		return resultado;
	}
	
	

}
