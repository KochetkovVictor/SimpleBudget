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
            System.out.println("pri-1");
            return purse;

        } else {
            System.out.println("pri-2");
            return em.merge(purse);}
    }

    @Override
    public Long getPurseAmount(Long id) {
        Purse purse = em.find(Purse.class, id);
        System.out.println(purse!=null);
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
