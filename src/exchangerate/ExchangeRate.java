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
import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.net.URL;
import java.net.HttpURLConnection;
import org.codehaus.jackson.map.ObjectMapper;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;
import static jdk.nashorn.internal.objects.NativeString.trim;

public class ExchangeRate
{
private static String[] KeyArgs = {"-User=","-Pass=","-IdMon=","-FechIni=","-FechFin="};
private static String   sUsuario;
private static String   sPassword;
private static String   sIdMon;
private static String   sIdSerie;
private static String   sFechIni;
private static String   sFechFin;
private static Date     dFechIni;
private static Date     dFechFin;

 public static Date ParseFecha(String fecha)
  {
   SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
   Date fechaDate = null;
   try {
        fechaDate = formato.parse(fecha);
       } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
   return fechaDate;
  }
 
 public static Response readSeries() throws Exception
 {
  URL url = new URL("https://www.banxico.org.mx/SieAPIRest/service/v1/series/" + sIdSerie + "/datos/" + sFechIni + "/" + sFechFin);
  HttpURLConnection cConnect = (HttpURLConnection) url.openConnection();
  cConnect.setRequestMethod("GET");
  cConnect.setRequestProperty("Content-Type", "application/json");
  cConnect.setRequestProperty("Bmx-Token", "6b0190f6def80603d3b32dd5e4ef35bfa4ad60bdfc190fad40933641ca821290");
  if (cConnect.getResponseCode() != HttpURLConnection.HTTP_OK) 
     throw new RuntimeException("HTTP error code : "+ cConnect.getResponseCode());
  ObjectMapper oMapper = new ObjectMapper();
  Response response=oMapper.readValue(cConnect.getInputStream(), Response.class);
  cConnect.disconnect();
  return response;     
 }
 
