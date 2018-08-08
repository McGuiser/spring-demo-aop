package com.corey.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.corey.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	@After("execution(* com.corey.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		
		// Print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n=======>>> Executing @After (finally) on method: " + method);
		
	}
	
	@AfterThrowing(
			pointcut="execution(* com.corey.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing="theExc")
	public void afterThrowingFindAccountsAdvice(
					JoinPoint theJoinPoint, Throwable theExc) {
		
		// Print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n=======>>> Executing @AfterThrowing on method: " + method);
		
		// Log the exception
		System.out.println("\n=======>>> The exception is: " + theExc);
		
	}
	
	@AfterReturning(
			pointcut="execution(* com.corey.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="result")
	public void afterReturningFindAccountsAdvice(
					JoinPoint theJoinPoint, List<Account> result) {
		
		// Print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n=======>>> Executing @AfterReturning on method: " + method);
		
		// Print out the results of the method call
		System.out.println("\n=======>>> Result is: " + result);
		
		// Let's post-process the data ... let's modify it
		
		// Convert the account names to uppercase
		convertAccountNamesToUpperCase(result);
		
		System.out.println("\n=======>>> Result is: " + result);
		
	}
	
	private void convertAccountNamesToUpperCase(List<Account> result) {
		
		result.forEach(tempAccount -> 
			tempAccount.setName(tempAccount.getName().toUpperCase()));
		
	}

	@Before("com.corey.aopdemo.aspect.AopDeclarations.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		
		System.out.println("\n=======>>> Executing @Before advice on method");
		
		// Display the method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		
		System.out.println("Method: " + methodSig);
		
		// Display the method arguments
		
		Object[] args = theJoinPoint.getArgs();
		
		for (Object tempArg : args) {
			System.out.println(tempArg);
			
			if (tempArg instanceof Account) {
				
				// Downcast and print Account specific stuff
				Account theAccount = (Account) tempArg;
				
				System.out.println("Account name: " + theAccount.getName());
				System.out.println("Account level: " + theAccount.getLevel());
				
			}
		}
		
	}

}
