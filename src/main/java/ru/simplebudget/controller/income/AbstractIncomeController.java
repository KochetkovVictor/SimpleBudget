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

public abstract class AbstractIncomeController {

    private final IncomeService incomeService;
    private final PurseService purseService;
    private final UserService userService;

    @Autowired
    public AbstractIncomeController(IncomeService incomeService, PurseService purseService, UserService userService) {
        this.incomeService = incomeService;
        this.purseService = purseService;
        this.userService = userService;
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
        User user = userService.getById(LoggedUser.id());
        Purse purse = purseService.getById(purseId, LoggedUser.id());


        if (income.getId() == 0) {
            income.setId(null);

        } else {
            Income oldIncome=getById(income.getId());
            Purse oldPurse = oldIncome.getPurse();
            if (!oldPurse.getId().equals(purseId)) {
                oldPurse.setAmount(oldPurse.getAmount()-oldIncome.getValue());
                purseService.saveOrUpdate(oldPurse, LoggedUser.id());
            }
        }
        income.setUser(user);
        income.setPurse(purse);
        purse.setAmount(purse.getAmount() + income.getValue());
        purse.getIncomes().add(income);
        purseService.saveOrUpdate(purse, LoggedUser.id());

        return income;
    }

    public void delete(Long id) {
        incomeService.delete(id, LoggedUser.id());
    }


}
