package ru.simplebudget.repository.receipt;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.simplebudget.model.common.Shop;
import ru.simplebudget.model.common.ShopNet;
import ru.simplebudget.model.out.Receipt;
import ru.simplebudget.model.out.Receipt_;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public class ReceiptRepositoryImpl implements ReceiptRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Receipt save(Receipt receipt) {
        if (receipt.getId() == null) {
            em.persist(receipt);
            return receipt;
        } else {
            return em.merge(receipt);
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        Receipt receipt=get(id);
        if (receipt!=null && receipt.isActive())
        {
            receipt.setActive(false);
            save(receipt);
            return true;
        }
        return false;
    }

    @Override
    public Receipt get(Long id) {

        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Receipt> criteriaQuery=cb.createQuery(Receipt.class);
        Root<Receipt> root=criteriaQuery.from(Receipt.class);

        Predicate condition = cb.equal(root.get(Receipt_.id),id);
        criteriaQuery.where(condition);
        TypedQuery<Receipt> q=em.createQuery(criteriaQuery);
        return q.getSingleResult();
    }

    @Override
    public List<Receipt> getByPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return em.createNamedQuery(Receipt.GET_BETWEEN_DATETIME ,Receipt.class)
                .setParameter("startDateTime", startDateTime).setParameter("endDateTime",endDateTime).getResultList();
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
        CriteriaBuilder cb =em.getCriteriaBuilder();
        CriteriaQuery<Receipt> cq = cb.createQuery(Receipt.class);
        Root<Receipt> root = cq.from(Receipt.class);
        Path<LocalDateTime> date=root.get(Receipt_.dateTime);
        cq.orderBy(cb.asc(date));
        TypedQuery<Receipt> query = em.createQuery(cq);

        return query.getResultList();
    }

}
