package ru.simplebudget.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.simplebudget.model.common.Shop;
import ru.simplebudget.model.common.ShopNet;
import ru.simplebudget.model.out.Receipt;
import ru.simplebudget.model.out.Receipt_;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public class CheckRepositoryImpl implements CheckRepository {

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
    public boolean delete(int id) {
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
    public Receipt get(int id) {

        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Receipt> criteriaQuery=cb.createQuery(Receipt.class);
        Root<Receipt> root=criteriaQuery.from(Receipt.class);

        Predicate condition = cb.equal(root.get(Receipt_.id),id);
        criteriaQuery.where(condition);
        TypedQuery<Receipt> q=em.createQuery(criteriaQuery);
        return q.getSingleResult();
        //return em.find(Receipt.class, id);
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

}
