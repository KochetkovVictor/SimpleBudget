package ru.simplebudget.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.simplebudget.model.user.LoggedUser;


import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @Order(Ordered.LOWEST_PRECEDENCE)
    ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        LOG.error("Exception at request " + req.getRequestURL(), e);
        ModelAndView mav = new ModelAndView("neme");
        mav.addObject("exception", e);

        // Interceptor is not invoked, put userTo

        LoggedUser loggedUser = LoggedUser.safeGet();
        if (loggedUser != null) {
            mav.addObject("user", loggedUser.getUser());
        }
        return mav;
    }
}
