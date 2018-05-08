package com.example.demo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.DB.BaseClass1;
import com.DB.UserInput;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
 
 
@RestController
public class RestWebController {
	ArrayList<CapRecord> records=new ArrayList<CapRecord>();

	/*List<Customer> cust = new ArrayList<Customer>();
	
	@RequestMapping(value = "/getallcustomer", method = RequestMethod.GET)
	public List<Customer> getResource(){
			return cust;
	}*/
	RecommendBean bean1=new RecommendBean();

	@RequestMapping(value = "/getallcustomer", method = RequestMethod.GET)
	public ArrayList<CapRecord> getResource(){
		RecommendLogic logic=new RecommendLogic();
		records=logic.computeRecommnedations(bean1);
System.out.println("Price is"+records.get(0).getPrice());
return records;
	}
	
/*	@RequestMapping(value="/postcustomer", method=RequestMethod.POST)
	public String postCustomer(@RequestBody Customer customer){
		cust.add(customer);
		
		return "Sucessful!";
	}*/
	
	@RequestMapping(value="/postinput", method=RequestMethod.POST)
	public String postCustomer(@RequestBody RecommendBean bean,HttpServletRequest req) throws InterruptedException{
		Gson gsonBuilder = new GsonBuilder().create();
		System.out.println("Hello");
		System.out.println(bean.getRisk());
		UserInput input=new UserInput();
		HttpSession s=req.getSession();
		System.out.println("Attribute is "+s.getAttribute("username"));
		try {
			records=input.method(bean.getRisk(),bean.getAmount());
			
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
	@RequestMapping(value="/saveData", method=RequestMethod.POST)
	public void SaveData(@RequestBody RecordBean bean,HttpServletRequest req)
	{
		System.out.println("Company is"+bean.getCompany());
		HttpSession session=req.getSession();
		HistoryDAO dao=new HistoryDAO();
		String user=(String) session.getAttribute("username");
		try {
			dao.historyDB(user,bean.getCompany(),Float.parseFloat(bean.getPrice()));
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		@RequestMapping(value="/getHistory", method=RequestMethod.GET)
		public String GetDataHistory(HttpServletRequest req) throws SQLException
		{
			System.out.println("In history");
			HttpSession session=req.getSession();
			HistoryDAO dao=new HistoryDAO();
			String user=(String) session.getAttribute("username");
			
			ArrayList<HistoryBean> records=dao.getHistory(user);
			Gson gsonBuilder = new GsonBuilder().create();
			String jsonFromJavaArrayList = gsonBuilder.toJson(records);
			System.out.println(jsonFromJavaArrayList);
			return jsonFromJavaArrayList;
			

	}
		/*@RequestMapping(value="/dashboard.html", method=RequestMethod.GET)
		public String ValidateDashboard(HttpServletRequest req)
		{
			HttpSession session=req.getSession();
			if(session.getAttribute("username")==null)
			{
				return "hello.html";
			}
			else
				return "dashboard.html";
		}
		*/
	
	
}
