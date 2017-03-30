package ru.simplebudget.repository.receipt;

import ru.simplebudget.model.common.Shop;
import ru.simplebudget.model.common.ShopNet;
import ru.simplebudget.model.out.Receipt;


import java.time.LocalDateTime;
import java.util.List;

public interface ReceiptRepository {

    Receipt save(Receipt receipt);
    boolean delete(Long id);
    Receipt get(Long id);
    List<Receipt> getByPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime);
    Receipt getAllByShop(Shop shop);
    Receipt getAllByShopNet(ShopNet shopNet);
    List<Receipt> getAll();

}
