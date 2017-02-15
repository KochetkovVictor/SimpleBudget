package ru.simplebudget;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.simplebudget.model.Product;
import ru.simplebudget.model.Purse;
import ru.simplebudget.model.out.Check;
import ru.simplebudget.repository.CheckRepository;
import ru.simplebudget.repository.PurseRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        System.out.println("Main class");
        ConfigurableApplicationContext configurableApplicationContext =
                new ClassPathXmlApplicationContext("spring/spring-app.xml");
        Check bean = configurableApplicationContext.getBean(Check.class);
        bean.setAmount(300L);
        bean.setDateTime(LocalDateTime.now());
        List<Product> products = new ArrayList<>();
        products.add(new Product("Chocolate", 300L));
        products.add(new Product("Milk", 60L));
        bean.setProducts(products);
        CheckRepository cp = (CheckRepository) configurableApplicationContext.getBeanFactory().getBean("checkRepository");
        cp.save(bean);
        Purse purse = new Purse();
        purse.setPurseId(1L);
        purse.setAmount(4000L);
        purse.setDescription("First test Purse");
        PurseRepository purseRepository=(PurseRepository) configurableApplicationContext.getBeanFactory().getBean("purseRepository");
        System.out.println((purseRepository.save(purse)));
        System.out.println(purseRepository.getPurseAmount(1L));
        purseRepository.setPurseAmount(1L,6000L);
        System.out.println(purseRepository.getPurseAmount(1L));
        configurableApplicationContext.close();
    }
}