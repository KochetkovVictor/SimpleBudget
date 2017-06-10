package ru.simplebudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.simplebudget.controller.user.AbstractUserController;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.user.LoggedUser;
import ru.simplebudget.model.user.User;
import ru.simplebudget.service.purse.PurseService;
import ru.simplebudget.service.shop.ShopService;
import ru.simplebudget.service.user.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class RootController{

    private final
    PurseService purseService;
    private final
    ShopService shopService;
    private final UserService userService;

    @Autowired
    public RootController(PurseService purseService, ShopService shopService, UserService userService) {
        this.purseService = purseService;
        this.shopService = shopService;
        this.userService=userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/purses", method = RequestMethod.GET)
    public String purseList(Model model) {
        //LoggedUser.safeGet();
        System.out.println("ID    =     " + LoggedUser.id());
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

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile() {
        return "register";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String updateProfile(@Valid User user, BindingResult result, SessionStatus status) {
        if (!result.hasErrors()) {
            try {
                user.setId(LoggedUser.id());
                userService.save(user);
                LoggedUser.get().update(user);
                status.setComplete();
                return "redirect:meals";
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("email", "exception.duplicate_email");
            }
        }
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("register", true);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegister(@Valid User user, BindingResult result, SessionStatus status, ModelMap model) {
        if (!result.hasErrors()) {
            try {
                userService.save(user);
                status.setComplete();
                return "redirect:login?message=app.registered";
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("email", "exception.duplicate_email");
            }
        }
        model.addAttribute("register", true);
        return "register";
    }
}
