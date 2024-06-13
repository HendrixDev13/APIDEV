package Programacion.PROGRAMACION.manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.io.FilePermission;
import javax.sound.midi.Soundbank;

import org.glassfish.jersey.media.multipart.FormDataParam;

import Programacion.PROGRAMACION.ConectionDB;
import Programacion.PROGRAMACION.Model.Libro;
import Programacion.PROGRAMACION.Model.ListaLibro;
import Programacion.PROGRAMACION.Model.jsonResult;

public class LibroManager {

	public jsonResult insLibro(Libro nuevoLibro) throws Exception{
		jsonResult salida = new jsonResult();
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion = ConectionDB.getConnection();
		// Paso 2: Construir la consulta SQL
        String sql = "INSERT INTO registro_libro (codigo_libro, Titulo, Editorial, Autor, Año, Num_pag, Categoria, Precio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Paso 3: Crear una sentencia preparada
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
        preparedStatement.setInt(1, nuevoLibro.Codigo_libro);
        preparedStatement.setString(2, nuevoLibro.Titulo);
        preparedStatement.setString(3, nuevoLibro.Editorial);
        preparedStatement.setString(4, nuevoLibro.Autor);
        preparedStatement.setInt(5, nuevoLibro.año);
        preparedStatement.setInt(6, nuevoLibro.Num_pag);
        preparedStatement.setString(7, nuevoLibro.Categoria);
        preparedStatement.setInt(8, nuevoLibro.Precio);
 
        int filasAfectadas = preparedStatement.executeUpdate();
        
        
        if (filasAfectadas > 0) {
            salida.id = 1;
            salida.msj = "ok";
            salida.result = "LIBRO GUARDADO";
        } else {
            salida.id = -1;
            salida.msj = "Error";
            salida.result = "Error al guardar";
        }
        
        // Paso 6: Cerrar la conexión y liberar recursos
        preparedStatement.close();
        conexion.close();
		return salida;
	}
	
	/*Metodo 1 para registrar una lista de libros*/
	public jsonResult insListaLibro(ListaLibro nuevoListaLibro) throws Exception{
		jsonResult salida = new jsonResult();
		
		for (Libro nuevoLibro : nuevoListaLibro.LISTALIBRO) {
			salida = insLibro(nuevoLibro);
		}
		return salida;
	}
	
	public jsonResult getLibrosRegistrados() throws Exception{
		jsonResult resultado = new jsonResult();
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion = ConectionDB.getConnection();
		// Paso 2: Construir la consulta SQL
	    String sql = "select * from registro_libro";
	    
	    // Paso 3: Crear una sentencia preparada
	    PreparedStatement preparedStatement = conexion.prepareStatement(sql);
	    
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
		resultado.id = 1;
		resultado.msj = "ok";
		resultado.result = "Registros ";
	    resultado.data = salida;
	    
		return resultado;
	}
	
	public jsonResult getLibroNombre(String nombre_libro) throws Exception{
		jsonResult resultado = new jsonResult();
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion = ConectionDB.getConnection();
		// Paso 2: Construir la consulta SQL
        String sql = "select * from registro_libro where Titulo like ?";
        
        // Paso 3: Crear una sentencia preparada
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
        preparedStatement.setString(1, "%" + nombre_libro + "%");
        
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
		resultado.id = 1;
		resultado.msj = "ok";
		resultado.result = "Registros consultados correctamente";
        resultado.data = salida;
        
		return resultado;
	}
	
	public jsonResult getLibroAutor(String nombre_autor) throws Exception{
		jsonResult resultado = new jsonResult();
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion = ConectionDB.getConnection();
		// Paso 2: Construir la consulta SQL
        String sql = "select * from registro_libro where Autor like ?";
        // Paso 3: Crear una sentencia preparada
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
        preparedStatement.setString(1, "%" + nombre_autor +"%");
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
		resultado.id = 1;
		resultado.msj = "ok";
		resultado.result = "Registros consultados correctamente";
        resultado.data = salida;
		return resultado;
	}
	
	public jsonResult getRutadearchivos() throws Exception{
		jsonResult resultado = new jsonResult();
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion = ConectionDB.getConnection();
		// Paso 2: Construir la consulta SQL
	    String sql = "select * from libroarchivo";
	    
	    // Paso 3: Crear una sentencia preparada
	    PreparedStatement preparedStatement = conexion.prepareStatement(sql);
	    
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
		resultado.id = 1;
		resultado.msj = "ok";
		resultado.result = "Registros";
	    resultado.data = salida;
	    
		return resultado;
	}
	
