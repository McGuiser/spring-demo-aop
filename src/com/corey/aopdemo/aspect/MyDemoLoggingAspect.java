package com.corey.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
	
	// This is where we add all of our related advices for logging
	
	// Lets start with an @Before advice
	
	//@Before("execution(public void add*())")
	
	@Before("execution (* add*(com.corey.aopdemo.Account))")
	public void beforeAddAccountAdvice() {
		
		System.out.println("\n=======>>> Executing @Before advice on method");
		
	}

}
