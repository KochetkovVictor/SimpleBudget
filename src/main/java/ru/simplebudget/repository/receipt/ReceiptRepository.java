package ru.simplebudget.repository.receipt;

import ru.simplebudget.model.common.Shop;
import ru.simplebudget.model.common.ShopNet;
import ru.simplebudget.model.out.Receipt;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReceiptRepository {


    Receipt getUserReceiptById(Long id, Long userId);

    List<Receipt> getUserReceiptsByPeriod(Long userId, LocalDate startDateTime, LocalDate endDateTime);

    List<Receipt> getAllByShopId(Long userId, Long shopId);

    List<Receipt> getAllByShopNetId(Long userId, Long shopNetId, LocalDate startDate, LocalDate endDate);

    List<Receipt> getAllByUser(Long userId);

    Receipt saveOrUpdate(Receipt receipt, Long userId, Long purseId);

    boolean delete(Long id, Long userId);


}
