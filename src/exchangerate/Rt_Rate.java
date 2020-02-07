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

public class Rt_Rate {
 private String    RT_RATE_INDEX;
 private int       TERM;
 private String    FROM_CUR;
 private String    TO_CUR;
 private String    RT_TYPE;
 private String    EFFDT;
 private float     RATE_MULT;
 private float     RATE_DIV;
 private int       SYNCID;
 private Timestamp LASTUPDDTTM;
 
 public Rt_Rate() {
 this.RT_RATE_INDEX = "EXCHANGE";
 this.TERM          = 0;
 this.FROM_CUR      = "";
 this.TO_CUR        = "";
 this.RT_TYPE       = "DIAR";
 this.EFFDT         = "";
 this.RATE_MULT     = (float) 0.0;
 this.RATE_DIV      = (float) 0.0;
 this.SYNCID        = 1;
 this.LASTUPDDTTM   = null;
 }

public String getRT_RATE_INDEX() {
 return RT_RATE_INDEX;
}

public void setRT_RATE_INDEX(String RT_RATE_INDEX) {
 this.RT_RATE_INDEX = RT_RATE_INDEX;
}

public int getTERM() {
 return TERM;
}

public void setTERM(int TERM) {
 this.TERM = TERM;
}

public String getFROM_CUR() {
 return FROM_CUR;
}

public void setFROM_CUR(String FROM_CUR) {
 this.FROM_CUR = FROM_CUR;
}

public String getTO_CUR() {
 return TO_CUR;
}

public void setTO_CUR(String TO_CUR) {
 this.TO_CUR = TO_CUR;
}

public String getRT_TYPE() {
 return RT_TYPE;
}

public void setRT_TYPE(String RT_TYPE) {
 this.RT_TYPE = RT_TYPE;
}

public String getEFFDT() {
 return EFFDT;
}

public void setEFFDT(String EFFDT) {
 this.EFFDT = EFFDT;
}

public float getRATE_MULT() {
 return RATE_MULT;
}

public void setRATE_MULT(float RATE_MULT) {
 this.RATE_MULT = RATE_MULT;
}

public float getRATE_DIV() {
 return RATE_DIV;
}

public void setRATE_DIV(float RATE_DIV) {
 this.RATE_DIV = RATE_DIV;
}

public int getSYNCID() {
 return SYNCID;
}

public void setSYNCID(int SYNCID) {
 this.SYNCID = SYNCID;
}

public Timestamp getLASTUPDDTTM() {
 return LASTUPDDTTM;
}

public void setLASTUPDDTTM(Timestamp LASTUPDDTTM) {
 this.LASTUPDDTTM = LASTUPDDTTM;
}

public boolean ExisteRt_Rate(Connection DBConn, String sRt_Rate_Index, int iTerm, String sFrom_Cur, String sTo_Cur, String sRt_Type, String sEffdt) throws SQLException
{
 String    sqlText;
 ResultSet rSet;
 PreparedStatement pStat;
 int NumRows = 0;
 
 this.RT_RATE_INDEX = sRt_Rate_Index;
 this.TERM          = iTerm;
 this.FROM_CUR      = sFrom_Cur;
 this.TO_CUR        = sTo_Cur;
 this.RT_TYPE       = sRt_Type;
 this.EFFDT         = sEffdt;
 sqlText = "Select RT_RATE_INDEX,   " +
           "       TERM,            " +
           "       FROM_CUR,        " +
           "       TO_CUR,          " +
           "       RT_TYPE,         " +
           "       EFFDT,           " +
           "       RATE_MULT,       " +
           "       RATE_DIV,        " +
           "       SYNCID,          " +
           "       LASTUPDDTTM      " +
           " From ps_rt_rate_tbl    " +
           "Where RT_RATE_INDEX = ? " +
           "  And TERM          = ? " +
           "  And FROM_CUR      = ? " +
           "  And TO_CUR        = ? " +
           "  And RT_TYPE       = ? " +
           "  And EFFDT         = STR_TO_DATE(?,'%d/%m/%Y')";
  pStat = DBConn.prepareStatement(sqlText);
  pStat.setString(1,this.RT_RATE_INDEX);
  pStat.setInt(2,this.TERM);
  pStat.setString(3,this.FROM_CUR);
  pStat.setString(4,this.TO_CUR);
  pStat.setString(5,this.RT_TYPE);
  pStat.setString(6,this.EFFDT);
  rSet = pStat.executeQuery();
    while (rSet.next())
    NumRows++;
    if (NumRows > 0)
     return true;
  else
     return false;
}
public boolean UpdateRt_Rate(Connection DBConn, String sRt_Rate_Index, int iTerm, String sFrom_Cur, String sTo_Cur, String sRt_Type, String sEffdt, float fRate_Mult, float fRate_Div ) throws SQLException
{
 String    sqlText;
 ResultSet rSet;
 PreparedStatement pStat;
 int NumRows = 0;
 
 this.RT_RATE_INDEX = sRt_Rate_Index;
 this.TERM          = iTerm;
 this.FROM_CUR      = sFrom_Cur;
 this.TO_CUR        = sTo_Cur;
 this.RT_TYPE       = sRt_Type;
 this.EFFDT         = sEffdt;
 this.RATE_MULT     = fRate_Mult;
 this.RATE_DIV      = fRate_Div;
 sqlText = "Update ps_rt_rate_tbl       " +
           "   Set RATE_MULT    = ?,    " +
           "       RATE_DIV     = ?,    " +
           "       LASTUPDDTTM  = Now() " +
           "Where RT_RATE_INDEX = ?     " +
           "  And TERM          = ?     " +
           "  And FROM_CUR      = ?     " +
           "  And TO_CUR        = ?     " +
           "  And RT_TYPE       = ?     " +
           "  And EFFDT         = STR_TO_DATE(?,'%d/%m/%Y')";
  pStat = DBConn.prepareStatement(sqlText);
  pStat.setFloat(1,this.RATE_MULT);
  pStat.setFloat(2,this.RATE_DIV);
  pStat.setString(3,this.RT_RATE_INDEX);
  pStat.setInt(4,this.TERM);
  pStat.setString(5,this.FROM_CUR);
  pStat.setString(6,this.TO_CUR);
  pStat.setString(7,this.RT_TYPE);
  pStat.setString(8,this.EFFDT);
  NumRows = pStat.executeUpdate();
  if (NumRows > 0)
     return true;
  else
     return false;
}
public boolean InsertRt_Rate(Connection DBConn, String sRt_Rate_Index, int iTerm, String sFrom_Cur, String sTo_Cur, String sRt_Type, String sEffdt, float fRate_Mult, float fRate_Div ) throws SQLException
{
 String    sqlText;
 ResultSet rSet;
 PreparedStatement pStat;
 int NumRows = 0;
 
 this.RT_RATE_INDEX = sRt_Rate_Index;
 this.TERM          = iTerm;
 this.FROM_CUR      = sFrom_Cur;
 this.TO_CUR        = sTo_Cur;
 this.RT_TYPE       = sRt_Type;
 this.EFFDT         = sEffdt;
 this.RATE_MULT     = fRate_Mult;
 this.RATE_DIV      = fRate_Div;
 sqlText = "Insert Into ps_rt_rate_tbl " +
           "           (RT_RATE_INDEX, " +
           "            TERM,          " +
           "            FROM_CUR,      " +
           "            TO_CUR,        " +
           "            RT_TYPE,       " +
           "            EFFDT,         " +
           "            RATE_MULT,     " +
           "            RATE_DIV,      " +
           "            SYNCID,        " +
           "            LASTUPDDTTM)   " +
           "    Values (?,             " +
           "            ?,             " +
           "            ?,             " +
           "            ?,             " +
           "            ?,             " +
           "            STR_TO_DATE(?,'%d/%m/%Y')," +
           "            ?,             " +
           "            ?,             " +
           "            1,             " +
           "            Now())         ";
  pStat = DBConn.prepareStatement(sqlText);
  pStat.setString(1,this.RT_RATE_INDEX);
  pStat.setInt(2,this.TERM);
  pStat.setString(3,this.FROM_CUR);
  pStat.setString(4,this.TO_CUR);
  pStat.setString(5,this.RT_TYPE);
  pStat.setString(6,this.EFFDT);
  pStat.setFloat(7,this.RATE_MULT);
  pStat.setFloat(8,this.RATE_DIV);
  NumRows = pStat.executeUpdate();
  if (NumRows > 0)
     return true;
  else
     return false;
}
}
