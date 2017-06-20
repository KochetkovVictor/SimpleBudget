package ru.simplebudget;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.simplebudget.controller.purse.AjaxPurseController;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.model.user.LoggedUser;
import ru.simplebudget.model.user.User;
import ru.simplebudget.repository.income.IncomeRepository;
import ru.simplebudget.repository.receipt.ReceiptRepository;
import ru.simplebudget.repository.purse.PurseRepository;
import ru.simplebudget.repository.user.UserRepository;
import ru.simplebudget.service.purse.PurseService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {

        System.out.println("SimpleBudget v.0.0.1");
        ConfigurableApplicationContext configurableApplicationContext =
                new ClassPathXmlApplicationContext("spring/spring-app.xml");


        /*PurseService ps=(PurseService)configurableApplicationContext.getBean("purseService");

        ps.getAll(302L).forEach(System.out::println);*/
        AjaxPurseController ps=(AjaxPurseController)configurableApplicationContext.getBean("ajaxPurseController");
        System.out.println(LoggedUser.id());
        ps.getAll().forEach(System.out::println);
        configurableApplicationContext.close();

    }
}