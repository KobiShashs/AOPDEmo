package com.johnbryce.demo.aspect;

import com.johnbryce.demo.beans.Person;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class MyAspect {

    @Pointcut("within(com.johnbryce.demo.beans.Person)")
    public void allPersonBeanMethods() {
    }

    @Pointcut("within(com.johnbryce.MyAspect.beans.Person..*)")
    private void inPerson() {}

    @Pointcut("execution(* com.johnbryce.MyAspect.beans.Person..*(..))")
    public void allMethods(){}

    @Pointcut("execution(public * *(..))")
    private void anyPublicOperation() {}

    @Pointcut("execution(* com.johnbryce.MyAspect.beans..get*())")
    public void allGetters() {
    }

    @Pointcut("execution(* set*(..))")
    public void allSetters() {
    }



    @Before("execution(public int getId())")
    public void advice1(){
        System.out.println("advice invoked before the pointcut");
    }
    @After("execution(public int getId())")
    public void advice2(){
        System.out.println("advice invoked after the pointcut");
    }

    @Before("execution(public void setId(..))")
    public void advice3(){
        System.out.println("advice invoked before the pointcut");
    }
    @After("execution(public int getId(..))")
    public void advice4(){
        System.out.println("advice invoked after the pointcut");
    }

    @Before("execution(public String getName())")
    public void advice5(JoinPoint joinPoint) {
        System.out.println("What is JoinPoint???");
        System.out.println(joinPoint.toString());
        System.out.println(joinPoint.getTarget());
        Person p = (Person) (joinPoint.getTarget());
        System.out.println(p.getName());
    }

    @Before("@annotation(Kokoriko)")
    public void advice6(){
        System.out.println("kokorikkkkkkkkkkkkkkkkkkooooooooooooooooo");
    }

    //  returnValue
    @AfterReturning(pointcut = "allSetters()", returning = "name")
    public void getterAdvice(JoinPoint joinPoint, String name) {
        System.out.println("Returned value=" + name);
    }

    //  catch Exception
    @AfterThrowing(pointcut = "allPersonBeanMethods()", throwing = "ex")
    public void throwingAdvice(JoinPoint joinPoint, Exception ex) {
        System.out.println("Exception is=" + ex.getMessage());
    }

    @Around("allGetters()")
    public Object myAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {

        Object returnValue = null;
        try {

            System.out.println("Before Advice");
            returnValue = proceedingJoinPoint.proceed();
            System.out.println("After Advice");

        } catch (Throwable e) {
            System.out.println("After Throwing");
        }
        System.out.println("After Try/Catch block");
        return returnValue;

    }




}