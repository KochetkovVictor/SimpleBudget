package ru.simplebudget.repository;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.simplebudget.model.Purse;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

            return em.merge(purse);}
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
        purse.setAmount(amount);
        em.merge(purse);

    }


}
