package ru.simplebudget.controller.purse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.simplebudget.service.purse.PurseServiceImpl;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value="/purses")
public class PurseController {

    @Autowired
    private
    PurseServiceImpl service;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model)
    {
        model.addAttribute("purseList",service.getAll());
        return "purseList";
    }
}
