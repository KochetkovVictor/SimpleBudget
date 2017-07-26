package ru.simplebudget.controller.income;


import org.springframework.beans.factory.annotation.Autowired;

import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.model.user.LoggedUser;
import ru.simplebudget.model.user.User;
import ru.simplebudget.service.income.IncomeService;
import ru.simplebudget.service.purse.PurseService;
import ru.simplebudget.service.user.UserService;
import ru.simplebudget.utils.TimeUtil;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public abstract class AbstractIncomeController {

    private final IncomeService incomeService;
    private final PurseService purseService;

    @Autowired
    public AbstractIncomeController(IncomeService incomeService, PurseService purseService) {
        this.incomeService = incomeService;
        this.purseService = purseService;
    }


    public Income getById(Long id) {
        return incomeService.getUserIncomeById(id, LoggedUser.id());
    }


    public List<Income> getAll() {
        return incomeService.getAll(LoggedUser.id());
    }

    public List<Income> getByPeriod(LocalDate startDate, LocalDate endDate) {
        return
                incomeService.getUserIncomesPerAPeriod(LoggedUser.id(), startDate == null ? TimeUtil.MIN_DATE : startDate, endDate == null ? TimeUtil.MAX_DATE : endDate);
    }

    Income saveOrUpdate(Income income, Long purseId) {
        Purse purse = purseService.getById(purseId, LoggedUser.id());

        if (income.getId() == 0)// add new income
        {
            income.setId(null);
            purse.setAmount(purse.getAmount() + income.getValue());
        } else { // update income
            Income oldIncome = getById(income.getId());
            Purse oldPurse = oldIncome.getPurse();
            if (!oldPurse.equals(purse)) { // if we want to transfer from old purse to new one
                oldPurse.setAmount(oldPurse.getAmount() - oldIncome.getValue());
                purse.setAmount(purse.getAmount() + income.getValue());
                purseService.saveOrUpdate(oldPurse, LoggedUser.id());
            } else {// if we correct the value or description of income
                purse.setAmount(purse.getAmount() - oldIncome.getValue() + income.getValue());
            }
        }
        purseService.saveOrUpdate(purse, LoggedUser.id());
        return incomeService.saveOrUpdate(income, LoggedUser.id(), purseId);
    }

    public void delete(Long id) {
        Income income = getById(id);
        Purse purse = income.getPurse();
        purse.setAmount(purse.getAmount() - income.getValue());
        purseService.saveOrUpdate(purse, LoggedUser.id());
        incomeService.delete(id, LoggedUser.id());
    }
}
