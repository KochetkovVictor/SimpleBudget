package ru.simplebudget.repository.income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.model.in.Income_;
import ru.simplebudget.model.user.User;
import ru.simplebudget.repository.purse.PurseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Repository("incomeRepository")
@Transactional(readOnly = true)
public class IncomeRepositoryImpl implements IncomeRepository {

    @PersistenceContext
    private
    EntityManager em;

    private final
    PurseRepository purseRepository;

    @Autowired
    public IncomeRepositoryImpl(PurseRepository purseRepository) {
        this.purseRepository = purseRepository;
    }

    @Transactional
    @Override
    public Income addIncome(Income income, Long userId, Long purseId) {
        if (!income.isNew() && getIncome(income.getId(), userId) == null) {
            return null;
        }
        income.setUser(em.getReference(User.class, userId));
        income.setPurse(em.getReference(Purse.class, purseId));
        if (income.isNew()) {
            em.persist(income);
          //  purseRepository.addPurseAmount(income.getPurse().getId(), userId, income.getValue());
            return income;
        } else {
            return em.merge(income);
        }

    }

    @Override
    public List<Income> getIncomesPerAPeriod(LocalDate startDateTime, LocalDate endDateTime,Long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Income> cq = cb.createQuery(Income.class);
        Root<Income> root = cq.from(Income.class);
        Path<User> user = root.get(Income_.user);
        Path<LocalDate> date = root.get(Income_.incomeDate);
        Predicate condition =cb.and(cb.equal(user.get("id"), userId),cb.between(date, startDateTime, endDateTime));
        cq.where(condition);
        cq.orderBy(cb.asc(date));
        TypedQuery<Income> query = em.createQuery(cq);

        return query.getResultList();
    }

    @Override
    public Income getIncome(Long incomeId, Long userId) {
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Income> cq=cb.createQuery(Income.class);
        Root<Income> root = cq.from(Income.class);
        Path<User> user = root.get(Income_.user);
        Predicate condition = cb.and(cb.equal(user.get("id"), userId), cb.equal(root.get("id"), incomeId));
        cq.where(condition);
        return DataAccessUtils.singleResult(em.createQuery(cq).getResultList());
    }

    @Override
    public List<Income> getAll(Long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Income> cq = cb.createQuery(Income.class);
        Root<Income> root = cq.from(Income.class);
        Path<LocalDate> date = root.get(Income_.incomeDate);
        Path<User> user = root.get(Income_.user);
        Predicate condition = cb.equal(user.get("id"), userId);
        cq.where(condition);
        cq.orderBy(cb.asc(date));
        TypedQuery<Income> query = em.createQuery(cq);

        return query.getResultList();
    }

    @Override
    @Transactional
    public Income changeIncome(Income changeIncome, Long userId) {
        Income income = em.find(Income.class, changeIncome.getId());
        Double oldValue = income.getValue();
        Purse oldPurse = income.getPurse();
        if (!income.getDescription().equals(changeIncome.getDescription())) {
            income.setDescription(changeIncome.getDescription());
        }
        if (!Objects.equals(oldValue, changeIncome.getValue())) {
            income.setValue(changeIncome.getValue());
            if (Objects.equals(oldPurse.getId(), changeIncome.getPurse().getId())) {
                purseRepository.addPurseAmount(changeIncome.getPurse().getId(), userId, changeIncome.getValue() - oldValue);
            } else {
                purseRepository.addPurseAmount(oldPurse.getId(), userId, -oldValue);
                purseRepository.addPurseAmount(changeIncome.getPurse().getId(), userId, changeIncome.getValue());
                income.setPurse(changeIncome.getPurse());
            }
        } else if (!Objects.equals(oldPurse.getId(), changeIncome.getPurse().getId())) {
            purseRepository.addPurseAmount(oldPurse.getId(), userId, -oldValue);
            purseRepository.addPurseAmount(changeIncome.getPurse().getId(), userId, changeIncome.getValue());
            income.setPurse(changeIncome.getPurse());
        }
        return em.merge(income);
    }

    @Override
    @Transactional
    public void delete(Long id, Long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Income> cdIncome = cb.createCriteriaDelete(Income.class);
        Root<Income> root = cdIncome.from(Income.class);
        Path<User> user=root.get(Income_.user);
        cdIncome.where(cb.and(cb.equal(user.get("id"), userId),cb.equal(root.get("id"), id)));
        purseRepository.addPurseAmount(getIncome(id, userId).getPurse().getId(), userId,
                -getIncome(id, userId).getValue());
        this.em.createQuery(cdIncome).executeUpdate();
    }
}
