package ru.simplebudget.controller.receipt;


import org.springframework.beans.factory.annotation.Autowired;
import ru.simplebudget.model.out.Receipt;
import ru.simplebudget.model.user.LoggedUser;
import ru.simplebudget.service.receipt.ReceiptService;
import ru.simplebudget.utils.TimeUtil;

import java.time.LocalDate;
import java.util.List;

public abstract class AbstractReceiptController {

    private final
    ReceiptService service;

    @Autowired
    public AbstractReceiptController(ReceiptService service) {
        this.service = service;
    }

    public List<Receipt> getAll() {
        return service.getAll(LoggedUser.id());
    }

    Receipt getReceipt(Long id) {
        return service.getById(id, LoggedUser.id());
    }

    public void deleteReceipt(Long id) {
        service.deleteReceipt(id, LoggedUser.id());
    }

    void addReceipt(Receipt receipt) {
        receipt.setId(null);
        service.addReceipt(receipt, LoggedUser.id());
    }

    public List<Receipt> getByPeriod(LocalDate startDate, LocalDate endDate) {
        return
                service.getByPeriod(LoggedUser.id(), startDate == null ? TimeUtil.MIN_DATE : startDate, endDate == null ? TimeUtil.MAX_DATE : endDate);
    }


}
