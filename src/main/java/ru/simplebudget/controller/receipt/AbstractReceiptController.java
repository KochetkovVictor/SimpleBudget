package ru.simplebudget.controller.receipt;


import org.springframework.beans.factory.annotation.Autowired;
import ru.simplebudget.utils.exceptions.NotEnoughMoneyException;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.common.Shop;
import ru.simplebudget.model.out.Receipt;
import ru.simplebudget.model.user.LoggedUser;
import ru.simplebudget.service.purse.PurseService;
import ru.simplebudget.service.receipt.ReceiptService;
import ru.simplebudget.service.shop.ShopService;
import ru.simplebudget.utils.TimeUtil;

import java.time.LocalDate;
import java.util.List;

public abstract class AbstractReceiptController {

    private final ReceiptService receiptService;
    private final PurseService purseService;

    private final ShopService shopService;

    @Autowired
    public AbstractReceiptController(ReceiptService receiptService,
                                     PurseService purseService,
                                     ShopService shopService) {
        this.receiptService = receiptService;
        this.purseService = purseService;
        this.shopService = shopService;
    }

    public List<Receipt> getAll() {
        return receiptService.getAllByUser(LoggedUser.id());
    }

    Receipt getById(Long id) {
        return receiptService.getUserReceiptById(id, LoggedUser.id());
    }

    public List<Receipt> getByPeriod(LocalDate startDate, LocalDate endDate) {
        return receiptService.getUserReceiptsByPeriod(LoggedUser.id(), startDate == null ? TimeUtil.MIN_DATE : startDate, endDate == null ? TimeUtil.MAX_DATE : endDate);
    }

    public List<Receipt> getAllByShopId(Long shopId) {
        return receiptService.getAllByShopId(LoggedUser.id(), shopId);
    }

    public void deleteReceipt(Long id) {
        Receipt receipt = getById(id);
        Purse purse = receipt.getPurse();
        purse.setAmount(purse.getAmount() + receipt.getAmount());
        purseService.saveOrUpdate(purse, LoggedUser.id());
        receiptService.delete(id, LoggedUser.id());
    }

    public Receipt saveOrUpdate(Receipt receipt, Long purseId, Long shopId) throws NotEnoughMoneyException {
        Purse purse = purseService.getById(purseId, LoggedUser.id());
        Shop shop = shopService.getById(shopId);
        if (purse.getAmount() - receipt.getAmount() < 0) {
            throw new NotEnoughMoneyException(purse + " don't have enough money");
        }
        if (receipt.getId() == 0) {
            receipt.setId(null);
            purse.setAmount(purse.getAmount() - receipt.getAmount());
        } else {
            Receipt oldReceipt = getById(receipt.getId());
            Purse oldPurse = oldReceipt.getPurse();
            if (!oldPurse.getId().equals(purseId)) {
                oldPurse.setAmount(oldPurse.getAmount() + oldReceipt.getAmount());
                purse.setAmount(purse.getAmount() - receipt.getAmount());
                purseService.saveOrUpdate(oldPurse, LoggedUser.id());
            } else {
                purse.setAmount(purse.getAmount() + oldReceipt.getAmount() - receipt.getAmount());
            }
        }
        purseService.saveOrUpdate(purse, LoggedUser.id());
        receipt.setShop(shop);
        return receiptService.saveOrUpdate(receipt, LoggedUser.id(), purseId);
    }
}
