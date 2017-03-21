package ru.simplebudget.repository.receipt;

import ru.simplebudget.model.common.Shop;
import ru.simplebudget.model.common.ShopNet;
import ru.simplebudget.model.out.Receipt;


import java.time.LocalDateTime;
import java.util.List;

public interface ReceiptRepository {

    Receipt save(Receipt receipt);
    boolean delete(int id);
    Receipt get(int id);
    List<Receipt> getByPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime);
    Receipt getAllByShop(Shop shop);
    Receipt getAllByShopNet(ShopNet shopNet);

}
