package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutClass {
@RequestMapping("/Logout")
public String logout(HttpServletRequest request)
{
	HttpSession session=request.getSession();
	session.invalidate();
	return "hello.html";
	
}

}
