package com.example.demo;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.DB.CurrentStocksDB;
import com.DB.UserInput;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class Viewstocks {
	ArrayList<CapRecord> records = new ArrayList<CapRecord>(); 
	@RequestMapping(value="/getallstocks", method=RequestMethod.GET)
	public String postCustomer(){
		Gson gsonBuilder = new GsonBuilder().create();
		System.out.println("Hello");
		CurrentStocksDB cur=new CurrentStocksDB();
		try {
			records=cur.currentStocks();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*RecommendLogic logic=new RecommendLogic();
				records=logic.computeRecommnedations(bean);*/
		System.out.println(records.get(0).getPrice());
		String jsonFromJavaArrayList = gsonBuilder.toJson(records);
		System.out.println(jsonFromJavaArrayList);
		return jsonFromJavaArrayList;
		
	}
	
}
