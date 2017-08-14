package ru.simplebudget.repository.shop;


import org.springframework.stereotype.Repository;
import ru.simplebudget.model.common.Shop;
import ru.simplebudget.model.common.ShopNet;
import ru.simplebudget.model.common.ShopNet_;
import ru.simplebudget.model.common.Shop_;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class ShopRepositoryImpl implements ShopRepository {

    @PersistenceContext
    private
    EntityManager entityManager;

    @Override
    public List<Shop> getAll() {
        CriteriaQuery<Shop> cq = entityManager.getCriteriaBuilder().createQuery(Shop.class);
        Root<Shop> root = cq.from(Shop.class);
        TypedQuery<Shop> query = entityManager.createQuery(cq);

        return query.getResultList();
    }

    @Override
    public Shop getById(Long id) {
        return entityManager.find(Shop.class, id);
    }

    @Override
    public List<Shop> getByTemplate(String template, Long shopNetId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Shop> cq = entityManager.getCriteriaBuilder().createQuery(Shop.class);

        Root<Shop> root = cq.from(Shop.class);
        Path<ShopNet> shopNetPath = root.get(Shop_.netName);
        cq.where(cb.and(
                cb.equal(shopNetPath.get("id"), shopNetId),
                cb.like(root.get(Shop_.name),
                        template.substring(0, 1).toUpperCase() + template.substring(1, template.length()) + "%")
                )
        );
        cq.orderBy(cb.asc(root.get(Shop_.name)));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Shop> getByShopNet(Long shopNetId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Shop> cq = entityManager.getCriteriaBuilder().createQuery(Shop.class);

        Root<Shop> root = cq.from(Shop.class);
        Path<ShopNet> shopNetPath = root.get(Shop_.netName);
        cq.where(cb.equal(shopNetPath.get("id"), shopNetId));
        cq.orderBy(cb.asc(root.get(Shop_.name)));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public Shop saveOrUpdate(Shop shop) {
        if (shop.getId() == null) {
            entityManager.persist(shop);
            return shop;
        } else
            return entityManager.merge(shop);
    }

    @Override
    public boolean delete(Long id) {
        Shop shop = getById(id);
        if (shop != null) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaDelete<Shop> cd = cb.createCriteriaDelete(Shop.class);
            Root<Shop> root = cd.from(Shop.class);
            Predicate condition = cb.equal(root.get("id"), id);
            cd.where(condition);
            entityManager.createQuery(cd).executeUpdate();
            return true;
        }
        return false;
    }
}
