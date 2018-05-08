package com.example.demo;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Servlet implementation class Recommend
 */
@RestController
public class Recommend {
	LoginController l;
	String username;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Recommend() {
        super();
    }

	@RequestMapping(value="/Recommend", method=RequestMethod.POST)
//	public Map<java.lang.String, Object> String(@RequestParam("amount") String amount,@RequestParam("radio") String radiovalue)
//	{
//		
//		System.out.println(amount+" "+radiovalue);
//	    Map<String,Object> model = new HashMap<String,Object>();
//
//		 model.put("id", UUID.randomUUID().toString());
//		    model.put("content", "Hello World");
//		    return model;
//		//return "pages/recommend.html";
//		
//	}
	public String postResult(@RequestBody RecommendBean bean)
	{
		return bean.getAmount();
	}
	

}
