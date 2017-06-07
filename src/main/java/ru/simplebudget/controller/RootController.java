package ru.simplebudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.user.LoggedUser;
import ru.simplebudget.service.purse.PurseService;
import ru.simplebudget.service.shop.ShopService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class RootController {

    @Autowired
    private
    PurseService purseService;
    @Autowired
    private
    ShopService shopService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/purses", method = RequestMethod.GET)
    public String purseList(Model model) {
        //LoggedUser.safeGet();
        System.out.println("ID    =     "+LoggedUser.id());
        model.addAttribute("purseList", purseService.getAll(LoggedUser.id()));
        return "purses";
    }

    @RequestMapping(value = "/incomes", method = RequestMethod.GET)
    public String incomeList(Model model) {
        model.addAttribute("purseList", purseService.getAll(LoggedUser.id()).stream().filter(Purse::isActive).collect(Collectors.toList()));
        return "incomes";
    }

    @RequestMapping(value = "/receipts", method = RequestMethod.GET)
    public String receiptList(Model model) {
        model.addAttribute("purseList", purseService.getAll(LoggedUser.id()).stream().filter(Purse::isActive).collect(Collectors.toList()));
        model.addAttribute("shopList", shopService.getAll());
        return "receipts";
    }

    @RequestMapping("/amount")
    public String amount() {
        return "amount";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) String message) {

        model.put("error", error);
        model.put("message", message);
        return "login";
    }
}
