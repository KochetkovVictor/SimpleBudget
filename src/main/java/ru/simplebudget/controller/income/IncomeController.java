package ru.simplebudget.controller.income;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.service.purse.PurseService;

import java.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/ajax/incomes")
public class IncomeController extends AbstractIncomeController {

    @Autowired
    private PurseService purseService;

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
    public void updateOrCreate(Income income) {
        if (income.getId() == 0L) {

            super.addIncome(income);
        } else {
            super.updateIncome(income);
        }
    }

    @RequestMapping(value = "/amount/pursesList", method = RequestMethod.GET)
    public List<Purse> purses() {
        return purseService.getAll().stream().filter(Purse::isActive).collect(Collectors.toList());
    }
}

