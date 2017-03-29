package ru.simplebudget;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.repository.income.IncomeRepository;
import ru.simplebudget.repository.receipt.ReceiptRepository;
import ru.simplebudget.repository.purse.PurseRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) {

        System.out.println("SimpleBudget v.0.0.1");
      ConfigurableApplicationContext  configurableApplicationContext =
                new ClassPathXmlApplicationContext("spring/spring-app.xml");

        //Income income=new  Income();/*income=configurableApplicationContext.getBean(Income.class);*/
        //income.setDescription("First income");
        PurseRepository pr=(PurseRepository) configurableApplicationContext.
                getBeanFactory().getBean("purseRepository");
        //income.setPurse(pr.get(107L));
        //income.setValue(500.55);
        IncomeRepository ir=(IncomeRepository) configurableApplicationContext.
                getBeanFactory().getBean("incomeRepository");
        //System.out.println(ir.addIncome(income));
      /*  Receipt bean = configurableApplicationContext.getBean(Receipt.class);
        //bean.setId(1L);
        bean.setAmount(300L);
        bean.setDateTime(LocalDateTime.now());
        List<Product> products = new ArrayList<>();
        products.add(new Product("Chocolate", 300L));
        products.add(new Product("Milk", 60L));
      //  bean.setProducts(products);
        ReceiptRepository cp = (ReceiptRepository) configurableApplicationContext.getBeanFactory().getBean("checkRepository");
        cp.save(bean);

        Purse purse = new Purse();

        purse.setAmount(4000L);
        purse.setDescription("First test Purse");
        PurseRepository purseRepository = (PurseRepository) configurableApplicationContext.getBeanFactory().getBean("purseRepository");
        System.out.println((purseRepository.save(purse)));

       ReceiptRepository cp = (ReceiptRepository) configurableApplicationContext.getBeanFactory().getBean("checkRepository");
       List<Receipt> receiptList=cp.getByPeriod(LocalDateTime.of(2017, Month.FEBRUARY,1,11,0), LocalDateTime.now());
        for (Receipt r:receiptList
             ) {
            System.out.println(r.getAmount()+ "    " +r.getDateTime());
        }
        PurseRepository pr=(PurseRepository) configurableApplicationContext.getBeanFactory().getBean("purseRepository");

        System.out.println("*********************");
        pr.addPurseAmount(107L,40000.55);
        Purse purse=pr.get(107L);
        System.out.println("Purse Amount of " +purse.getDescription() +" is "+purse .getAmount());*/
        //System.out.println(ir.changeIncome(116L,"Forth Income", 550.50, pr.get(108L)));
        LocalDateTime start=LocalDateTime.of(2017,3,22,0,0);
        LocalDateTime end=LocalDateTime.of(2017,3,26,23,59);
        System.out.println(ir.getIncomesPerAPeriod(start,end));
        configurableApplicationContext.close();
    }
}