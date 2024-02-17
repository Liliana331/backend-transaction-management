package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
PreparedStatement: tiene mejor desempeño que Statement, se usa cualquiera
*/


public class Conexion {

    public static final String URL = "jdbc:mysql://localhost:3306/udemy?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    public Conexion() {
    }

    public static Connection getConnection(){
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out); 
       }
        return conexion;
    }
    
    //se hace sobre carga de metodos para cerrar las conexiones
    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

    }
    
    public static void close(Statement stm) {
        try {
            stm.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

    }
    
    public static void close(PreparedStatement stm) {
        try {
            stm.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

    }
    
    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

    }
}
