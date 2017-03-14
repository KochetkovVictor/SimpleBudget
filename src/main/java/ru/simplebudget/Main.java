package ru.simplebudget;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.repository.CheckRepository;
import ru.simplebudget.repository.PurseRepository;


public class Main {
    public static void main(String[] args) {

        ConfigurableApplicationContext configurableApplicationContext =
                new ClassPathXmlApplicationContext("spring/spring-app.xml");
       /* Receipt bean = configurableApplicationContext.getBean(Receipt.class);
        //bean.setId(1L);
        bean.setAmount(300L);
        bean.setDateTime(LocalDateTime.now());
        List<Product> products = new ArrayList<>();
        products.add(new Product("Chocolate", 300L));
        products.add(new Product("Milk", 60L));
      //  bean.setProducts(products);
        CheckRepository cp = (CheckRepository) configurableApplicationContext.getBeanFactory().getBean("checkRepository");
        cp.save(bean);

        Purse purse = new Purse();

        purse.setAmount(4000L);
        purse.setDescription("First test Purse");
        PurseRepository purseRepository = (PurseRepository) configurableApplicationContext.getBeanFactory().getBean("purseRepository");
        System.out.println((purseRepository.save(purse)));
*/
       CheckRepository cp = (CheckRepository) configurableApplicationContext.getBeanFactory().getBean("checkRepository");
       /*List<Receipt> receiptList=cp.getByPeriod(LocalDateTime.of(2017, Month.FEBRUARY,1,11,0), LocalDateTime.now());
        for (Receipt r:receiptList
             ) {
            System.out.println(r.getAmount()+ "    " +r.getDateTime());
        }*/
        PurseRepository pr=(PurseRepository) configurableApplicationContext.getBeanFactory().getBean("purseRepository");

        System.out.println("*********************");
        pr.setPurseAmount(107L,40000L);
        Purse purse=pr.get(107L);
        System.out.println("Purse Amount of " +purse.getDescription() +" is "+purse .getAmount());
        configurableApplicationContext.close();
    }
}