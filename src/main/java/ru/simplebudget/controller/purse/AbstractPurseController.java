package ru.simplebudget.controller.purse;

import org.springframework.beans.factory.annotation.Autowired;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.to.AmountWrapper;
import ru.simplebudget.model.user.LoggedUser;
import ru.simplebudget.service.purse.PurseService;

import java.util.List;


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

    void update(Purse purse) {
        service.updatePurse(purse, LoggedUser.id());
    }

    void addPurse(Purse purse) {
        purse.setId(null);
        service.addPurse(purse, LoggedUser.id());
    }

    void transferAmount(Long from, Long to, Double amount) {

        service.transferAmount(from, to, amount, LoggedUser.id());
    }

    AmountWrapper getTotalAmount() {
        return new AmountWrapper(service.getTotalAmount(LoggedUser.id()));
    }

}
