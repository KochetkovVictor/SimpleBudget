package ru.simplebudget.service.receipt;


import ru.simplebudget.model.out.Receipt;

import java.time.LocalDate;
import java.util.List;

public interface ReceiptService {

    List<Receipt> getAll(Long userId);
    Receipt getById(Long id, Long userId);
    List<Receipt> getByPeriod(Long userId, LocalDate startDateTime, LocalDate endDateTime);
    Receipt addReceipt(Receipt receipt, Long userId);
    Receipt changeReceipt(Receipt receipt, Long userId);
    void deleteReceipt(Long id, Long userId);
}
