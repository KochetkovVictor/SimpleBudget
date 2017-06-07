package ru.simplebudget.controller.income;


import org.springframework.beans.factory.annotation.Autowired;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.model.user.LoggedUser;
import ru.simplebudget.service.income.IncomeService;
import ru.simplebudget.utils.TimeUtil;


import java.time.LocalDate;
import java.util.List;

public abstract class AbstractIncomeController {

    @Autowired
    private IncomeService service;

    public Income getIncome(Long id){return service.getById(id, LoggedUser.id());}
    public void deleteIncome(Long id) {service.deleteIncome(id, LoggedUser.id());}
    public List<Income> getAll() {
        return service.getAll(LoggedUser.id());
    }

    void addIncome(Income income) {
        income.setId(null);
        service.addIncome(income,LoggedUser.id());
    }

    public List<Income> getByPeriod(LocalDate startDate, LocalDate endDate) {
        return
                service.getIncomesPerAPeriod(LoggedUser.id(), startDate==null? TimeUtil.MIN_DATE:startDate, endDate==null? TimeUtil.MAX_DATE:endDate);
    }

    void updateIncome(Income income) {
        service.changeIncome(income, LoggedUser.id());
    }
}
