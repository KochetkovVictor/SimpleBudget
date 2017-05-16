package ru.simplebudget.controller.purse;

import org.springframework.beans.factory.annotation.Autowired;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.to.AmountWrapper;
import ru.simplebudget.service.purse.PurseService;

import java.util.List;


public abstract class AbstractPurseController {

    @Autowired
    private PurseService service;

    List<Purse> getAll() {
        return service.getAll(301L);
    }

    Purse get(Long id) {
        return service.getById(id, 301L);
    }

    void delete(Long id) {
        service.delete(id, 301L);
    }

    void update(Purse purse) {
        service.updatePurse(purse, 301L);
    }

    void addPurse(Purse purse) {
        purse.setId(null);
        service.addPurse(purse, 301L);
    }

    void transferAmount(Long from, Long to, Double amount) {

        service.transferAmount(from, to, amount, 301L);
    }

    AmountWrapper getTotalAmount() {
        return new AmountWrapper(service.getTotalAmount(301L));
    }

}
