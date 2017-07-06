package ru.simplebudget.controller.purse;

import org.springframework.beans.factory.annotation.Autowired;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.model.user.LoggedUser;
import ru.simplebudget.service.purse.PurseService;
import java.util.List;
import java.util.Set;


public abstract class AbstractPurseController {

    private final PurseService service;

    @Autowired
    public AbstractPurseController(PurseService service) {
        this.service = service;
    }

    List<Purse> getAll() {
        return service.getAll(LoggedUser.id());
    }

    Purse get(Long id) {
        return service.getById(id, LoggedUser.id());
    }

    void delete(Long id) {
        service.delete(id, LoggedUser.id());
    }

    Purse saveOrUpdate(Purse purse) {
        if (purse.getId() == 0)
            purse.setId(null);
        else {
            System.out.println("*****");
            System.out.println(purse.getIncomes()==null);
//            purse.getIncomes().forEach(System.out::println);
            System.out.println();
            get(purse.getId()).getIncomes().forEach(System.out::println);
        purse.getIncomes().addAll(get(purse.getId()).getIncomes());}
        return service.saveOrUpdate(purse, LoggedUser.id());
    }

    void transferAmount(Long from, Long to, Double amount) {
        service.transferAmount(from, to, amount, LoggedUser.id());
    }
}
