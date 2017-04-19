package ru.simplebudget.controller.income;


import org.springframework.beans.factory.annotation.Autowired;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.service.income.IncomeService;


import java.time.LocalDate;
import java.util.List;

public abstract class AbstractIncomeController {

    @Autowired
    private IncomeService service;

    public Income getIncome(Long id){return service.getById(id);}
    public void deleteIncome(Long id) {service.deleteIncome(id);}
    public List<Income> getAll() {
        return service.getAll();
    }

    void addIncome(Income income) {
        income.setId(null);
        service.addIncome(income);
    }

    public List<Income> getByPeriod(LocalDate startDate, LocalDate endDate) {
        return
                service.getIncomesPerAPeriod(startDate, endDate);
    }

    void updateIncome(Income income) {
        service.changeIncome(income);
    }
}
