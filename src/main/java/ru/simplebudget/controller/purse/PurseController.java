package ru.simplebudget.controller.purse;


import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.simplebudget.exceptions.NotEnoughMoneyException;
import ru.simplebudget.model.common.Purse;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ajax/purses")
public class PurseController extends AbstractPurseController {


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Purse> getAll() {
        return super.getAll();
    }
    @RequestMapping(value = "/totalAmount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Double getTotalAmount(){
        return super.getTotalAmount();
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
