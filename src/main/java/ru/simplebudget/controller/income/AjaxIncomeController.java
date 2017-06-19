package ru.simplebudget.controller.income;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.model.user.LoggedUser;
import ru.simplebudget.service.income.IncomeService;
import ru.simplebudget.service.purse.PurseService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
   public String saveIncome(@Valid Income income, BindingResult result, SessionStatus status, HttpServletRequest request) {
       if (!result.hasErrors()) {
           try {
               System.out.println(income);
               income.setPurse(purseService.getById(Long.valueOf(request.getParameter("purseid")), LoggedUser.id()));
               if (income.getId() == 0) {
                   super.add(income);
               } else super.update(income);
               status.setComplete();
           } catch (DataIntegrityViolationException ex) {
               result.rejectValue("description", "exception.duplicate_description");
           }
       }
       return "redirect:incomes";
   }

}

