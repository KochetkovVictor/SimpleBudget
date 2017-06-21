package ru.simplebudget.controller.receipt;


import org.springframework.beans.factory.annotation.Autowired;
import ru.simplebudget.model.out.Receipt;
import ru.simplebudget.model.user.LoggedUser;
import ru.simplebudget.service.purse.PurseService;
import ru.simplebudget.service.receipt.ReceiptService;
import ru.simplebudget.service.shop.ShopService;
import ru.simplebudget.service.user.UserService;
import ru.simplebudget.utils.TimeUtil;

import java.time.LocalDate;
import java.util.List;

public abstract class AbstractReceiptController {

    private final ReceiptService receiptService;
    private final PurseService purseService;
    private final UserService userService;
    private final ShopService shopService;

    @Autowired
    public AbstractReceiptController(ReceiptService receiptService,
                                     PurseService purseService,
                                     UserService userService,
                                     ShopService shopService) {
        this.receiptService = receiptService;
        this.purseService = purseService;
        this.userService = userService;
        this.shopService=shopService;
    }

    public List<Receipt> getAll() {
        return receiptService.getAll(LoggedUser.id());
    }

    Receipt getReceipt(Long id) {
        return receiptService.getById(id, LoggedUser.id());
    }

    public void deleteReceipt(Long id) {
        receiptService.deleteReceipt(id, LoggedUser.id());
    }

    void addReceipt(Receipt receipt) {
        receipt.setId(null);
        receiptService.addReceipt(receipt, LoggedUser.id());
    }

    public List<Receipt> getByPeriod(LocalDate startDate, LocalDate endDate) {
        return
                receiptService.getByPeriod(LoggedUser.id(), startDate == null ? TimeUtil.MIN_DATE : startDate, endDate == null ? TimeUtil.MAX_DATE : endDate);
    }


}
