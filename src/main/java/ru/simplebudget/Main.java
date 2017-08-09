package ru.simplebudget;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.repository.income.IncomeRepository;
import ru.simplebudget.repository.receipt.ReceiptRepository;
import ru.simplebudget.repository.purse.PurseRepository;
import ru.simplebudget.repository.user.UserRepository;
import ru.simplebudget.utils.PasswordUtil;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) {

        System.out.println("SimpleBudget v.0.0.1");
        ConfigurableApplicationContext configurableApplicationContext =
                new ClassPathXmlApplicationContext("spring/spring-app.xml");


        System.out.println(PasswordUtil.encode("password"));
        System.out.println(PasswordUtil.encode("strpngpassword"));
        configurableApplicationContext.close();

    }
}