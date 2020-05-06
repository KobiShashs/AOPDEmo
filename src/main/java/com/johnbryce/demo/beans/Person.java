package com.johnbryce.demo.beans;

import com.johnbryce.demo.aspect.Kokoriko;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope("prototype")
public class Person {
    private int id;
    private String name;

    public Person() {
    }

    @Kokoriko
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @PostConstruct
    public void init(){
        System.out.println("Right after the CTOR invoked");
    }
    @PreDestroy
    public void bye(){
        System.out.println("Just before destroying the object");
    }

    public void doSomething(){
        throw new RuntimeException("Oops...");
    }
}