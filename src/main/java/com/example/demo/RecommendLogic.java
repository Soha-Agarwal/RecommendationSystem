package com.example.demo;

import java.util.ArrayList;

public class RecommendLogic {
	private double smallamt,midamt,largeamt;
	String risk;
	ArrayList<CapRecord> record= new ArrayList<CapRecord>();
	


	ArrayList<CapRecord> computeRecommnedations(RecommendBean bean)
	{
		double amount=Double.parseDouble(bean.getAmount());
		if(bean.getRisk().equals("low"))
		{
			smallamt=amount*0.1;
			midamt=amount*0.3;
			largeamt=amount*0.6;
			//Database connection-DAO
			CapRecord caprecord=new CapRecord(); 
			caprecord.setCompany("HDFC");
			caprecord.setPrice(1500);
			caprecord.setCap("small");
			record.add(caprecord);
			return record;
			
		}
		else if(bean.getRisk().equals("medium"))
		{
			smallamt=amount*0.4;
			midamt=amount*0.3;
			largeamt=amount*0.3;
		}
		else
		{
			smallamt=amount*0.6;
			midamt=amount*0.3;
			largeamt=amount*0.1;
		}
		return record;
	}
}
