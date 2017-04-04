package ru.simplebudget.controller.income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.service.income.IncomeService;
import ru.simplebudget.service.purse.PurseService;


import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/incomes")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;
    @Autowired
    private PurseService purseService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAll() {
        Map<String, Object> modelMap = new HashMap<>();
        List<Income> incomeList = incomeService.getAll();
        modelMap.put("incomeList", incomeList);
        Double totalAmount = 0.0;
        for (Income income : incomeList)
            totalAmount += income.getValue();
        modelMap.put("totalAmount", totalAmount);
        return new ModelAndView("incomes", modelMap);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView addOrUpdateIncome(HttpServletRequest request) {
        Income income=new Income();
        String id=request.getParameter("id");
        income.setIncomeId(id.isEmpty() ? null:Long.valueOf(request.getParameter("id")));
        income.setDescription(request.getParameter("description"));
        income.setIncomeDateTime(LocalDateTime.parse(request.getParameter("dateTime")));
        income.setValue(Double.parseDouble(request.getParameter("value")));
        income.setPurse(purseService.getById(Long.valueOf(request.getParameter("purse"))));

        if (income.getIncomeId()==null)
        {
            System.out.println("ADD AN INCOME");
            incomeService.addIncome(income);
        }
        else {
            System.out.println("CHANGE INCOME # "+income.getIncomeId());
            incomeService.changeIncome(income);
        }
        return new ModelAndView("redirect:/incomes");
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView toAddIncomePage() {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("income", new Income());
        modelMap.put("purseList", purseService.getAll().stream().filter(Purse::isActive).collect(Collectors.toList()));
        modelMap.put("action", "Add an Income");
        return new ModelAndView("incomeEdit", modelMap);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView toUpdateIncomePage(@PathVariable("id") Long id) {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("income", incomeService.getById(id));
        modelMap.put("purseList", purseService.getAll().stream().filter(Purse::isActive).collect(Collectors.toList()));
        modelMap.put("action", "Update an Income");
        return new ModelAndView("incomeEdit", modelMap);
    }
}

