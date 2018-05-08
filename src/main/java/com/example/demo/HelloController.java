package com.example.demo;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.DB.BaseClass1;

@Controller
public class HelloController {
@RequestMapping("/")
String dosome() throws InterruptedException, SQLException
{
	BaseClass1 bs=new BaseClass1();
	//bs.updateDB();
	return "hello.html";
}
}
