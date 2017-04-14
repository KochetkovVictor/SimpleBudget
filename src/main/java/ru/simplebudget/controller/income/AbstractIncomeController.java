package ru.simplebudget.controller.income;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.service.income.IncomeService;

import java.util.List;

public abstract class AbstractIncomeController {

    @Autowired
    private IncomeService service;

    public List<Income> getAll(){
        return service.getAll();
    }


}