	public jsonResult guardaArchivo(InputStream libroPDF) throws Exception{
		jsonResult salida = new jsonResult();
		String filePath = "C:/prueba/";
		Random random = new Random();
		int numeroAleatorio = random.nextInt(100) + 1;
		String nombreArchivo = "LibroDigital"+numeroAleatorio+".pdf";
		File file = new File(filePath + nombreArchivo);
		try (FileOutputStream outputStream = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = libroPDF.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            String rutaArchivo = filePath + nombreArchivo;
            salida = insArchivoLibro(rutaArchivo);
        }
		return salida;
	}
	
	
	
	public jsonResult insArchivoLibro(String rutaArchivo) throws Exception{
		jsonResult salida = new jsonResult();
		
		try {
	        Class.forName("com.mysql.jdbc.Driver");
	        Connection conexion = ConectionDB.getConnection();
	        String sql = "INSERT INTO registro_rutas (rutadelibro) VALUES (?)";
	        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
	            preparedStatement.setString(1, rutaArchivo);
	            int filasAfectadas = preparedStatement.executeUpdate();
	            if (filasAfectadas > 0) {
	                salida.id = 1;
	                salida.msj = "ok";
	                salida.result = "Registro Guardado";
	            } else {
	                salida.id = -1;
	                salida.msj = "Error";
	                salida.result = "Error al guardar el registro";
	            }
	        }
	        conexion.close();
	    } catch (Exception e) {
	        // Manejo de excepciones
	        salida.id = -1;
	        salida.msj = e.getMessage();
	        salida.result = "Error al guardar el registro en la base de datos";
	        e.printStackTrace(); // Registrar la excepción en la consola para su análisis
	    }
	    return salida;
	}
	public jsonResult Guardar (InputStream libroPDF,@FormDataParam("NombreArchivo") String NombreArchivo)  throws Exception {
		jsonResult salida = new jsonResult();
		String filePath = "C:/prueba/";
		Random random = new Random();
		int numeroAleatorio = random.nextInt(100) + 1;
		String nombreArchivo = "PRUEBA"+numeroAleatorio+".pdf";
		File file2 = new File(filePath + nombreArchivo);
		if (NombreArchivo == null || NombreArchivo.isEmpty()) {
            salida.id=( -1);
            salida.msj=("NombreArchivo no puede ser nulo o vacío");
            salida.result=( "Error");
            return salida;
        }

        String fechadehoy = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String carpetaFecha = filePath + fechadehoy;
        File carpeta = new File(carpetaFecha);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        String nombreArchivo2 = NombreArchivo;
        String nombreArchivoSinExtension = nombreArchivo.substring(0, nombreArchivo.lastIndexOf('.'));
        String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf('.'));
        File file = new File(carpetaFecha + "/" + nombreArchivo);
        int contador = 1;
        
        while (file.exists()) {
            nombreArchivo = nombreArchivoSinExtension + "_" + contador + extension;
            file = new File(carpetaFecha + "/" + nombreArchivo);
            contador++;
        }

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = libroPDF.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            salida.id=( -1);
            salida.msj=( e.getMessage());
            salida.result=( "Error");
            return salida;
        }

        String rutaArchivo = carpetaFecha + "/" + nombreArchivo;
        salida = insArchivoLibro(rutaArchivo);

        return salida;
    }
	//nuevo
	public jsonResult getcategorias() throws Exception{
		jsonResult resultado = new jsonResult();
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion = ConectionDB.getConnection();
	    String sql = "SELECT DISTINCT Categoria FROM registro_libro";
	    
	    PreparedStatement preparedStatement = conexion.prepareStatement(sql);
	    
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
		resultado.id = 1;
		resultado.msj = "ok";
		resultado.result = "Registros ";
	    resultado.data = salida;
	    
		return resultado;
	}
	 //nuevo
	public jsonResult buscaCategoria(String categoria) throws Exception{
		jsonResult resultado = new jsonResult();
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion = ConectionDB.getConnection();
		// Paso 2: Construir la consulta SQL
        String sql = "select * from registro_libro where categoria like ?";
        // Paso 3: Crear una sentencia preparada
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
        preparedStatement.setString(1, "%" + categoria +"%");
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
		resultado.id = 1;
		resultado.msj = "ok";
		resultado.result = "Registros consultados correctamente";
        resultado.data = salida;
		return resultado;
	}
	
	//buscar por codigo de el libro
	public jsonResult buscarporcodigo(String codigo) throws Exception{
		jsonResult resultado = new jsonResult();
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion = ConectionDB.getConnection();
		// Paso 2: Construir la consulta SQL
        String sql = "select * from registro_libro where Codigo_libro like ?";
        // Paso 3: Crear una sentencia preparada
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
        preparedStatement.setString(1, "%" + codigo +"%");
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
		resultado.id = 1;
		resultado.msj = "ok";
		resultado.result = "Registros consultados correctamente";
        resultado.data = salida;
		return resultado;
	}
	
}