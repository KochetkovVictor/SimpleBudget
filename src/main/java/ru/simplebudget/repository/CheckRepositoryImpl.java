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
@Transactional
public class CheckRepositoryImpl implements CheckRepository {

    @PersistenceContext
    private EntityManager em;

    public Check save(Check check) {
        if (check.getCheckId() == null) {
            em.persist(check);
            System.out.println("CrI-1");
            return check;
        } else {
            System.out.println("cri-2");
            return em.merge(check);}

    }

    public boolean delete(int id) {
        return false;
    }

    public Check get(int id) {
        return null;
    }

    public List<Check> getByPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return null;
    }

    public Object save(Object o) {
        return null;
    }

    public Iterable save(Iterable iterable) {
        return null;
    }

    public Object findOne(Serializable serializable) {
        return null;
    }

    public boolean exists(Serializable serializable) {
        return false;
    }

    public Iterable findAll() {
        return null;
    }

    public Iterable findAll(Iterable iterable) {
        return null;
    }

    public long count() {
        return 0;
    }

    public void delete(Serializable serializable) {

    }

    public void delete(Object o) {

    }

    public void delete(Iterable iterable) {

    }

    public void deleteAll() {

    }
}
