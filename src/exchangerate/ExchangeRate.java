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
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class ExchangeRate
{
private static String[] KeyArgs = {"-User=","-Pass=","-IdMon=","-FechIni=","-FechFin="};
private static String   sUsuario;
private static String   sPassword;
private static String   sIdMon;
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
 
 public static void main(String[] args)
 {
  if(args.length == 0)
  {
    System.out.println("Sintaxis: "); 
    System.out.println("ExchangeRate -User=xxx -Pass=xxxx -IdMon=xxx -FechIni=YYYY-MM-DD (-FechFin=YYYY-MM-DD) "); 
    System.exit(0);
  }
  for(int i = 0; i < args.length; i++)
    for(int x = 0; x < KeyArgs.length;x++) 
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
              sIdMon = args[i].substring(KeyArgs[x].length(),args[i].length());
              break;
          case "-FechIni=":
              sFechIni = args[i].substring(KeyArgs[x].length(),args[i].length());
              break;
          case "-FechFin=":
              sFechFin = args[i].substring(KeyArgs[x].length(),args[i].length());
              break;
         };
       };
/*  System.out.println("Usuario      " + sUsuario);
    System.out.println("Password     " + sPassword);*/
   if (sUsuario == null)
      {
        System.out.println("Parametro -User= es requerido.!"); 
        System.exit(0);
      };
   if (sPassword == null)
      {
        System.out.println("Parametro -Pass= es requerido.!"); 
        System.exit(0);
      };
   if (sIdMon == null)
      {
        System.out.println("Parametro -FechIni= es requerido.!"); 
        System.exit(0);
      };
   if (sFechIni == null) 
      {
        System.out.println("Parametro -FechFin= es requerido.!"); 
        System.exit(0);
      };
   if (sFechFin == null)
     sFechFin = sFechIni;
   DateValidator Validador = new DtIsVaidFormat("yyyy-MM-dd");   
   if (Validador.isValid(sFechIni) == false)
      {
        System.out.println("Parametro -FechFin= No es una fecha valida"); 
        System.exit(0);
      }
   if (Validador.isValid(sFechFin) == false)
      {
        System.out.println("Parametro -FechFin= No es una fecha valida"); 
        System.exit(0);
      }
   dFechIni = ParseFecha(sFechIni);
   dFechFin = ParseFecha(sFechFin);
   if (!dFechFin.before(dFechIni))
    {
    } else {
       System.out.println("Parametro -FechFin= no puede ser menor a -FechIni=");  
       System.exit(0);
    }
    ConnectionDB SQL = new ConnectionDB();
    SQL.setUser(sUsuario);
    SQL.setPass(sPassword);
    SQL.ConectMySQL();
    
    if (SQL.getMyDBConn() != null)
      SQL.DisConectMySQL();
 };
};