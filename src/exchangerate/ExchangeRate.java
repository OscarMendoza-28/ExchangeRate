/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangerate;
import com.mysql.jdbc.Connection;
import java.sql.SQLException;
/**
 *
 * @author OM000402
 */
public class ExchangeRate
{   

 public static void main(String[] args)
 {
    ConnectionDB SQL = new ConnectionDB();
    Connection conn = SQL.conectarMySQL();
    if (conn != null)
    {
       try{
            conn.close();
            System.out.println("Connexion Cerrada");   
          } catch (SQLException e)
            {
             System.out.println("Connexion no se pudo Cerrar");
            }   
    }
 }
}
