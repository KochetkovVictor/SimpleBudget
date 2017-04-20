package ru.simplebudget.service.receipt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simplebudget.model.out.Receipt;
import ru.simplebudget.repository.receipt.ReceiptRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService{

    @Autowired
    private ReceiptRepository receiptRepository;

    @Override
    public List<Receipt> getAll() {
        return receiptRepository.getAll();
    }

    @Override
    public Receipt getById(Long id) {
        return receiptRepository.get(id);
    }

    @Override
    public List<Receipt> getByPeriod(LocalDate startDateTime, LocalDate endDateTime) {
        return receiptRepository.getByPeriod(startDateTime,endDateTime);
    }

    @Override
    public Receipt addReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    @Override
    public Receipt changeReceipt(Receipt receipt) {
        return receiptRepository.changeReceipt(receipt);
    }

    @Override
    public void deleteReceipt(Long id) {
        receiptRepository.delete(id);
    }
}
