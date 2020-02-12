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
import java.sql.*;
import java.util.Date;
import java.sql.Connection;

public class Currency_Serie {
 private String CURRENCY_CD;
 private String IDSERIE;

 public Currency_Serie() {
 this.CURRENCY_CD = "";
 this.IDSERIE     = "";
}
public String getCURRENCY_CD() {
 return CURRENCY_CD;
}

public String getIDSERIE() {
 return IDSERIE;
}
public boolean LeerCurrency_Serie(Connection DBConn, String Currency_Cd) throws SQLException
 {
  String    sqlText;
  ResultSet rSet;
  PreparedStatement pStat;
  int NumRows = 0;
  this.CURRENCY_CD = Currency_Cd;
  sqlText = "Select CURRENCY_CD,     " +
            "       IDSERIE          " +
            "  From ps_currency_serie" +
            " Where CURRENCY_CD = ?   ";
  pStat = DBConn.prepareStatement(sqlText);
  pStat.setString(1,this.CURRENCY_CD);
  rSet = pStat.executeQuery();
  while (rSet.next())
    {
     this.CURRENCY_CD = rSet.getString("CURRENCY_CD");
     this.IDSERIE = rSet.getString("IDSERIE");
     NumRows++; 
    }
  if (NumRows > 0)
     return true;
  else
     return false;
 }
}
