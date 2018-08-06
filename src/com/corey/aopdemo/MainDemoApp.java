package com.corey.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.corey.aopdemo.dao.AccountDAO;
import com.corey.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {

		// Read the spring config java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// Get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// Get membership bean from spring container
		MembershipDAO theMembershipDAO = 
				context.getBean("membershipDAO", MembershipDAO.class);
		
		// Call the business method
		Account myAccount = new Account();
		theAccountDAO.addAccount(myAccount);
		
		// Call the membership business method
		theMembershipDAO.addNewMember();
		
		// Close the context
		context.close();

	}

}
