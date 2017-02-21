package ru.simplebudget.repository;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.simplebudget.model.Purse;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class PurseRepositoryImpl implements PurseRepository {

    @PersistenceContext
    private
    EntityManager em;

    @Override
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

    @Override
    public Purse get(Long id) {
        return em.find(Purse.class, id);
    }

    @Override
    public Long getPurseAmount(Long id) {
        Purse purse = em.find(Purse.class, id);
        return purse.getAmount();
    }

    @Override
    @Transactional
    public void setPurseAmount(Long id, Long amount) {
        Purse purse = em.find(Purse.class, id);
        purse.setAmount(purse.getAmount() + amount);
        em.merge(purse);

    }

    @Override
    public Long getTotalAmount(List<Purse> purseList) {
        Long amount = 0L;
        for (Purse p : purseList) {
            if (p.isActive())
                amount += p.getAmount();
        }
        return amount;
    }

    @Override
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

    @Override
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
}
