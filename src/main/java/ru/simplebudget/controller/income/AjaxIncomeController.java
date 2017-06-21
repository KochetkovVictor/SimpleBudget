package ru.simplebudget.controller.income;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.service.income.IncomeService;
import ru.simplebudget.service.purse.PurseService;
import ru.simplebudget.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;

import java.util.List;


@RestController
@RequestMapping("/ajax/incomes")
public class AjaxIncomeController extends AbstractIncomeController {


    public AjaxIncomeController(IncomeService incomeService, PurseService purseService, UserService userService) {
        super(incomeService, purseService, userService);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Income> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Income getById(@PathVariable("id") Long id) {
        return super.getById(id);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Income> getByPeriod(@RequestParam(value = "startDate", required = false) LocalDate startDate,
                                    @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return super.getByPeriod(startDate, endDate);
    }

   @RequestMapping(method = RequestMethod.POST)
   public String saveOrUpdate(@Valid Income income, BindingResult result, SessionStatus status, HttpServletRequest request) {
       if (!result.hasErrors()) {
           try {
               super.saveOrUpdate(income, Long.valueOf(request.getParameter("purseid")));
               status.setComplete();
           } catch (DataIntegrityViolationException ex) {
               result.rejectValue("description", "exception.duplicate_description");
           }
       }
       return "redirect:incomes";
   }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        super.delete(id);
    }
}

