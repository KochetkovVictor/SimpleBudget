package ru.simplebudget.controller.income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.simplebudget.service.income.IncomeService;

@RequestMapping(value="incomes")
public class IncomeController {

    @Autowired
    IncomeService incomeService;
}
