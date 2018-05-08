package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.DB.Oracle;

public class HistoryDAO {

	
	ArrayList<CapRecord> records=new ArrayList<CapRecord>();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	   LocalDateTime now = LocalDateTime.now(); 
	   
	//Enter into Database
	public void historyDB(String username,String company,float price) throws SQLException
	{Connection conn1U;
	conn1U = Oracle.odbcconn();
	String query="SELECT * FROM HISTORY where company=?";

	PreparedStatement stmtu=conn1U.prepareStatement(query);
	stmtu.setString(1,company);
	ResultSet rs=stmtu.executeQuery();
if(rs.next())   //Update
	{
		PreparedStatement pst = conn1U.prepareStatement("upadte HISTORY set price=(?) where company="+company);
	      pst.setFloat(1,price);
	}
	else{
	      PreparedStatement pst = conn1U.prepareStatement("insert into HISTORY values(?,?,?,?)");
	      pst.setString(1,username);
	      pst.setString(2,company);
	      pst.setFloat(3,price);
	      pst.setString(4,dtf.format(now).toString());
	      pst.executeUpdate();
		

		System.out.println();
	}
		   System.out.println(dtf.format(now));  

		
	}
	public ArrayList<HistoryBean> getHistory(String username) throws SQLException
	{
		Connection conn;
		conn = Oracle.odbcconn();
		String query="SELECT * FROM HISTORY where USERNAME=?";
		PreparedStatement stmtu=conn.prepareStatement(query);
		stmtu.setString(1, username);
		ResultSet rsH=stmtu.executeQuery();
		ArrayList<HistoryBean> hb=new ArrayList<HistoryBean>();
		while(rsH.next())
		{
			HistoryBean h = new HistoryBean();
			h.setCompany(rsH.getString("company"));
			h.setPrice(rsH.getFloat("price"));
			h.setDate1(rsH.getString("date1"));
			System.out.println("Date is "+h.getDate1());
			hb.add(h);
		}
		for(int i=0;i<hb.size();i++)
		{
			System.out.println("array");
			System.out.println(hb.toString());
		}
		return hb;
	}
}
