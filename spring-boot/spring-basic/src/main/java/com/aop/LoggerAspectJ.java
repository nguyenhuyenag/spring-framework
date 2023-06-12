package com.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Các Annotation chung chủa AspectJ
 * 
 * @Before: Chạy trước khi method được thực thi
 * 
 * @After: Chạy sau khi method trả về một kết quả
 * 
 * @AfterReturning: Chạy sau khi method trả về một kết quả, lấy kết quả trả về
 * 
 * @AfterThrowing: Chạy khi method xảy ra exception
 * 
 * @Around: Gồm các tất cả các advice trên
 * 
 * @Aspect: Đánh dấu đây là 1 aspect
 */
@Aspect
public class LoggerAspectJ {

	@Before("execution(* com.aop.Hello.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		System.out.println("before method: " + joinPoint.getSignature().getName());
	}

	@After("execution(* com.aop.Hello.*(..))")
	public void logAfter(JoinPoint joinPoint) {
		System.out.println("before method: " + joinPoint.getSignature().getName());
	}

	// chỉ thực hiện log với method2 của Hello.java
	@AfterReturning(pointcut = "execution(* com.aop.Hello.method2(..))", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		System.out.println("after return method : " + joinPoint.getSignature().getName());
		System.out.println("Method returned value is : " + result);
	}

	@AfterThrowing(pointcut = "execution(* com.aop.Hello.*(..))", throwing = "error")
	public void logThrow(JoinPoint joinPoint, Throwable error) {
		System.out.println("exception in method: " + joinPoint.getSignature().getName());
		System.out.println("Exception is: " + error);

	}

}
