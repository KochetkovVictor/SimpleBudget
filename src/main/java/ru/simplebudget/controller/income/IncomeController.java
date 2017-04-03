package ru.simplebudget.controller.income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.service.income.IncomeService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/incomes")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @RequestMapping(method= RequestMethod.GET)
    public ModelAndView getAll()
    {
        Map<String, Object> modelMap = new HashMap<>();
        List<Income> incomeList=incomeService.getAll();
        modelMap.put("incomeList", incomeList);
        Double totalAmount=0.0;
        for(Income income:incomeList)
            totalAmount+=income.getValue();
        modelMap.put("totalAmount", totalAmount);
        return new ModelAndView("incomes", modelMap);
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ModelAndView addIncome(){
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("newIncome", new Income());
        return new ModelAndView("incomeEdit", modelMap);
    }
    @RequestMapping(value="/add", method = RequestMethod.GET)
    public ModelAndView toAddIncomePage(){
        return new ModelAndView("incomeEdit");
    }
}

