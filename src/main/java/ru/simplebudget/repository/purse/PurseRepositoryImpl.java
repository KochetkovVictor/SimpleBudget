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

    @Transactional
    @Override
    public boolean deletePurse(Long id, Long userId) {
        Purse purse = getById(id, userId);
        if (purse != null) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaDelete<Purse> cd = cb.createCriteriaDelete(Purse.class);
            Root<Purse> root = cd.from(Purse.class);
            Predicate condition = cb.equal(root.get("id"), id);
            cd.where(condition);
            em.createQuery(cd).executeUpdate();
            return true;
        }
        return false;
    }

    /*@Override
    public Double getPurseAmount(Long id, Long userId) {
        Purse purse = getById(id,userId);
        return purse.getAmount();
    }


    @Transactional
    @Override
    public void addPurseAmount(Long id, Long userId, Double amount) {
        Purse purse = getById(id,userId);
        purse.setAmount(purse.getAmount() + amount);
        em.merge(purse);
    }

    @Transactional
    @Override
    public void setPurseAmount(Long id, Long userId, Double amount) {
        Purse purse = getById(id,userId);
        purse.setAmount(amount);
        em.merge(purse);
    }

    @Override
    public Double getTotalAmount(List<Purse> purseList, Long userId) {
        Double amount = 0.00;
        for (Purse p : purseList) {
            if (p.isActive())
                amount += p.getAmount();
        }
        return amount;
    }*/




   /* @Transactional
    @Override
    public boolean changeName(Long id, Long userId, String newDescription, Double amount, boolean active) {
        Purse purse = getById(id, userId);
        if (purse != null) {
            purse.setDescription(newDescription);
            purse.setActive(active);
            purse.setAmount(amount);
            save(purse, userId);
            return true;
        }
        return false;
    }*/



    /*@Override
    @Transactional
    public void transferAmount(Long fromPurseId, Long toPurseId, Double transferAmount, Long userId) {
        Purse fromPurse = getById(fromPurseId, userId);
        Purse toPurse= getById(toPurseId, userId);
        if (fromPurse.getAmount() - transferAmount < 0 || fromPurse.getAmount() == 0)
            throw new NotEnoughMoneyException("Not enough money");
        else {
            fromPurse.setAmount(fromPurse.getAmount() - transferAmount);
            toPurse.setAmount(toPurse.getAmount() + transferAmount);
            em.merge(fromPurse);
            em.merge(toPurse);
        }
    }*/
}
