package ru.simplebudget.service.receipt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simplebudget.model.out.Receipt;
import ru.simplebudget.repository.receipt.ReceiptRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptServiceImpl(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    @Override
    public Receipt getUserReceiptById(Long id, Long userId) {
        return receiptRepository.getUserReceiptById(id, userId);
    }

    @Override
    public List<Receipt> getUserReceiptsByPeriod(Long userId, LocalDate startDateTime, LocalDate endDateTime) {
        return receiptRepository.getUserReceiptsByPeriod(userId, startDateTime, endDateTime);
    }

    @Override
    public List<Receipt> getAllByUser(Long userId) {
        return receiptRepository.getAllByUser(userId);
    }

    @Override
    public List<Receipt> getAllByShopId(Long userId, Long shopId) {
        return receiptRepository.getAllByShopId(userId, shopId);
    }

    @Override
    public List<Receipt> getAllByShopNetId(Long userId, Long shopNetId, LocalDate startDate, LocalDate endDate) {
        return receiptRepository.getAllByShopNetId(userId, shopNetId, startDate, endDate);
    }

    @Override
    public Receipt saveOrUpdate(Receipt receipt, Long userId) {
        return receiptRepository.saveOrUpdate(receipt, userId);
    }

    @Override
    public boolean delete(Long id, Long userId) {
        return receiptRepository.delete(id, userId);
    }
}
