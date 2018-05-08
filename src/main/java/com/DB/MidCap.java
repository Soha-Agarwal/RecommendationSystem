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

public class MidCap implements Runnable{
	 public void Price() throws SQLException
	   {
		   System.out.println("Mid cap and price");
		   int zm=100;
			Connection conn1m;
			conn1m = Oracle.odbcconn();
			Statement stmtm=conn1m.createStatement();    
	        ResultSet rsm=stmtm.executeQuery("select * from MIDCAPS"); 
	        
			ArrayList aam=new ArrayList();
			ArrayList bbm=new ArrayList ();
			while(rsm.next())
			{
				zm++;
				System.out.println(zm);
				try {
					
					URL urlm=new URL("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=NSE:"+rsm.getString(1)+"&interval=1min&outputsize=full&apikey=VF5XHGQ798FVHSDW");
					{
							try (InputStream ism = urlm.openStream();
									       JsonReader rdrm = Json.createReader(ism)) {
									 		
									      JsonObject objm = rdrm.readObject();
									      JsonObject o1m=objm.getJsonObject("Time Series (Daily)");
									   //   Collection<JsonValue> c1=o1.values();
									      if(o1m!=null)
									      {
									    	  Iterator<JsonValue> i1m = o1m.values().iterator();
										      float summ=0;
										      float avgm=0;
										      for(int im=0;im<1;im++)
										      {
										    	  JsonObject o2m=(JsonObject) i1m.next();  
										    	  JsonValue sm=o2m.get("4. close");
										    	  String valuem = sm.toString().substring(1, sm.toString().length()-1);
										    	  float highestm=Float.valueOf(valuem);
										    	 // System.out.println(highest);
										    	 
										    	  PreparedStatement stmt2m = conn1m.prepareStatement("UPDATE MIDCAPS SET PRICE = ? WHERE SYMBOL =?");
										    	  
										    	  stmt2m.setFloat(1,highestm);
										    	  stmt2m.setString(2,(String)rsm.getString(1));
										    	  stmt2m.executeUpdate();
										    	  bbm.add(highestm);
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
				System.out.println("\n"+x+".\t"+aa.get(y)+":"+bb.get(y));
			}*/

	   }
	   public void Average() throws NumberFormatException, SQLException
	   {
		   System.out.println("Mid cap and avg");
		   int zmm=0;
			ArrayList aamm=new ArrayList();
			ArrayList bbmm=new ArrayList ();
			Connection conn1mm;
			conn1mm = Oracle.odbcconn();
			Statement stmtmm=conn1mm.createStatement();    
	        ResultSet rsmm=stmtmm.executeQuery("select * from MIDCAPS"); 
	        
			while(rsmm.next())
			{
				zmm++;
				//System.out.println(z);
				try {
					
					URL urlmm=new URL("https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=NSE:"+rsmm.getString(1)+"&apikey=VF5XHGQ798FVHSDW");
						{
							try (InputStream ismm = urlmm.openStream();
							  JsonReader rdrmm = Json.createReader(ismm)) {
									 		
									      JsonObject objmm = rdrmm.readObject();
									      

									      JsonObject o1mm=objmm.getJsonObject("Weekly Time Series");
									      if(o1mm!=null)
									      {
									    	  Iterator<JsonValue> i1mm = o1mm.values().iterator();
										      float summm=0;
										      float avgmm=0;
										      float arrmm[]=new float[3];
										      for(int imm=0;imm<3;imm++)
										      {
										    	  JsonObject o2mm=(JsonObject) i1mm.next();  
										    	  JsonValue smm=o2mm.get("2. high");
										    	  String valuemm = smm.toString().substring(1, smm.toString().length()-1);
										    	//JsonString s=o2.getJsonString("5. volume");
										    	  float highestmm=Float.valueOf(valuemm);
										    	//  arr[i]=highest;
										    	  summm=summm+highestmm;		
										    	//S  System.out.println("\nHIGH: "+highest);
										      }
										      avgmm=summm/3;
										      PreparedStatement stmt2mm = conn1mm.prepareStatement("UPDATE MIDCAPS SET AVG = ? WHERE SYMBOL =?");
									    	  
									    	  stmt2mm.setFloat(1,avgmm);
									    	  stmt2mm.setString(2,(String)rsmm.getString(1));
									    	  stmt2mm.executeUpdate();
//										      float sq=0;
//										      for(int i=0;i<3;i++)
//										      {
//										    	  arr[i]=arr[i]-avg;
//										    	  sq=sq+(arr[i]*arr[i]);
//										      }
//										      float var=sq/3;
//										      System.out.println("VARIANCE :"+var);
										      bbmm.add(avgmm);
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
	   	int arr=0;float p1,a1;
	   	Connection conn2d;
	   	conn2d = Oracle.odbcconn();
	   	Statement stmtd1=conn2d.createStatement();    
	       ResultSet rsd1=stmtd1.executeQuery("select * from MIDCAPS");
	       if(rsd1 != null)
	       {
	          while(rsd1.next())
	            {
	       	   
	       	   p1= rsd1.getFloat(2);
	       	   a1= rsd1.getFloat(3);
	       	   float diff = a1-p1;
	          	diff=Math.abs(diff);
	          	float per1 = (diff/a1)*100;
	          	PreparedStatement pstmtd = conn2d.prepareStatement("UPDATE MIDCAPS SET DIFF = ? WHERE SYMBOL = ?");
	          	pstmtd.setString(2,(String)rsd1.getString(1));
	          	pstmtd.setFloat(1,(float)per1);
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
			System.out.println("\nMidCap Data Ended");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
