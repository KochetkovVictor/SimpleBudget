package ru.simplebudget;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.simplebudget.model.out.Check;


public class Main{
    public static void main(String[] args) {
        System.out.println("Main class");
        ConfigurableApplicationContext configurableApplicationContext=
                new ClassPathXmlApplicationContext("spring/spring-app.xml");
        Check bean= configurableApplicationContext.getBean(Check.class);
        bean.setAmount(300L);
        System.out.println(bean.getAmount());
        configurableApplicationContext.close();
    }
}