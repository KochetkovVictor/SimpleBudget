package ru.simplebudget.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.simplebudget.model.out.Check;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public class CheckRepositoryImpl implements CheckRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Check save(Check check) {
        if (check.getCheckId() == null) {
            em.persist(check);
            return check;
        } else {
            return em.merge(check);
        }

    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Check get(int id) {
        return null;
    }

    @Override
    public List<Check> getByPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return null;
    }

}
