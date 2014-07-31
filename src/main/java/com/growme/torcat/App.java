package com.growme.torcat;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		TestBean bean = (TestBean) context.getBean("test");
		
		bean.printMessage();
		
	}
	
}
