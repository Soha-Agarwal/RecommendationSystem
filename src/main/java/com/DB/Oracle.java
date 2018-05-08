package com.DB;

import java.sql.*;
import java.util.ArrayList; 
public class Oracle 
{
    public static Connection odbcconn()
    {
    	 try
    	 {    
             Class.forName("oracle.jdbc.driver.OracleDriver");  
             Connection con=DriverManager.getConnection(  
             "jdbc:oracle:thin:@localhost:1521:xe","system","root");
             return con;
    	 }
    	 catch(Exception e)
         { 
   	         System.out.println(e);
   	     }  
    	 return null;
    }
}