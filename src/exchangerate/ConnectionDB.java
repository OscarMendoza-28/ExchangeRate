/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangerate;
/**
 *
 * @author OM000402
 */
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
private static final String driver   = "com.mysql.jdbc.Driver";
private static final String hostname = "localhost";
private static final String port     = "3306";
private static final String database = "exchangerate";
private static final String user     = "root";
private static final String pass     = "tecmilenio";
private static       String url      = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
private static   Connection MyDBConn;

public Connection conectarMySQL() {
    MyDBConn = null;
    try {
         Class.forName(driver);
         MyDBConn = (Connection) DriverManager.getConnection(url, user, pass);
         } catch (ClassNotFoundException | SQLException e) {
         /*e.printStackTrace(); */
           System.out.println(String.valueOf(e));   
        }
        System.out.println("Connexion establecida");   
        return MyDBConn;
  }
}