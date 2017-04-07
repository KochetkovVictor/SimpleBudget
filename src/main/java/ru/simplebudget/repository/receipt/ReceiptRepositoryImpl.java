package ru.simplebudget.repository.receipt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.common.Shop;
import ru.simplebudget.model.common.ShopNet;
import ru.simplebudget.model.out.Receipt;
import ru.simplebudget.model.out.Receipt_;
import ru.simplebudget.repository.purse.PurseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Repository
@Transactional(readOnly = true)
public class ReceiptRepositoryImpl implements ReceiptRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private PurseRepository purseRepository;

    @Transactional
    public Receipt save(Receipt receipt) {
        if (receipt.getId() == null) {
            em.persist(receipt);
            purseRepository.addPurseAmount(receipt.getPurse().getPurseId(), -receipt.getAmount());
            return receipt;
        } else {
            return em.merge(receipt);
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        Receipt receipt = get(id);
        if (receipt != null && receipt.isActive()) {
            receipt.setActive(false);
            save(receipt);
            return true;
        }
        return false;
    }

    @Override
    public Receipt get(Long id) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Receipt> criteriaQuery = cb.createQuery(Receipt.class);
        Root<Receipt> root = criteriaQuery.from(Receipt.class);

        Predicate condition = cb.equal(root.get(Receipt_.id), id);
        criteriaQuery.where(condition);
        TypedQuery<Receipt> q = em.createQuery(criteriaQuery);
        return q.getSingleResult();
    }

    @Override
    public List<Receipt> getByPeriod(LocalDate startDate, LocalDate endDate) {
        return em.createNamedQuery(Receipt.GET_BETWEEN_DATETIME, Receipt.class)
                .setParameter("startDateTime", startDate).setParameter("endDateTime", endDate).getResultList();
    }

    @Override
    public Receipt getAllByShop(Shop shop) {
        return null;
    }

    @Override
    public Receipt getAllByShopNet(ShopNet shopNet) {
        return null;
    }

    @Override
    public List<Receipt> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Receipt> cq = cb.createQuery(Receipt.class);
        Root<Receipt> root = cq.from(Receipt.class);
        Path<LocalDate> date = root.get(Receipt_.receiptDate);
        cq.orderBy(cb.asc(date));
        TypedQuery<Receipt> query = em.createQuery(cq);

        return query.getResultList();
    }

    @Override
    @Transactional
    public Receipt changeReceipt(Receipt changeReceipt) {
        Receipt receipt = em.find(Receipt.class, changeReceipt.getId());
        Double oldAmount = receipt.getAmount();
        Purse oldPurse = receipt.getPurse();
        if (!receipt.getShop().equals(changeReceipt.getShop())) {
            receipt.setShop(changeReceipt.getShop());
        }
        if (!Objects.equals(oldAmount, changeReceipt.getAmount())) {
            receipt.setAmount(changeReceipt.getAmount());
            if (Objects.equals(oldPurse.getPurseId(), changeReceipt.getPurse().getPurseId())) {
                purseRepository.addPurseAmount(changeReceipt.getPurse().getPurseId(), -changeReceipt.getAmount() - oldAmount);
            } else {
                purseRepository.addPurseAmount(oldPurse.getPurseId(), oldAmount);
                purseRepository.addPurseAmount(changeReceipt.getPurse().getPurseId(), -changeReceipt.getAmount());
                receipt.setPurse(changeReceipt.getPurse());
            }
        } else if (!Objects.equals(oldPurse.getPurseId(), changeReceipt.getPurse().getPurseId())) {
            purseRepository.addPurseAmount(oldPurse.getPurseId(), oldAmount);
            purseRepository.addPurseAmount(changeReceipt.getPurse().getPurseId(), -changeReceipt.getAmount());
            receipt.setPurse(changeReceipt.getPurse());
        }
        return em.merge(receipt);
    }

}
