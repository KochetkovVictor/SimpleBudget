package ru.simplebudget.repository.receipt;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.simplebudget.model.common.Shop;
import ru.simplebudget.model.out.Receipt;
import ru.simplebudget.model.out.Receipt_;
import ru.simplebudget.model.user.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public class ReceiptRepositoryImpl implements ReceiptRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Receipt getUserReceiptById(Long id, Long userId) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Receipt> criteriaQuery = cb.createQuery(Receipt.class);
        Root<Receipt> root = criteriaQuery.from(Receipt.class);
        Path<User> user = root.get(Receipt_.user);
        Predicate condition = cb.and(cb.equal(user.get("id"), userId), cb.equal(root.get(Receipt_.id), id));
        criteriaQuery.where(condition);
        return em.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public List<Receipt> getUserReceiptsByPeriod(Long userId, LocalDate startDate, LocalDate endDate) {
        return em.createNamedQuery(Receipt.GET_BETWEEN_DATETIME, Receipt.class).setParameter("userId", userId)
                .setParameter("startDateTime", startDate).setParameter("endDateTime", endDate).getResultList();
    }

    @Override
    public List<Receipt> getAllByShop(Long userId, Shop shop) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Receipt> cq = cb.createQuery(Receipt.class);
        Root<Receipt> root = cq.from(Receipt.class);
        Path<Shop> shopPath = root.get(Receipt_.shop);
        Path<User> user = root.get(Receipt_.user);
        Predicate condition = cb.and(cb.equal(user.get("id"), userId), cb.equal(shopPath.get("id"), shop.getId()));
        cq.where(condition);
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Receipt> getAllByShopNet(Long userId, Long shopNetId, LocalDate startDate, LocalDate endDate) {
        return em.createNamedQuery(Receipt.JOIN_SHOPNET_GET_BETWEEN_DATETIME, Receipt.class)
                .setParameter("userId", userId)
                .setParameter("shopNetId", shopNetId)
                .setParameter("startDateTime", startDate)
                .setParameter("endDateTime", endDate)
                .getResultList();
    }

    @Override
    public List<Receipt> getAllByUser(Long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Receipt> cq = cb.createQuery(Receipt.class);
        Root<Receipt> root = cq.from(Receipt.class);
        Path<LocalDate> date = root.get(Receipt_.receiptDate);
        Path<User> user = root.get(Receipt_.user);
        Predicate condition = cb.equal(user.get("id"), userId);
        cq.where(condition);
        cq.orderBy(cb.asc(date));
        TypedQuery<Receipt> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Receipt saveOrUpdate(Receipt receipt, Long userId) {
        if (receipt.getId() != null && getUserReceiptById(receipt.getId(), userId) == null) {
            return null;
        }
        receipt.setUser(em.getReference(User.class, userId));
        if (receipt.getId() == null) {
            em.persist(receipt);
            return receipt;
        } else {
            return em.merge(receipt);
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id, Long userId) {
        Receipt receipt = getUserReceiptById(id, userId);
        if (receipt != null && receipt.isActive()) {
            receipt.setActive(false);
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaDelete<Receipt> cd = cb.createCriteriaDelete(Receipt.class);
            Root<Receipt> root = cd.from(Receipt.class);
            Path<User> user = root.get(Receipt_.user);
            cd.where(cb.and(cb.equal(user.get("id"), userId), cb.equal(root.get("id"), id)));
            em.createQuery(cd).executeUpdate();
            return true;
        }
        return false;
    }
}