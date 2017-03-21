package ru.simplebudget.repository.purse;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.repository.purse.PurseRepository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class PurseRepositoryImpl implements PurseRepository {

    @PersistenceContext
    private
    EntityManager em;


    @Transactional
    public Purse save(Purse purse) {
        if (purse.getPurseId() == null) {
            em.persist(purse);
            em.flush();
            return purse;
        } else {
            return em.merge(purse);
        }
    }


    public Purse get(Long id) {
        return em.find(Purse.class, id);
    }


    public Double getPurseAmount(Long id) {
        Purse purse = em.find(Purse.class, id);
        return purse.getAmount();
    }


    @Transactional
    public void addPurseAmount(Long id, Double amount) {
        Purse purse = em.find(Purse.class, id);
        purse.setAmount(purse.getAmount() + amount);
        em.merge(purse);
    }

    @Transactional
    public void setPurseAmount(Long id, Double amount) {
        Purse purse = em.find(Purse.class, id);
        purse.setAmount(amount);
        em.merge(purse);
    }


    public Double getTotalAmount(List<Purse> purseList) {
        Double amount = 0.00;
        for (Purse p : purseList) {
            if (p.isActive())
                amount += p.getAmount();
        }
        return amount;
    }


    @Transactional
    public boolean deletePurse(Long id) {
        Purse purse = get(id);
        if (purse != null) {
            boolean flag = purse.isActive();
            if (!flag) {
                purse.setActive(false);
                save(purse);
                return true;
            }
        }
        return false;
    }


    @Transactional
    public boolean changeName(Long id, String newDescription) {
        Purse purse = get(id);
        if (purse != null) {
            String oldName = purse.getDescription();
            if (!oldName.equals(newDescription)) {
                purse.setDescription(newDescription);
                save(purse);
                return true;
            }
        }
        return false;
    }


    public List<Purse> getAll() {
        CriteriaQuery<Purse> cq = em.getCriteriaBuilder().createQuery(Purse.class);
        Root<Purse> root = cq.from(Purse.class);
        TypedQuery<Purse> query = em.createQuery(cq);

        return query.getResultList();
    }
}
