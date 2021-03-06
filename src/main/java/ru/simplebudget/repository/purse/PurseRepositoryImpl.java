package ru.simplebudget.repository.purse;


import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.common.Purse_;
import ru.simplebudget.model.user.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("purseRepository")
@Transactional(readOnly = true)
public class PurseRepositoryImpl implements PurseRepository {

    @PersistenceContext
    private
    EntityManager em;

    @Override
    @Transactional
    public Purse save(Purse purse, Long userId) {
        if (purse.getId() != null && getById(purse.getId(), userId) == null) {
            return null;
        }
        purse.setUser(em.getReference(User.class, userId));
        if (purse.getId() == null) {
            em.persist(purse);
            em.flush();
            return purse;
        } else {
            return em.merge(purse);
        }
    }

    @Override
    public Purse getById(Long id, Long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Purse> cq = cb.createQuery(Purse.class);
        Root<Purse> root = cq.from(Purse.class);
        Path<User> user = root.get(Purse_.user);
        Predicate condition = cb.and(cb.equal(user.get("id"), userId), cb.equal(root.get("id"), id));
        cq.where(condition);

        return DataAccessUtils.singleResult(em.createQuery(cq).getResultList());
    }

    @Transactional
    @Override
    public boolean deletePurse(Long id, Long userId) {
        Purse purse = getById(id, userId);
        if (purse != null) {
            boolean flag = purse.isActive();
            if (flag) {
                purse.setActive(false);
                save(purse, userId);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Purse> getAll(Long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Purse> cq = cb.createQuery(Purse.class);
        Root<Purse> root = cq.from(Purse.class);
        Path<User> user = root.get(Purse_.user);
        Predicate condition = cb.equal(user.get("id"), userId);
        cq.where(condition);
        return em.createQuery(cq).getResultList();
    }
}
