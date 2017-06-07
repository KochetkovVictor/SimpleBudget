package ru.simplebudget.controller.purse;


import org.springframework.http.MediaType;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.simplebudget.exceptions.NotEnoughMoneyException;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.service.purse.PurseService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("purseController")
@RequestMapping("/ajax/purses")
public class PurseController extends AbstractPurseController {


    public PurseController(PurseService service) {
        super(service);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Purse> getAll() {

        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        super.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
    public Purse get(@PathVariable("id") Long id) {
        return super.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateOrCreate(Purse purse)
    {
        if(purse.getId()==0L)
        {
            super.addPurse(purse);
        }
        else{
            super.update(purse);
        }
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
