package ru.simplebudget.controller.income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.model.user.LoggedUser;
import ru.simplebudget.service.income.IncomeService;
import ru.simplebudget.service.purse.PurseService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/incomes")
public class IncomeController extends AbstractIncomeController {

    private final PurseService purseService;
    @Autowired
    public IncomeController(IncomeService service, PurseService purseService) {
        super(service);
        this.purseService=purseService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveIncome(@Valid Income income, BindingResult result, SessionStatus status, HttpServletRequest request) {
        if (!result.hasErrors()) {
            try {
                income.setPurse(purseService.getById(Long.valueOf(request.getParameter("purseid")), LoggedUser.id()));
                if (income.getId() == 0) {
                    super.add(income);
                } else super.update(income);
                status.setComplete();
                /*return "incomes";*/
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("description", "exception.duplicate_description");
            }
        }
        return "redirect:incomes";
    }
}
