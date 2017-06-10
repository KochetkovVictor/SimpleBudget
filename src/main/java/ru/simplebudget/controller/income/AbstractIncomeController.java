package ru.simplebudget.controller.income;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.model.user.LoggedUser;
import ru.simplebudget.service.income.IncomeService;
import ru.simplebudget.utils.TimeUtil;


import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

public abstract class AbstractIncomeController {

    private final IncomeService service;

    @Autowired
    public AbstractIncomeController(IncomeService service) {
        this.service = service;
    }

    public Income getIncome(Long id) {
        return service.getById(id, LoggedUser.id());
    }

    public void deleteIncome(Long id) {
        service.deleteIncome(id, LoggedUser.id());
    }

    public List<Income> getAll() {
        return service.getAll(LoggedUser.id());
    }

    void add(Income income) {
        income.setId(null);
        service.addIncome(income, LoggedUser.id());
    }

    public List<Income> getByPeriod(LocalDate startDate, LocalDate endDate) {
        return
                service.getIncomesPerAPeriod(LoggedUser.id(), startDate == null ? TimeUtil.MIN_DATE : startDate, endDate == null ? TimeUtil.MAX_DATE : endDate);
    }

    void update(Income income) {
        service.changeIncome(income, LoggedUser.id());
    }
}
