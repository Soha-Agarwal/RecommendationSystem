package com.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.example.demo.CapRecord;

public class UserInput {

	public ArrayList<CapRecord> method(String risk,String amount) throws SQLException 
	{
		// TODO Auto-generated method stub
		Connection conn1u;
		conn1u = Oracle.odbcconn();
		   
		
        
        Scanner sc=new Scanner(System.in);
		float budget;
		double sb=0,lb=0,mb=0;
		
		System.out.println("\nAmount is  :"+amount);
		budget=Float.parseFloat(amount);
		switch(risk)
		{
		case "high":
		{
			sb=(0.6*budget);
			mb=(0.3*budget);
			lb=(0.1*budget);
		}
		break;
		case "medium":
		{
			sb=(0.4*budget);
			mb=(0.3*budget);
			lb=(0.3*budget);
		}
		break;
		case "low":
		{
			sb=(0.1*budget);
			mb=(0.3*budget);
			lb=(0.6*budget);
		}
		break;
			
		}
		System.out.println("\n\nSMALL :"+sb+"\tMEDIUM :"+mb+"\tLARGE :"+lb);
		ArrayList<CapRecord> records=new ArrayList<CapRecord>();
		Statement stmtu=conn1u.createStatement(); 
		ResultSet rsuL=stmtu.executeQuery("select * from (select * from LARGECAPS order by DIFF) where ROWNUM <=3 and PRICE <="+lb);
		System.out.println("\nLARGE CAP RECOMMENDED STOCKS :");
		while(rsuL.next())
		 {
			CapRecord singlecap=new CapRecord();
			singlecap.setCap("large");
			singlecap.setBudget(lb);
			singlecap.setCompany(rsuL.getString(1));
			singlecap.setPrice(rsuL.getFloat(2));
			records.add(singlecap);
			System.out.println("\n"+rsuL.getString(1)+"\t"+rsuL.getFloat(2));
		 }
        //Statement stmtu=conn1u.createStatement(); 
		ResultSet rsuM=stmtu.executeQuery("select * from (select * from MIDCAPS order by DIFF) where ROWNUM <=3 and PRICE <="+mb);
		System.out.println("\nMIDCAP CAP RECOMMENDED STOCKS :");
		while(rsuM.next())
		 {
			CapRecord singlecap=new CapRecord();
			singlecap.setCap("mid");
			singlecap.setBudget(mb);
			singlecap.setCompany(rsuM.getString("SYMBOL"));
			singlecap.setPrice(rsuM.getFloat("PRICE"));
			records.add(singlecap);
			//System.out.println("\n"+rsuM.getString(1)+"\t"+rsuM.getFloat(2));
		 }
		//Statement stmtu=conn1u.createStatement(); 
		ResultSet rsuS=stmtu.executeQuery("select * from (select * from SMALLCAPS order by DIFF) where ROWNUM <=3 and PRICE <="+sb);
		System.out.println("\nSMALL CAP RECOMMENDED STOCKS :");
		while(rsuS.next())
		 {
			CapRecord singlecap=new CapRecord();
			singlecap.setCap("small");
			singlecap.setBudget(sb);
			singlecap.setCompany(rsuS.getString(1));
			singlecap.setPrice(rsuS.getFloat(2));
			records.add(singlecap);
			System.out.println("\n"+rsuS.getString(1)+"\t"+rsuS.getFloat(2));
		 }
		
		for(int i=0;i<records.size();i++)
		{
			System.out.println(records.get(i).getPrice());
		}
		return records;
	}

}

//select * from ( select foo , bar from yourtable order by foo desc ) where rownum <= 10
