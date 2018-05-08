package com.DB;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BaseClass1 {

	public  void updateDB() throws InterruptedException, SQLException 
	{
		ExecutorService executor=Executors.newFixedThreadPool(10);
		Runnable worker1=new LargeCap();
		executor.execute(worker1);
		Runnable worker2=new MidCap();
		executor.execute(worker2);
		Runnable worker3=new SmallCap();
		executor.execute(worker3);
		executor.shutdown();
		executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		
		// TODO Auto-generated method stub
/*		Thread t1 = new Thread(new LargeCap());
		t1.start();
		t1.sleep(3000);
		
		Thread t2 = new Thread(new MidCap());
		t2.start();
		t2.sleep(5007);
		
		Thread t3 = new Thread(new SmallCap());
		t3.start();
		t3.sleep(7016);
		System.out.println("Entered base class");
		LargeCap l=new LargeCap();
		MidCap m=new MidCap();
		SmallCap s=new SmallCap();
		//l.Average();
		//l.Price();
		l.diff();
		System.out.println("\nLargeCap Ended SUCCESSFULLY !");
		//m.Average();m.Price();
		m.diff();
		System.out.println("\nMidCap Ended SUCCESSFULLY !");
		//s.Average();s.Price();
		s.diff();
		System.out.println("\nSmallCap Ended SUCCESSFULY !");
	//	UserInput u = new UserInput();
	//	u.method();*/
	}

}
