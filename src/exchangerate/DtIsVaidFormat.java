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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class DtIsVaidFormat implements DateValidator 
{
  private String DtFrmt;   
  public DtIsVaidFormat(String dateFormat) 
   {
    this.DtFrmt = dateFormat;
   }
  @Override 
  public boolean isValid(String dateStr)
  {
   DateFormat sDtFormat = new SimpleDateFormat(this.DtFrmt);
   sDtFormat.setLenient(false);
   try
      {
       sDtFormat.parse(dateStr);
      } catch (ParseException e)
        {
          return false;
        } 
     return true;
  }  
}
