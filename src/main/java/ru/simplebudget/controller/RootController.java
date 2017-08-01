package ru.simplebudget.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.simplebudget.model.common.Purse;

import ru.simplebudget.model.user.LoggedUser;
import ru.simplebudget.service.purse.PurseService;
import ru.simplebudget.service.shop.ShopService;


import java.util.stream.Collectors;

@Controller
public class RootController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "purses";
    }

    @RequestMapping(value = "/purses", method = RequestMethod.GET)
    public String purseList() {

        return "purses";
    }

    @RequestMapping(value = "/incomes", method = RequestMethod.GET)
    public String incomeList() {
        return "incomes";
    }

    @RequestMapping(value = "/receipts", method = RequestMethod.GET)
    public String receiptList() {
        return "receipts";
    }

    @RequestMapping("/amount")
    public String amount() {
        return "oldFiles/amount";
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
