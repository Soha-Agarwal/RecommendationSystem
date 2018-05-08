package com.DB;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

//import Final1.Orac;

//import Final1.Orac;

public class LargeCap implements Runnable 
{
   public void Price() throws SQLException
   {
	   System.out.println("Large cap and price");
	   int zl=0;
		Connection conn1l;
		conn1l = Oracle.odbcconn();
		Statement stmtl=conn1l.createStatement();    
       ResultSet rsl=stmtl.executeQuery("select * from LARGECAPS"); 
		ArrayList bbl=new ArrayList ();
		while(rsl.next())
		{
			zl++;
			System.out.println(zl);
			try {
				
				URL urll=new URL("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=NSE:"+rsl.getString(1)+"&interval=1min&outputsize=full&apikey=VF5XHGQ798FVHSDW");
				{
					try (InputStream isl = urll.openStream();JsonReader rdrl = Json.createReader(isl)) 
						{			 		
						    JsonObject objl = rdrl.readObject();
						    JsonObject o1l=objl.getJsonObject("Time Series (Daily)");
						    if(o1l!=null)
						    {
						    	Iterator<JsonValue> i1l = o1l.values().iterator();
							    float suml=0;
							    float avgl=0;
								for(int il=0;il<1;il++)
							       {
									   JsonObject o2l=(JsonObject) i1l.next();  
									   JsonValue sl=o2l.get("4. close");
									   String valuel = sl.toString().substring(1, sl.toString().length()-1);
									   float highestl=Float.valueOf(valuel);
									   //System.out.println(highest);	 
									   PreparedStatement stmt2l = conn1l.prepareStatement("UPDATE LARGECAPS SET PRICE = ? WHERE NAME =?"); 	  
									   stmt2l.setFloat(1,highestl);
									   stmt2l.setString(2,(String)rsl.getString(1));
									   stmt2l.executeUpdate();
									   bbl.add(highestl);
								   }
						    }
							
					   }
				}
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		// TODO Auto-generated method stub
//		for(int y=0;y<aa.size();y++)
//		{
//			int x=y+1;
//			System.out.println("\n"+x+".\t"+aa.get(y)+":"+bb.get(y));
//		}

   }
   public void Average() throws SQLException
   {
	   System.out.println("Large cap and avg");
	   int zll=0;
		ArrayList aall=new ArrayList();
		ArrayList bbll=new ArrayList ();
		Connection conn1ll;
		conn1ll = Oracle.odbcconn();
		Statement stmtll=conn1ll.createStatement();    
       ResultSet rsll=stmtll.executeQuery("select * from LARGECAPS"); 
       
		while(rsll.next())
		{
			zll++;
			//System.out.println(z);
			try {
				
				URL urlll=new URL("https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=NSE:"+rsll.getString(1)+"&apikey=VF5XHGQ798FVHSDW");
					{
						try (InputStream isll = urlll.openStream();
						  JsonReader rdrll = Json.createReader(isll)) {
								 		
								      JsonObject objll = rdrll.readObject();
								      

								      JsonObject o1ll=objll.getJsonObject("Weekly Time Series");
								      if(o1ll!=null)
								      {
								    	  Iterator<JsonValue> i1ll = o1ll.values().iterator();
									      float sumll=0;
									      float avgll=0;
									      float arll[]=new float[3];
									      for(int ill=0;ill<3;ill++)
									      {
									    	  String key="2. high";
									    	  JsonObject o2ll=(JsonObject) i1ll.next();  
									    	  JsonValue sll=o2ll.get("2. high");
									    	  String valuell = sll.toString().substring(1, sll.toString().length()-1);
									    	//JsonString s=o2.getJsonString("5. volume");
									    	  float highestll=Float.valueOf(valuell);
									    	//  arr[i]=highest;
									    	  sumll=sumll+highestll;		
									    	//S  System.out.println("\nHIGH: "+highest);
									      }
									      avgll=sumll/3;
									      PreparedStatement stmt2ll = conn1ll.prepareStatement("UPDATE LARGECAPS SET AVG = ? WHERE NAME =?");
								    	  
								    	  stmt2ll.setFloat(1,avgll);
								    	  stmt2ll.setString(2,(String)rsll.getString(1));
								    	  stmt2ll.executeUpdate();
//									      float sq=0;
//									      for(int i=0;i<3;i++)
//									      {
//									    	  arr[i]=arr[i]-avg;
//									    	  sq=sq+(arr[i]*arr[i]);
//									      }
//									      float var=sq/3;
//									      System.out.println("VARIANCE :"+var);
									      bbll.add(avgll);
								      }
								      
								      
								    //  System.out.println(o2.getJsonString("5. volume"));
								 }

					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		// TODO Auto-generated method stub
		/*for(int y=0;y<aa.size();y++)
		{
			int x=y+1;
			System.out.println("\n"+x+".\t"+aa.get(y)+":"+bb.get(y));
		}*/

   }
public void diff() throws SQLException
{
	int arr=0;float p,a;
	Connection conn1d;
	conn1d = Oracle.odbcconn();
	Statement stmtd=conn1d.createStatement();    
    ResultSet rsd=stmtd.executeQuery("select * from LARGECAPS");
    if(rsd != null)
    {
       while(rsd.next())
         {
    	   
    	   p= rsd.getFloat(2);
    	   a= rsd.getFloat(3);
    	   float diff = a-p;
       	diff=Math.abs(diff);
       	float per = (diff/a)*100;
       	PreparedStatement pstmtd = conn1d.prepareStatement("UPDATE LARGECAPS SET DIFF = ? WHERE NAME = ?");
       	pstmtd.setString(2,(String)rsd.getString(1));
       	pstmtd.setFloat(1,(float)per);
       	pstmtd.executeUpdate();
    	   
         }
    }
}
@Override
public void run() {
	// TODO Auto-generated method stub
	try {
		Price();
		Average();
		System.out.println("\nLargeCap Data Ended");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
