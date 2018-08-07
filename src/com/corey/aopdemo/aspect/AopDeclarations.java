package com.corey.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopDeclarations {
	
	@Pointcut("execution (* com.corey.aopdemo.dao.*.*(..))")
	public void forDaoPackage() {}
	
	// Create pointcut for getter methods
	@Pointcut("execution (* com.corey.aopdemo.dao.*.get*(..))")
	public void getter() {}
	
	// Create pointcut for setter methods
	@Pointcut("execution (* com.corey.aopdemo.dao.*.set*(..))")
	public void setter() {}
	
	// Create pointcut: include package ... exclude getter/setter
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	public void forDaoPackageNoGetterSetter() {}

}
