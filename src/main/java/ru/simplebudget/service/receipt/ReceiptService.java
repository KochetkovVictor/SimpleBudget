package ru.simplebudget.service.receipt;


import ru.simplebudget.model.out.Receipt;

import java.time.LocalDate;
import java.util.List;

public interface ReceiptService {


    Receipt getUserReceiptById(Long id, Long userId);

    List<Receipt> getUserReceiptsByPeriod(Long userId, LocalDate startDateTime, LocalDate endDateTime);

    List<Receipt> getAllByUser(Long userId);

    List<Receipt> getAllByShopId(Long userId, Long shopId);

    List<Receipt> getAllByShopNetId(Long userId, Long shopNetId, LocalDate startDate, LocalDate endDate);

    Receipt saveOrUpdate(Receipt receipt, Long userId, Long purseId);

    boolean delete(Long id, Long userId);
}
