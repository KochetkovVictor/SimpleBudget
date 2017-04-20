package ru.simplebudget.service.receipt;


import ru.simplebudget.model.out.Receipt;

import java.time.LocalDate;
import java.util.List;

public interface ReceiptService {

    List<Receipt> getAll();
    Receipt getById(Long id);
    List<Receipt> getByPeriod(LocalDate startDateTime, LocalDate endDateTime);
    Receipt addReceipt(Receipt receipt);
    Receipt changeReceipt(Receipt receipt);
    void deleteReceipt(Long id);
}
