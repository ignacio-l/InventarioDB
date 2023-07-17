package bd;

import java.sql.*;


public class ConectarBD {

    private static final String nombreBaseDeDatos = "prueba";
    private static final String url = "jdbc:mysql://localhost:3306/" + nombreBaseDeDatos;
    private static final String usuario = "root";
    private static final String password = "root";


    public static Connection conectar() throws SQLException {
       return DriverManager.getConnection(url,usuario,password);
    }

}