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
private static final String url      = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";

private Connection MyDBConn = null;
private String user = "";
private String pass = "";


public String getUser() {
 return user;
}

public void setUser(String user)
{
 this.user = user;
}

public String getPass()
{
 return pass;
}

public void setPass(String pass)
{
 this.pass = pass;
}

public Connection getMyDBConn()
{
 return MyDBConn;
}

public void ConectMySQL()
 {
  try {
       Class.forName(driver);
       this.MyDBConn = (Connection) DriverManager.getConnection(url, this.user, this.pass);
       if (this.MyDBConn != null)
           System.out.println("Connexion establecida"); 
      } catch (ClassNotFoundException | SQLException e)
       {
        /* System.out.println(String.valueOf(e)); */
           System.out.println(e.getMessage());
           System.exit(107);
       }     
 }
public void DisConectMySQL() {
 if (this.MyDBConn != null)
  try{
      this.MyDBConn.close();
      System.out.println("Connexion Cerrada");   
     } catch (SQLException e)
       {
        System.out.println("Connexion no se pudo Cerrar");
        System.exit(108);
       } 
  else
     {
      System.out.println("No hay connexion abierta");
      System.exit(109);
     }
  }
}