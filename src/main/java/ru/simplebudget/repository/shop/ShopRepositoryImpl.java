package ru.simplebudget.repository.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.simplebudget.model.common.Shop;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ShopRepositoryImpl implements ShopRepository{

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
}
