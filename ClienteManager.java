package Programacion.PROGRAMACION.manager;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


import Programacion.PROGRAMACION.ConectionDB;
import Programacion.PROGRAMACION.Model.ClienteModel;
import Programacion.PROGRAMACION.Model.jsonResult;
public class ClienteManager {

    public jsonResult insCliente(ClienteModel cliente) throws Exception {
        jsonResult salida = new jsonResult();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexion = ConectionDB.getConnection();
        String sql = "INSERT INTO registro_cliente (Dpi,Nombre,Apellido,Fecha_nacimiento,Telefono,Correo,Fecha_ingreso) VALUES (?, ?, ?, ?, ?, ?, ?)";
                   
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
        ((java.sql.PreparedStatement)preparedStatement).setBigDecimal(1, new java.math.BigDecimal(cliente.Dpi));
        if (!cliente.validarDpi()) {
            salida.id = -1;
            salida.msj = "Error: El DPI es inválido";
            return salida;
        }
        preparedStatement.setString(2, cliente.Nombre);
        preparedStatement.setString(3, cliente.Apellido);
        preparedStatement.setDate(4, cliente.Fecha_nacimiento);
        preparedStatement.setInt(5, cliente.Telefono);
        if (!cliente.validarnum()) {
            salida.id = -1;
            salida.msj = "Error: El número de teléfono es inválido o no es un número real";
            return salida;
        }
        preparedStatement.setString(6, cliente.Correo);
        if (!cliente.validarCorreo()) {
            salida.id = -1;
            salida.msj = "Error: El correo electrónico es inválido";
            return salida;
        }
        preparedStatement.setDate(7, cliente.Fecha_ingreso);
        
        int filasAfectadas = preparedStatement.executeUpdate();
        
        if (filasAfectadas > 0) {
            salida.id = 1;
            salida.msj = "ok";
        } else {
            salida.id = -1;
            salida.msj = "Error";
        }
        
        preparedStatement.close();
        conexion.close();
        return salida;
    }

    public jsonResult GetCliente(BigInteger Dpi) throws Exception{
        jsonResult resultado = new jsonResult();
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexion = ConectionDB.getConnection();
        String sql = "SELECT * FROM registro_cliente WHERE Dpi = ?";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
        preparedStatement.setBigDecimal(1, new BigDecimal(String.valueOf(Dpi)));
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        List<Map<String, Object>> salida = new ArrayList<Map<String, Object>>();
        ResultSetMetaData meta = resultSet.getMetaData();
        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                String key = meta.getColumnName(i).toString();
                Object value = resultSet.getObject(key);
                map.put(key, value);
            }
            salida.add(map);
        }
        resultado.data = salida;
        
        return resultado;
    }
    
    public jsonResult GetCliente() throws Exception{
        jsonResult resultado = new jsonResult();
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexion = ConectionDB.getConnection();
        String sql = "select * from registro_cliente";
        
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
        resultado.data = salida;
        
        return resultado;
    }
	
}
