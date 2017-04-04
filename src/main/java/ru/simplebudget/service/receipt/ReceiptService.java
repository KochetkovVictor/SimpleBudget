package ru.simplebudget.service.receipt;


import ru.simplebudget.model.out.Receipt;

import java.time.LocalDateTime;
import java.util.List;

public interface ReceiptService {

    List<Receipt> getAll();
    Receipt getById(Long id);
    List<Receipt> getByPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime);
    Receipt addReceipt(Receipt receipt);
    Receipt changeReceipt(Receipt receipt);
}
