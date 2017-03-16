package ru.simplebudget.controller.purse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.service.purse.PurseServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="/")
public class PurseController {

    @Autowired
    private
    PurseServiceImpl service;

    @RequestMapping(method = RequestMethod.GET)
    public /*String*/ ModelAndView getAll(/*Model model*/)
    {
        //model.addAttribute("purseList",service.getAll());
        //ModelAndView mav=new ModelAndView("index", "totalAmount", service.getTotalAmount());
       // mav.addObjects()new ModelAndView("index","purseList",service.getAll());
       /* model.addAttribute("purseList",service.getAll());
        model.addAttribute("totalAmount",service.getTotalAmount());*/
        Map<String, Object> modelMap=new HashMap<>();
        modelMap.put("purseList", service.getAll());
        modelMap.put("totalAmount", service.getTotalAmount());

        return /*"index"*/new ModelAndView("index", modelMap);
    }
}