 public static void main(String[] args) throws SQLException
 {
  if(args.length == 0)
  {
    System.out.println("Sintaxis: "); 
    System.out.println("ExchangeRate -User=xxx -Pass=xxxx -IdMon=xxx -FechIni=YYYY-MM-DD (-FechFin=YYYY-MM-DD) "); 
    System.exit(0);
  }
  for(int i = 0; i < args.length; i++)
  {
    for(int x = 0; x < KeyArgs.length;x++)
    {
    if (args[i].contains(KeyArgs[x]))
       {
         System.out.println("Key      " + args[i].substring(0,KeyArgs[x].length()));
         System.out.println("Value    " + args[i].substring(KeyArgs[x].length(),args[i].length()));
         switch(KeyArgs[x]) /* args[i].substring(0,KeyArgs[x].length()) */
         {
          case "-User=":
              sUsuario = args[i].substring(KeyArgs[x].length(),args[i].length());
              break;
          case "-Pass=":
              sPassword = args[i].substring(KeyArgs[x].length(),args[i].length());
              break;
          case "-IdMon=":
              sIdMon = toUpperCase(trim(args[i].substring(KeyArgs[x].length(),args[i].length())));
              break;
          case "-FechIni=":
              sFechIni = args[i].substring(KeyArgs[x].length(),args[i].length());
              break;
          case "-FechFin=":
              sFechFin = args[i].substring(KeyArgs[x].length(),args[i].length());
              break;
         };
       };
    };
  }; 
/*  System.out.println("Usuario      " + sUsuario);
    System.out.println("Password     " + sPassword);*/
   if (sUsuario == null)
      {
        System.out.println("Parametro -User= es requerido.!"); 
        System.exit(100);
      };
   if (sPassword == null)
      {
        System.out.println("Parametro -Pass= es requerido.!"); 
        System.exit(101);
      };
   if (sIdMon == null)
      {
        System.out.println("Parametro -FechIni= es requerido.!"); 
        System.exit(102);
      };
   if (sFechIni == null) 
      {
        System.out.println("Parametro -FechFin= es requerido.!"); 
        System.exit(103);
      };
   if (sFechFin == null)
     sFechFin = sFechIni;
   DateValidator Validador = new DtIsVaidFormat("yyyy-MM-dd");   
   if (Validador.isValid(sFechIni) == false)
      {
        System.out.println("Parametro -FechIni= No es una fecha valida"); 
        System.exit(104);
      }
   if (Validador.isValid(sFechFin) == false)
      {
        System.out.println("Parametro -FechFin= No es una fecha valida"); 
        System.exit(105);
      }
   dFechIni = ParseFecha(sFechIni);
   dFechFin = ParseFecha(sFechFin);
   if (!dFechFin.before(dFechIni))
    {
    } else {
       System.out.println("Parametro -FechFin= no puede ser menor a -FechIni=");  
       System.exit(106);
    }
    ConnectionDB SQL = new ConnectionDB();
    SQL.setUser(sUsuario);
    SQL.setPass(sPassword);
    SQL.ConectMySQL();
    Currency_CD Curr_Cd = new Currency_CD();
    if (!Curr_Cd.LeerCurrency(SQL.getMyDBConn(),sIdMon, "A" ))
       {
        System.out.println("No existe moneda " + sIdMon + " o no esta activa."); 
        System.exit(201);
        }
    System.out.println("Moneda "+ Curr_Cd.getCURRENCY_CD() + " " + Curr_Cd.getDESCR() + Curr_Cd.getEFF_STATUS() + " " + Curr_Cd.getEFFDT() + " " + Curr_Cd.getLASTUPDDTTM());
    Currency_Serie Curr_Serie = new Currency_Serie();
    if (!Curr_Serie.LeerCurrency_Serie(SQL.getMyDBConn(),sIdMon ))
       {
        System.out.println("No existe Serie para la moneda " + sIdMon + "."); 
        System.exit(202);
        }
    sIdSerie = Curr_Serie.getIDSERIE();
    System.out.println("Serie: " + Curr_Serie.getIDSERIE() + ".");
    try {
         Response response = readSeries();
	 Serie serie=response.getBmx().getSeries().get(0);
	 System.out.println("Serie: " + serie.getTitulo());
         Rt_Rate rtRt_Rate = new Rt_Rate();
	 for(DataSerie data:serie.getDatos())
            {
	    if(data.getDato().equals("N/E"))
              continue;
	     System.out.println("Fecha: "+data.getFecha());
	     System.out.println("Dato:  "+data.getDato());
             if (rtRt_Rate.ExisteRt_Rate(SQL.getMyDBConn(),"EXCHANGE",0,sIdMon,"MXN","DIAR",data.getFecha()))
                { 
                System.out.println("Si existe "+data.getFecha()+ " " + data.getDato());
                rtRt_Rate.UpdateRt_Rate(SQL.getMyDBConn(),"EXCHANGE",0,sIdMon,"MXN","DIAR",data.getFecha(),Float.parseFloat(data.getDato()),(float) 1.0 );
                }
             else
                {
                System.out.println("No existe "+data.getFecha()+ " " + data.getDato());
                rtRt_Rate.InsertRt_Rate(SQL.getMyDBConn(),"EXCHANGE",0,sIdMon,"MXN","DIAR",data.getFecha(),Float.parseFloat(data.getDato()),(float) 1.0 );
                }
             if (rtRt_Rate.ExisteRt_Rate(SQL.getMyDBConn(),"EXCHANGE",0,"MXN",sIdMon,"DIAR",data.getFecha()))
                {
                System.out.println("Si existe "+data.getFecha()+ " " + data.getDato());
                rtRt_Rate.UpdateRt_Rate(SQL.getMyDBConn(),"EXCHANGE",0,"MXN",sIdMon,"DIAR",data.getFecha(),(float) 1.0, Float.parseFloat(data.getDato()) );
                }
             else
                {
                System.out.println("No existe "+data.getFecha()+ " " + data.getDato());
                rtRt_Rate.InsertRt_Rate(SQL.getMyDBConn(),"EXCHANGE",0,"MXN",sIdMon, "DIAR",data.getFecha(),(float) 1.0,Float.parseFloat(data.getDato()));                        
                }
	    }
	} catch(Exception e)
          {
	   System.out.println("ERROR: "+e.getMessage());
 	  }
    if (SQL.getMyDBConn() != null)
      SQL.DisConectMySQL();
 };
};