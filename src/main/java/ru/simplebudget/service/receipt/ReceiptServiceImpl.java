package ru.simplebudget.service.receipt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simplebudget.model.out.Receipt;
import ru.simplebudget.repository.receipt.ReceiptRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService{

    private final ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptServiceImpl(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    @Override
    public List<Receipt> getAll(Long userId) {
        return receiptRepository.getAll(userId);
    }

    @Override
    public Receipt getById(Long id, Long userId) {
        return receiptRepository.get(id, userId);
    }

    @Override
    public List<Receipt> getByPeriod(Long userId, LocalDate startDateTime, LocalDate endDateTime) {
        return receiptRepository.getByPeriod(userId, startDateTime,endDateTime);
    }

    @Override
    public Receipt addReceipt(Receipt receipt, Long userId) {

        return receiptRepository.save(receipt, userId);
    }

    @Override
    public Receipt changeReceipt(Receipt receipt, Long userId) {
        return receiptRepository.changeReceipt(receipt, userId);
    }

    @Override
    public void deleteReceipt(Long id, Long userId) {
        receiptRepository.delete(id, userId);
    }
}
