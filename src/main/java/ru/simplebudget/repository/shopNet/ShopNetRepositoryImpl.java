package ru.simplebudget.repository.shopNet;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.simplebudget.model.common.ShopNet;
import ru.simplebudget.model.common.ShopNet_;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class ShopNetRepositoryImpl implements ShopNetRepository {

    @PersistenceContext
    private
    EntityManager entityManager;

    @Override
    public List<ShopNet> getAll() {
        CriteriaQuery<ShopNet> cq = entityManager.getCriteriaBuilder().createQuery(ShopNet.class);
        Root<ShopNet> root = cq.from(ShopNet.class);
        TypedQuery<ShopNet> query = entityManager.createQuery(cq);

        return query.getResultList();
    }

    @Override
    public ShopNet getById(Long id) {
        return entityManager.find(ShopNet.class, id);
    }

    @Override
    @Transactional
    public ShopNet saveOrUpdate(ShopNet shopNet) {
        if (shopNet.getId()==null)
        {
            entityManager.persist(shopNet);
            return shopNet;
        }
        return entityManager.merge(shopNet);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        ShopNet shopNet = getById(id);
        if (shopNet != null) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaDelete<ShopNet> cd = cb.createCriteriaDelete(ShopNet.class);
            Root<ShopNet> root = cd.from(ShopNet.class);
            Predicate condition = cb.equal(root.get("id"), id);
            cd.where(condition);
            entityManager.createQuery(cd).executeUpdate();
            return true;
        }
        return false;
    }

    @Override
    public List<ShopNet> getByTemplate(String template) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ShopNet> cq = entityManager.getCriteriaBuilder().createQuery(ShopNet.class);

        Root<ShopNet> root = cq.from(ShopNet.class);
        cq.where(
                cb.like(root.get(ShopNet_.name),
                        template.substring(0, 1).toUpperCase() + template.substring(1, template.length()) + "%")

        );
        cq.orderBy(cb.asc(root.get(ShopNet_.name)));
        return entityManager.createQuery(cq).getResultList();
    }
}
