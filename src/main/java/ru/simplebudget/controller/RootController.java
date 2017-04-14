package ru.simplebudget.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RootController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
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
        return "amount";
    }
}
