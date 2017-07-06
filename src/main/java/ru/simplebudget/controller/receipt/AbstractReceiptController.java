package ru.simplebudget.controller.receipt;


import org.springframework.beans.factory.annotation.Autowired;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.common.Shop;
import ru.simplebudget.model.out.Receipt;
import ru.simplebudget.model.user.LoggedUser;
import ru.simplebudget.model.user.User;
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
        purse.getReceipts().remove(receipt);
        purse.setAmount(purse.getAmount() + receipt.getAmount());
        purseService.saveOrUpdate(purse, LoggedUser.id());
    }

    public Receipt saveOrUpdate(Receipt receipt, Long purseId, Long shopId) {
        User user = userService.getById(LoggedUser.id());
        Purse purse = purseService.getById(purseId, LoggedUser.id());
        Shop shop = shopService.getById(shopId);

        if (receipt.getId() == 0) {
            receipt.setId(null);
            receipt.setUser(user);
            receipt.setShop(shop);
            receipt.setPurse(purse);
            purse.getReceipts().add(receipt);
            purse.setAmount(purse.getAmount() - receipt.getAmount());
            purseService.saveOrUpdate(purse, LoggedUser.id());

        } else {
            Receipt oldReceipt = getById(receipt.getId());
            Purse oldPurse = oldReceipt.getPurse();
            if (!oldPurse.getId().equals(purseId)) {
                oldPurse.setAmount(oldPurse.getAmount() + oldReceipt.getAmount());
                purse.setAmount(purse.getAmount() - oldReceipt.getAmount());
                receipt.setPurse(purse);
                receipt.setUser(user);
                receipt.setShop(shop);
                purse.getReceipts().add(receipt);
                oldPurse.getReceipts().remove(receipt);
                System.out.println("*** BEFORE RECEIPT CHANGING");
                purse.getIncomes().forEach(System.out::println);
                System.out.println("OLD PURSE");
                oldPurse.getIncomes().forEach(System.out::println);
                purseService.saveOrUpdate(purse, LoggedUser.id());
                purseService.saveOrUpdate(oldPurse, LoggedUser.id());
                System.out.println("*** AFTER RECEIPT CHANGING");
                purse.getIncomes().forEach(System.out::println);
                System.out.println("OLD PURSE AFTER");
                oldPurse.getIncomes().forEach(System.out::println);
            } else {
                purse.setAmount(purse.getAmount() + oldReceipt.getAmount() - receipt.getAmount());
                receipt.setPurse(purse);
                receipt.setUser(user);
                receipt.setShop(shop);
                purse.getReceipts().remove(receipt);
                purse.getReceipts().add(receipt);

                purseService.saveOrUpdate(purse, LoggedUser.id());

            }
        }
        return receipt;
    }
}
