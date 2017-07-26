package ru.simplebudget.controller.receipt;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.simplebudget.model.out.Receipt;

import ru.simplebudget.service.purse.PurseService;
import ru.simplebudget.service.receipt.ReceiptService;
import ru.simplebudget.service.shop.ShopService;
import ru.simplebudget.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;

import java.util.List;


@RestController
@RequestMapping(value = "/ajax/receipts")
public class AjaxReceiptController extends AbstractReceiptController {

    public AjaxReceiptController(ReceiptService receiptService, PurseService purseService, ShopService shopService) {
        super(receiptService, purseService, shopService);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Receipt> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Receipt> getByPeriod(@RequestParam(value = "startDate", required = false) LocalDate startDate,
                                     @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return super.getByPeriod(startDate, endDate);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteReceipt(@PathVariable("id") Long id) {
        super.deleteReceipt(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Receipt getById(@PathVariable("id") Long id) {
        return super.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdate(@Valid Receipt receipt, BindingResult result, SessionStatus status, HttpServletRequest request) {
        if (!result.hasErrors()) {
            try {
                super.saveOrUpdate(receipt, Long.valueOf(request.getParameter("editedPurse")), Long.valueOf(request.getParameter("editedShop")));
                status.setComplete();
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("description", "exception.duplicate_description");
            }
        }
        return "redirect:receipts";
    }
}
