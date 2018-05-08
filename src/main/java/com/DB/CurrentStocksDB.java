package com.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.demo.CapRecord;

public class CurrentStocksDB {
	public ArrayList<CapRecord> currentStocks() throws SQLException {
		Connection conn1u;
		conn1u = Oracle.odbcconn();
		ArrayList<CapRecord> records=new ArrayList<CapRecord>();
		Statement stmtu=conn1u.createStatement(); 
		ResultSet rsuL=stmtu.executeQuery("select * from LARGECAPS");
		System.out.println("\nLARGE CAP RECOMMENDED STOCKS :");
		while(rsuL.next())
		 {
			CapRecord singlecap=new CapRecord();
			singlecap.setCap("large");
			singlecap.setCompany(rsuL.getString(1));
			singlecap.setPrice(rsuL.getFloat(2));
			records.add(singlecap);
			System.out.println("\n"+rsuL.getString(1)+"\t"+rsuL.getFloat(2));
		 }
		ResultSet rsuM=stmtu.executeQuery("select * from MIDCAPS");
		while(rsuM.next())
		 {
			CapRecord singlecap=new CapRecord();
			singlecap.setCap("mid");
			singlecap.setCompany(rsuM.getString(1));
			singlecap.setPrice(rsuM.getFloat(2));
			records.add(singlecap);
			System.out.println("\n"+rsuM.getString(1)+"\t"+rsuM.getFloat(2));
		 }
		ResultSet rsuS=stmtu.executeQuery("select * from SMALLCAPS");
		while(rsuS.next())
		 {
			CapRecord singlecap=new CapRecord();
			singlecap.setCap("small");
			singlecap.setCompany(rsuS.getString(1));
			singlecap.setPrice(rsuS.getFloat(2));
			records.add(singlecap);
			System.out.println("\n"+rsuS.getString(1)+"\t"+rsuS.getFloat(2));
		 }
		return records;
		
	}


}
