package com.web.cv.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggableAspect {

	private final Logger LOGGER = LoggerFactory.getLogger(LoggableAspect.class);

	@Pointcut("@annotation(Loggable)")
	public void executeTiming() {
	}

	@Around("executeTiming()")
	public Object logMethodTiming(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object returnValue = proceedingJoinPoint.proceed();
		long totalTime = System.currentTimeMillis() - startTime;
		StringBuilder message = new StringBuilder(" \t Method: ");
		message.append(proceedingJoinPoint.getSignature().getName());
		message.append(" \t| ");
		message.append(totalTime).append("ms");
		LOGGER.info(message.toString());
		return returnValue;
	}
	
	
	@Before("executeTiming()")
	public void logMethodCall(JoinPoint joinPoint) {
		// TODO this type of aspect is not for logging
		StringBuilder logger = new StringBuilder(" \t Method: ");
		logger.append(joinPoint.getSignature().getName());
//		LOGGER.info(logger.toString());
	}
	
	@After("executeTiming()")
	public void logAfterMethodCall(JoinPoint joinPoint) {
		StringBuilder logger = new StringBuilder(" \t Method: ");
		logger.append(joinPoint.getSignature().getName() + " \t| ");
		logger.append(joinPoint.getKind());
		LOGGER.info(logger.toString());
	}
 
}
