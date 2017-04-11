package ru.simplebudget.controller.purse;

import org.springframework.beans.factory.annotation.Autowired;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.to.AmountWrapper;
import ru.simplebudget.service.purse.PurseService;

import java.util.List;


public abstract class AbstractPurseController {

    @Autowired
    private PurseService service;

    List<Purse> getAll(){
        return service.getAll();
    }
    AmountWrapper getTotalAmount(){return new AmountWrapper(service.getTotalAmount());}
    void transferAmount(Long from, Long to, Double amount){service.transferAmount(from,to,amount);}


}
