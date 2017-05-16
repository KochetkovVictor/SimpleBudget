package ru.simplebudget.controller.receipt;


import org.springframework.beans.factory.annotation.Autowired;
import ru.simplebudget.model.out.Receipt;
import ru.simplebudget.service.receipt.ReceiptService;
import ru.simplebudget.utils.TimeUtil;

import java.time.LocalDate;
import java.util.List;

public abstract class AbstractReceiptController {

    @Autowired
    private
    ReceiptService service;

    public List<Receipt> getAll() {
        return service.getAll(301L);
    }

    Receipt getReceipt(Long id) {
        return service.getById(id, 301L);
    }

    public void deleteReceipt(Long id) {
        service.deleteReceipt(id, 301L);
    }

    void addReceipt(Receipt receipt) {
        receipt.setId(null);
        service.addReceipt(receipt, 301L);
    }

    public List<Receipt> getByPeriod(LocalDate startDate, LocalDate endDate) {
        return
                service.getByPeriod(301L, startDate == null ? TimeUtil.MIN_DATE : startDate, endDate == null ? TimeUtil.MAX_DATE : endDate);
    }

    void updateReceipt(Receipt receipt) {
        service.changeReceipt(receipt, 301L);
    }
}
