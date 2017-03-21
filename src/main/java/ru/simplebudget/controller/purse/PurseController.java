package ru.simplebudget.controller.purse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;
import ru.simplebudget.service.purse.PurseServiceImpl;

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
}
