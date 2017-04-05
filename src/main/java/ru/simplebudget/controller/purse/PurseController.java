package ru.simplebudget.controller.purse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;
import ru.simplebudget.exceptions.NotEnoughMoneyException;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.service.purse.PurseServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/purses")
public class PurseController {

    @Autowired
    private
    PurseServiceImpl service;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAll() {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("purseList", service.getAll());
        modelMap.put("totalAmount", service.getTotalAmount());
        return new ModelAndView("purses", modelMap);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView transferAmount(HttpServletRequest request) {
        try {
            service.transferAmount(Long.valueOf(request.getParameter("fromPurse")),
                    Long.valueOf(request.getParameter("toPurse")),
                    Double.valueOf(request.getParameter("transferAmount")));
        } catch (NotEnoughMoneyException neme)
        {
            Map<String, Object> modelMap=new HashMap<>();
            modelMap.put("exception",neme.getMessage());
            return new ModelAndView("neme",modelMap);
        }
        return new ModelAndView("redirect:/purses");
    }
}
