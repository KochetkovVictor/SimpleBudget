package ru.simplebudget;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.repository.income.IncomeRepository;
import ru.simplebudget.repository.receipt.ReceiptRepository;
import ru.simplebudget.repository.purse.PurseRepository;
import ru.simplebudget.repository.user.UserRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) {

        System.out.println("SimpleBudget v.0.0.1");
        ConfigurableApplicationContext configurableApplicationContext =
                new ClassPathXmlApplicationContext("spring/spring-app.xml");


        IncomeRepository ir = (IncomeRepository) configurableApplicationContext.
                getBeanFactory().getBean("incomeRepository");
        /*ir.getAll().forEach(System.out::println);
        PurseRepository pr=(PurseRepository) configurableApplicationContext.
                getBeanFactory().getBean("purseRepository");
        pr.getAll(301L).forEach(System.out::println);
        ReceiptRepository rr =(ReceiptRepository)configurableApplicationContext.getBeanFactory().getBean("receiptRepository");
        rr.getAllByShopNet(301L,202L,
                LocalDate.of(2017, 5,10), LocalDate.of(2017, 5,17))
                .forEach(System.out::println);*/
        PurseRepository pr = (PurseRepository)configurableApplicationContext.getBean("purseRepository");
        UserRepository ur = (UserRepository)configurableApplicationContext.getBean("userRepository");
        Purse purse=pr.get(110L,302L);

        Income income=new Income();

        income.setUser(ur.getById(302L));
        income.setDescription("test income #37");
        income.setValue(-700.77);
        purse.setAmount(purse.getAmount()+income.getValue());
        income.setPurse(purse);
        pr.save(purse,302L);
        ir.addIncome(income,302L,110L);
        configurableApplicationContext.close();

    }
}