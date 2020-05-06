package com.johnbryce.demo;

import com.johnbryce.demo.beans.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);

		System.out.println("GO!");
		Person p1 = ctx.getBean(Person.class);
		System.out.println(p1);
		p1.setId(123);
		p1.setName("Yaakov");
		System.out.println("The id is "+p1.getId());
		System.out.println("The name is "+p1.getName());
		p1.setName("Kobi");
		System.out.println(p1);
		//p1.doSomething();

		((AnnotationConfigApplicationContext)ctx).close();
	}


}
