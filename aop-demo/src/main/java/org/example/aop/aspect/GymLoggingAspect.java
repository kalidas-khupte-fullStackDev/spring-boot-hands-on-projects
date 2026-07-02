package org.example.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect      // 🚀 Tells Spring this class holds cross-cutting infrastructure code
@Component   // Registers it as a Spring Bean container
public class GymLoggingAspect {

    // 🎯 POINTCUT: Matches any method execution inside classes within the 'com.gym.service' package
    @Pointcut("execution(* org.example.aop.service.*.*(..))")
    public void serviceLayerMethods() {}

    // 🛡️ BEFORE ADVICE: Triggers right before any method matched by the pointcut runs
    @Before("serviceLayerMethods()")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        System.out.println("🎥 [AOP ASPECT - BEFORE]: Audit Camera tracked entry into: "
                + methodName + "() with parameters: " + Arrays.toString(methodArgs));
    }

    // 🛡️ AFTER RETURNING ADVICE: Triggers only after a method completes successfully
    @AfterReturning(pointcut = "serviceLayerMethods()", returning = "result")
    public void logAfterMethodSuccess(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("🎥 [AOP ASPECT - AFTER]: " + methodName + "() completed cleanly. Returned value: " + result);
    }

    // 🛡️ AFTER RETURNING ADVICE: Triggers only after a method completes successfully
    @After("serviceLayerMethods()")
    public void logAfterMethodExe(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("🎥 [AOP ASPECT - AFTER Exe]: " + methodName + "() finished cleanly.");
    }
}
