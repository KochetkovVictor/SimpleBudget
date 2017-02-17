package ru.simplebudget.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.simplebudget.model.out.Receipt;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public class CheckRepositoryImpl implements CheckRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Receipt save(Receipt receipt) {
        if (receipt.getCheckId() == null) {
            em.persist(receipt);
            return receipt;
        } else {
            return em.merge(receipt);
        }

    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Receipt get(int id) {
        return null;
    }

    @Override
    public List<Receipt> getByPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return null;
    }

}
