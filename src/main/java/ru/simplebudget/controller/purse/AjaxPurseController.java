package ru.simplebudget.controller.purse;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;


import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.simplebudget.exceptions.NotEnoughMoneyException;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.service.purse.PurseService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ajax/purses")
public class AjaxPurseController extends AbstractPurseController {

    public AjaxPurseController(PurseService service) {
        super(service);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Purse> getAll() {
        return super.getAll();
    }

    @RequestMapping(value="/filtered", method = RequestMethod.GET, produces = "application/json")
    public List<Purse> getFiltered() {
        return super.getFiltered();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        super.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Purse get(@PathVariable("id") Long id) {
        return super.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String savePurse(@Valid Purse purse, BindingResult result, SessionStatus status) {
        if (!result.hasErrors()) {
            try {
                super.saveOrUpdate(purse);
                status.setComplete();
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("description", "exception.duplicate_description");
            }
        }
        return "redirect:purses";
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public ModelAndView transferAmount(HttpServletRequest request) {
        try {
            super.transferAmount(Long.valueOf(request.getParameter("fromPurse")),
                    Long.valueOf(request.getParameter("toPurse")),
                    Double.valueOf(request.getParameter("transferAmount")));
        } catch (NotEnoughMoneyException neme) {
            Map<String, Object> modelMap = new HashMap<>();
            modelMap.put("exception", neme.getMessage());
            return new ModelAndView("neme", modelMap);
        }
        return new ModelAndView("redirect:/purses");
    }
}
