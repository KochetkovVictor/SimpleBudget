package ru.simplebudget.controller.receipt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.simplebudget.model.out.Receipt;
import ru.simplebudget.service.receipt.ReceiptService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "receipts")
public class ReceiptController {

    @Autowired
    private
    ReceiptService service;

    @RequestMapping(method= RequestMethod.GET)
    public ModelAndView getAll()
    {
        Map<String, Object> modelMap = new HashMap<>();
        List<Receipt> receiptList=service.getAll();
        modelMap.put("receiptList", receiptList);
        Double totalAmount=0.0;
        for(Receipt receipt:receiptList)
            totalAmount+=receipt.getAmount();
        modelMap.put("totalAmount", totalAmount);
        return new ModelAndView("receipts", modelMap);
    }
}
