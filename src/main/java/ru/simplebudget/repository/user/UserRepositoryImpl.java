package ru.simplebudget.repository.user;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.simplebudget.model.user.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;

import java.util.List;

@Repository("userRepository")
@Transactional(readOnly=true)
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private
    EntityManager em;

    @Override
    public User getById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);
        Predicate condition = cb.equal(user.get("email"), email);
        cq.where(condition);
        return em.createQuery(cq).getSingleResult();
    }

    @Override
    public User getByNickName(String nickName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);
        Predicate condition = cb.equal(user.get("nickName"), nickName);
        cq.where(condition);
        return em.createQuery(cq).getSingleResult();
    }

    @Override
    @Transactional
    public User save(User user) {
        if (user.getId() != null) {
            return em.merge(user);
        }
        else {
        em.persist(user);
        return user;
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        User user = getById(id);
        if (user != null && user.isEnabled()) {
            /*CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaDelete<User> cd = cb.createCriteriaDelete(User.class);
            Root<User> root = cd.from(User.class);
            Predicate condition = cb.equal(root.getUserIncomeById("id"), id);
            cd.where(condition);
            em.createQuery(cd).executeUpdate();*/
            user.setEnabled(false);
            save(user);
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        //Root<User> user = cq.from(User.class);
        return em.createQuery(cq).getResultList();
    }
}
