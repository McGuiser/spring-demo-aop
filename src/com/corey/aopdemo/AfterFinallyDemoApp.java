package com.corey.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.corey.aopdemo.dao.AccountDAO;

public class AfterFinallyDemoApp {

	public static void main(String[] args) {

		// Read the spring config java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// Get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// Call method to find the accounts
		List<Account> theAccounts = null;
		
		try {
			// Add a boolean flag to simulate exceptions
			boolean tripWire = false;
			theAccountDAO.findAccounts(tripWire);
		} catch (Exception exc) {
			System.out.println("\n\nMain Program ... caught exception: " + exc);
		}
		
		// Display the accounts
		System.out.println("\n\nMain Program: AfterThrowingDemoApp");
		System.out.println("----");
		
		System.out.println(theAccounts);
		System.out.println("\n");
		
		// Close the context
		context.close();

	}

}
