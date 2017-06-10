package ru.simplebudget.controller.purse;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.user.LoggedUser;
import ru.simplebudget.service.purse.PurseService;

import javax.validation.Valid;

@RestController
@RequestMapping("/purses")
public class PurseController extends AbstractPurseController {

    public PurseController(PurseService service) {
        super(service);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String savePurse(@Valid Purse purse, BindingResult result, SessionStatus status) {
        if (!result.hasErrors()) {
            try {
                if (purse.getId() == 0) {
                    super.addPurse(purse);
                } else super.update(purse);
                status.setComplete();
                /*return "purses";*/
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("description", "exception.duplicate_description");
            }
        }
        return "redirect:purses";
    }
}
