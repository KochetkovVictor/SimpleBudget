package ru.simplebudget.controller.income;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.model.user.LoggedUser;
import ru.simplebudget.service.income.IncomeService;
import ru.simplebudget.service.purse.PurseService;

import java.time.LocalDate;

import java.util.List;


@RestController
@RequestMapping("/ajax/incomes")
public class AjaxIncomeController extends AbstractIncomeController {

    private final PurseService purseService;

    @Autowired
    public AjaxIncomeController(IncomeService service, PurseService purseService) {
        super(service);
        this.purseService = purseService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Income> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Income getIncome(@PathVariable("id") Long id) {
        return super.getIncome(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteIncome(@PathVariable("id") Long id) {
        super.deleteIncome(id);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Income> getByPeriod(@RequestParam(value = "startDate", required = false) LocalDate startDate,
                                    @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return super.getByPeriod(startDate, endDate);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateOrCreate(@RequestParam (value = "incomeDate") LocalDate date,
                               @RequestParam(value="description") String description,
                               @RequestParam(value="value")Double value,
                               @RequestParam(value="purseid")Long purseId,
                               @RequestParam(value="id")Long id) {
        Income income=new Income();
        income.setId(id);
        income.setPurse(purseService.getById(purseId, LoggedUser.id()));
        income.setValue(value==null? 0.0:value);
        income.setDescription(description);
        income.setIncomeDate(date==null? LocalDate.now():date);
        if (income.getId() == 0L) {

            super.add(income);
        } else {
            super.update(income);
        }
    }
}

