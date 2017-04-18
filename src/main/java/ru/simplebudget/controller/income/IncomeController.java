package ru.simplebudget.controller.income;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import ru.simplebudget.model.in.Income;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/ajax/incomes")
public class IncomeController extends AbstractIncomeController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Income> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Income getIncome(@PathVariable("id") Long id){return super.getIncome(id);}

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public void deleteIncome(@PathVariable("id") Long id){
        super.deleteIncome(id);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Income> getByPeriod(@RequestParam(value = "startDate", required = false) LocalDate startDate,
                                    @RequestParam(value = "endDate",required = false) LocalDate endDate) {
        return super.getByPeriod(startDate, endDate);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateOrCreate(Income income)
    {
        if (income.getId()==0L)
        {

            super.addIncome(income);
        }
        else {updateIncome(income);}
    }
}

