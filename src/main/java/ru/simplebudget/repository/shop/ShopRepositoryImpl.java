package ru.simplebudget.repository.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.simplebudget.model.common.Shop;
import ru.simplebudget.model.common.Shop_;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
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

    @Override
    public List<Shop> getByTemplate(String action, String id) {
        CriteriaBuilder cb= entityManager.getCriteriaBuilder();
        CriteriaQuery<Shop> cq = entityManager.getCriteriaBuilder().createQuery(Shop.class);

        Root<Shop> root = cq.from(Shop.class);
        cq.where(cb.like(root.get(Shop_.name),"%"+id.substring(0,1).toUpperCase()+id.substring(1,id.length())+"%"));

        return entityManager.createQuery(cq).getResultList();
    }
}
