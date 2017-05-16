package ru.simplebudget.repository.purse;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.simplebudget.exceptions.NotEnoughMoneyException;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.common.Purse_;
import ru.simplebudget.model.user.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class PurseRepositoryImpl implements PurseRepository {

    @PersistenceContext
    private
    EntityManager em;


    @Transactional
    @Override
    public Purse save(Purse purse, Long userId) {
        if (purse.getId() != null && get(purse.getId(), userId) == null) {
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
    public Purse get(Long id, Long userId) {
        return em.find(Purse.class, id);
    }

    @Override
    public Double getPurseAmount(Long id, Long userId) {
        Purse purse = em.find(Purse.class, id);
        return purse.getAmount();
    }


    @Transactional
    @Override
    public void addPurseAmount(Long id, Long userId, Double amount) {
        Purse purse = em.find(Purse.class, id);
        purse.setAmount(purse.getAmount() + amount);
        em.merge(purse);
    }

    @Transactional
    @Override
    public void setPurseAmount(Long id, Long userId, Double amount) {
        Purse purse = em.find(Purse.class, id);
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
    }


    @Transactional
    @Override
    public boolean deletePurse(Long id, Long userId) {
        Purse purse = get(id, userId);
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


    @Transactional
    @Override
    public boolean changeName(Long id, Long userId, String newDescription, Double amount, boolean active) {
        Purse purse = get(id, userId);
        if (purse != null) {
            purse.setDescription(newDescription);
            purse.setActive(active);
            purse.setAmount(amount);
            save(purse, userId);
            return true;
        }
        return false;
    }

    @Override
    public List<Purse> getAll(Long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Purse> cq = cb.createQuery(Purse.class);
        Root<Purse> root = cq.from(Purse.class);
        Path<User> user=root.get(Purse_.user);
        Predicate condition=cb.equal(user.get("id"), userId);
        cq.where(condition);
        TypedQuery<Purse> query = em.createQuery(cq);

        return query.getResultList();
    }

    @Override
    @Transactional
    public void transferAmount(Long fromPurseId, Long toPurseId, Double transferAmount, Long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Purse> cq = em.getCriteriaBuilder().createQuery(Purse.class);

        Root<Purse> root = cq.from(Purse.class);

        Predicate fromCondition = cb.equal(root.get("id"), fromPurseId);
        cq.where(fromCondition);
        TypedQuery<Purse> fromQuery = em.createQuery(cq);
        Purse fromPurse = fromQuery.getSingleResult();
        if (fromPurse.getAmount() - transferAmount < 0 || fromPurse.getAmount() == 0)
            throw new NotEnoughMoneyException("Not enough money");
        else {
            fromPurse.setAmount(fromPurse.getAmount() - transferAmount);
            Predicate toCondition = cb.equal(root.get("id"), toPurseId);
            cq.where(toCondition);
            TypedQuery<Purse> toQuery = em.createQuery(cq);
            Purse toPurse = toQuery.getSingleResult();
            toPurse.setAmount(toPurse.getAmount() + transferAmount);

            em.merge(fromPurse);
            em.merge(toPurse);
        }
    }
}
