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

public class SmallCap implements Runnable{
	 public void Price() throws SQLException
	   {
		   System.out.println("Small cap and price");
		   int zs=1000;
			Connection conn1s;
			conn1s = Oracle.odbcconn();
			Statement stmt=conn1s.createStatement();    
	        ResultSet rss=stmt.executeQuery("select * from SMALLCAPS"); 
	        
	        
			ArrayList aas=new ArrayList();
			ArrayList bbs=new ArrayList ();
			/*aa.add("ADANIPORTS");aa.add("AMBUJACEM");aa.add("ASIANPAINT");aa.add("AUROPHARMA");
			aa.add("AXISBANK");aa.add("BAJAJ-AUTO");aa.add("BAJFINANCE");aa.add("BPCL");aa.add("BHARTIARTL");aa.add("INFRATEL");
			aa.add("BOSCHLTD");aa.add("CIPLA");aa.add("COALINDIA");aa.add("DRREDDY");aa.add("EICHERMOT");aa.add("GAIL");
			aa.add("HCLTECH");aa.add("HDFCBANK");aa.add("HEROMOTOCO");aa.add("HINDALCO");aa.add("HINDPETRO");aa.add("HINDUNILVR");
			aa.add("HDFC");aa.add("ITC");aa.add("ICICIBANK");aa.add("IBULHSGFIN");aa.add("IOC");aa.add("INDUSINDBK");aa.add("INFY");
			aa.add("KOTAKBANK");aa.add("LT");aa.add("LUPIN");aa.add("MARUTI");aa.add("NTPC");aa.add("ONGC");aa.add("POWERGRID");
			aa.add("RELIANCE");aa.add("SBIN");aa.add("SUNPHARMA");aa.add("TCS");aa.add("TATAMOTORS");aa.add("TATASTEEL");aa.add("TECHM");
			aa.add("UPL");aa.add("ULTRACEMCO");aa.add("VEDL");aa.add("WIPRO");aa.add("YESBANK");aa.add("ZEEL");*/
			while(rss.next())
			{
				zs++;
				System.out.println(zs);
				try {
					
					URL urls=new URL("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=NSE:"+rss.getString(1)+"&interval=1min&outputsize=full&apikey=VF5XHGQ798FVHSDW");
					{
							try (InputStream iss = urls.openStream();
									       JsonReader rdrs = Json.createReader(iss)) {
									 		
									      JsonObject objs = rdrs.readObject();
									      JsonObject o1s=objs.getJsonObject("Time Series (Daily)");
									   //   Collection<JsonValue> c1=o1.values();
									      if(o1s!=null)
									      {
									    	  Iterator<JsonValue> i1s = o1s.values().iterator();
										      float sums=0;
										      float avgs=0;
										      for(int is=0;is<1;is++)
										      {
										    	  JsonObject o2s=(JsonObject) i1s.next();  
										    	  JsonValue ss=o2s.get("4. close");
										    	  String values = ss.toString().substring(1, ss.toString().length()-1);
										    	  float highests=Float.valueOf(values);
										    	 // System.out.println(highest);
										    	 
										    	  PreparedStatement stmt2s = conn1s.prepareStatement("UPDATE SMALLCAPS SET PRICE = ? WHERE SYMBOL =?");
										    	  
										    	  stmt2s.setFloat(1,highests);
										    	  stmt2s.setString(2,(String)rss.getString(1));
										    	  stmt2s.executeUpdate();
										    	  bbs.add(highests);
										      }
									      }
									      
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
				//System.out.println("\n"+x+".\t"+aa.get(y)+":"+bb.get(y));
			}*/

	   }
	   public void Average() throws NumberFormatException, SQLException
	   {
		   System.out.println("Small cap and avg");
		   int zss=0;
			ArrayList aass=new ArrayList();
			ArrayList bbss=new ArrayList ();
			Connection conn1ss;
			conn1ss = Oracle.odbcconn();
			Statement stmtss=conn1ss.createStatement();    
	        ResultSet rsss=stmtss.executeQuery("select * from SMALLCAPS"); 
	        
			while(rsss.next())
			{
				zss++;
				//System.out.println(z);
				try {
					
					URL urlss=new URL("https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=NSE:"+rsss.getString(1)+"&apikey=VF5XHGQ798FVHSDW");
						{
							try (InputStream isss = urlss.openStream();
							  JsonReader rdrss = Json.createReader(isss)) {
									 		
									      JsonObject objss = rdrss.readObject();
									      

									      JsonObject o1ss=objss.getJsonObject("Weekly Time Series");
									      if(o1ss!=null)
									      {
									    	  Iterator<JsonValue> i1ss = o1ss.values().iterator();
										      float sumss=0;
										      float avgss=0;
										      float arrss[]=new float[3];
										      for(int iss=0;iss<3;iss++)
										      {
										    	  JsonObject o2ss=(JsonObject) i1ss.next();  
										    	  JsonValue sss=o2ss.get("2. high");
										    	  String valuess = sss.toString().substring(1, sss.toString().length()-1);
										    	//JsonString s=o2.getJsonString("5. volume");
										    	  float highestss=Float.valueOf(valuess);
										    	//  arr[i]=highest;
										    	  sumss=sumss+highestss;		
										    	//S  System.out.println("\nHIGH: "+highest);
										      }
										      avgss=sumss/3;
										      PreparedStatement stmt2ss = conn1ss.prepareStatement("UPDATE SMALLCAPS SET AVG = ? WHERE SYMBOL =?");
									    	  
									    	  stmt2ss.setFloat(1,avgss);
									    	  stmt2ss.setString(2,(String)rsss.getString(1));
									    	  stmt2ss.executeUpdate();
										      bbss.add(avgss);
									      }
									      
									 }
						}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
	   }
	   public void diff() throws SQLException
	   {
	   	int arr=0;float p2,a2;
	   	Connection conn3d;
	   	conn3d = Oracle.odbcconn();
	   	Statement stmtd3=conn3d.createStatement();    
	       ResultSet rsd3=stmtd3.executeQuery("select * from SMALLCAPS");
	       if(rsd3 != null)
	       {
	          while(rsd3.next())
	            {
	       	   
	       	   p2= rsd3.getFloat(2);
	       	   a2= rsd3.getFloat(3);
	       	   float diff3 = a2-p2;
	          	diff3=Math.abs(diff3);
	          	float per3 = (diff3/a2)*100;
	          	PreparedStatement pstmtd3 = conn3d.prepareStatement("UPDATE SMALLCAPS SET DIFF = ? WHERE SYMBOL = ?");
	          	pstmtd3.setString(2,(String)rsd3.getString(1));
	          	pstmtd3.setFloat(1,(float)per3);
	          	pstmtd3.executeUpdate();
	       	   
	            }
	       }
	   }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Price();
			Average();
			System.out.println("\nSmallCap Data Ended");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
