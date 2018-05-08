package com.example.demo;


import java.util.*;
import java.io.IOException;
import java.sql.ResultSet;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController{

	HistoryDAO h=new HistoryDAO();
	@Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
        this.jdbcTemplate = jdbcTemplate;  
    } 
    
		@RequestMapping(value="/LoginController", method=RequestMethod.POST)
		public String addUser(@RequestParam String username,@RequestParam String password,HttpServletRequest req) throws IOException
		{
			System.out.println("Hii");
			String query="select * from PERSON where USERNAME='"+username+"'";
			int f=0;
			 List<Map<String, Object>> list = jdbcTemplate.queryForList(query);
			    for (Map<String, Object> row : list) {
			        System.out.println(row.get("USERNAME"));
			        if(password.equals(row.get("PASS")) && username.equals(row.get("USERNAME")))
			        {
			        	System.out.println("autheticated");
			        	req.setAttribute(username, username);
			        	//h.historyDB(username);
			        	HttpSession session=req.getSession();
			        	f=1;
			        	session.setAttribute("username", username);
			        	System.out.println("log"+session.getAttribute("username"));
			        	return "redirect:dashboard.html";
			        }
			       else
			        {
			        	/*System.out.println("Add right password");
			        	return "redirect:error.html";*/
			    	   //break;
			        }
					
			    }   
			    if(f==0)
			    {
			    	return "redirect:/error2.html";
			    }
	        	
			   return "redirect:/error2.html";
			    
			
		}
	
}

