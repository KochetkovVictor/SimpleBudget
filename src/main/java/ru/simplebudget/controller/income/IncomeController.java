package ru.simplebudget.controller.income;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.simplebudget.model.in.Income;
import java.util.List;


@RestController
@RequestMapping("/ajax/incomes")
public class IncomeController extends AbstractIncomeController{

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Income> getAll(){
        return  super.getAll();
    }

    /*@RequestMapping(method = RequestMethod.POST)
    public ModelAndView addOrUpdateIncome(HttpServletRequest request) {
        Income income=new Income();
        String id=request.getParameter("id");
        income.setIncomeId(id.isEmpty() ? null:Long.valueOf(request.getParameter("id")));
        income.setDescription(request.getParameter("description"));
        income.setIncomeDate(LocalDate.parse(request.getParameter("dateTime")));
        income.setValue(Double.parseDouble(request.getParameter("value")));
        income.setPurse(purseService.getById(Long.valueOf(request.getParameter("purse"))));

        if (income.getIncomeId()==null)
        {
            incomeService.addIncome(income);
        }
        else {
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
    }*/
}

