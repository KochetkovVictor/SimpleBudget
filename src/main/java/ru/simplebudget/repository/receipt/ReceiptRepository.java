package ru.simplebudget.repository.receipt;

import ru.simplebudget.model.common.Shop;
import ru.simplebudget.model.common.ShopNet;
import ru.simplebudget.model.out.Receipt;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReceiptRepository {

    Receipt save(Receipt receipt, Long userId);
    boolean delete(Long id, Long userId);
    Receipt get(Long id, Long userId);
    List<Receipt> getByPeriod(Long userId, LocalDate startDateTime, LocalDate endDateTime);
    Receipt getAllByShop(Long userId, Shop shop);
    Receipt getAllByShopNet(Long userId, ShopNet shopNet);
    List<Receipt> getAll(Long userId);
    Receipt changeReceipt(Receipt receipt, Long userId);

}
