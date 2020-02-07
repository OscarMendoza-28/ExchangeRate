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
import com.mysql.jdbc.Connection;

public class Currency_CD {
 private String    CURRENCY_CD;
 private Date      EFFDT;
 private String    EFF_STATUS;
 private String    DESCR;
 private String    DESCRSHORT;
 private String    COUNTRY;
 private String    CUR_SYMBOL;
 private int       DECIMAL_POSITIONS;
 private int       SCALE_POSITIONS;
 private int       SYNCID;
 private Timestamp LASTUPDDTTM;

/* public Currency_CD(String CURRENCY_CD, Date EFFDT, String EFF_STATUS, String DESCR, String DESCRSHORT, String COUNTRY, String CUR_SYMBOL, int DECIMAL_POSITIONS, int SCALE_POSITIONS, int SYNCID, Timestamp LASTUPDDTTM) {*/
 public Currency_CD() {
 this.CURRENCY_CD       = "";
 this.EFFDT             = null;
 this.EFF_STATUS        = "";
 this.DESCR             = "";
 this.DESCRSHORT        = "";
 this.COUNTRY           = "";
 this.CUR_SYMBOL        = "";
 this.DECIMAL_POSITIONS = 0;
 this.SCALE_POSITIONS   = 0;
 this.SYNCID            = 0;
 this.LASTUPDDTTM       = null;
}

public String getCURRENCY_CD() {
 return CURRENCY_CD;
}

public Date getEFFDT() {
 return EFFDT;
}

public String getEFF_STATUS() {
 return EFF_STATUS;
}

public String getDESCR() {
 return DESCR;
}

public String getDESCRSHORT() {
 return DESCRSHORT;
}

public String getCOUNTRY() {
 return COUNTRY;
}

public String getCUR_SYMBOL() {
 return CUR_SYMBOL;
}

public int getDECIMAL_POSITIONS() {
 return DECIMAL_POSITIONS;
}

public int getSCALE_POSITIONS() {
 return SCALE_POSITIONS;
}

public int getSYNCID() {
 return SYNCID;
}

public Timestamp getLASTUPDDTTM() {
 return LASTUPDDTTM;
}

public boolean LeerCurrency(Connection DBConn, String Currency_Cd, String Eff_Status) throws SQLException
 {
  String    sqlText;
  ResultSet rSet;
  PreparedStatement pStat;
  int NumRows = 0;
  
  this.CURRENCY_CD = Currency_Cd;
  this.EFF_STATUS  = Eff_Status;
  sqlText = "Select CURRENCY_CD,      " +
            "       EFFDT,            " +
            "       EFF_STATUS,       " +
            "       DESCR,            " +
            "       DESCRSHORT,       " +
            "       COUNTRY,          " +
            "       CUR_SYMBOL,       " +
            "       DECIMAL_POSITIONS," +
            "       SCALE_POSITIONS,  " +
            "       SYNCID,           " +
            "       LASTUPDDTTM       " +
            "  From ps_currency_cd_tbl" +
            " Where CURRENCY_CD = ?   " +
            "   And EFF_STATUS  = ?;  ";
  pStat = DBConn.prepareStatement(sqlText);
  pStat.setString(1,this.CURRENCY_CD);
  pStat.setString(2,this.EFF_STATUS);
  rSet = pStat.executeQuery();
  while (rSet.next())
    {
     this.CURRENCY_CD       = rSet.getString("CURRENCY_CD");
     this.EFFDT             = rSet.getDate("EFFDT");
     this.EFF_STATUS        = rSet.getString("EFF_STATUS");
     this.DESCR             = rSet.getString("DESCR");
     this.DESCRSHORT        = rSet.getString("DESCRSHORT");
     this.COUNTRY           = rSet.getString("COUNTRY");
     this.CUR_SYMBOL        = rSet.getString("CUR_SYMBOL");
     this.DECIMAL_POSITIONS = rSet.getInt("DECIMAL_POSITIONS");
     this.SCALE_POSITIONS   = rSet.getInt("SCALE_POSITIONS");
     this.SYNCID            = rSet.getInt("SYNCID");
     this.LASTUPDDTTM       = rSet.getTimestamp("LASTUPDDTTM");
     NumRows++; 
    }
  if (NumRows > 0)
     return true;
  else
     return false;
 }
    
}
