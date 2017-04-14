package ru.simplebudget.controller.receipt;


import org.springframework.beans.factory.annotation.Autowired;
import ru.simplebudget.model.out.Receipt;
import ru.simplebudget.service.receipt.ReceiptService;

import java.util.List;

public class AbstractReceiptController {

    @Autowired
    private
    ReceiptService service;

    public List<Receipt> getAll(){
        return service.getAll();
    }
}
