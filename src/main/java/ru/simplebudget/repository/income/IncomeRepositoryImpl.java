package ru.simplebudget.repository.income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.model.in.Income_;
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

    @Autowired
    private
    PurseRepository purseRepository;

    @Transactional
    public Income addIncome(Income income) {
        if (income.getId() == null) {
            em.persist(income);
            em.flush();
            purseRepository.addPurseAmount(income.getPurse().getId(), income.getValue());
            return income;
        } else {
            return em.merge(income);
        }
    }

    @Override
    public List<Income> getIncomesPerAPeriod(LocalDate startDateTime, LocalDate endDateTime) {
        CriteriaBuilder cb =em.getCriteriaBuilder();
        CriteriaQuery<Income> cq = cb.createQuery(Income.class);
        Root<Income> root = cq.from(Income.class);
        Path<LocalDate> date=root.get(Income_.incomeDate);

        Predicate condition = cb.between(date,startDateTime,endDateTime);
        cq.where(condition);
        cq.orderBy(cb.asc(date));
        TypedQuery<Income> query = em.createQuery(cq);

        return query.getResultList();
    }

    @Override
    public Income getIncome(Long incomeId) {
        return em.find(Income.class, incomeId);
    }

    @Override
    public List<Income> getAll() {
        CriteriaBuilder cb =em.getCriteriaBuilder();
        CriteriaQuery<Income> cq = cb.createQuery(Income.class);
        Root<Income> root = cq.from(Income.class);
        Path<LocalDate> date=root.get(Income_.incomeDate);
        cq.orderBy(cb.asc(date));
        TypedQuery<Income> query = em.createQuery(cq);

        return query.getResultList();
    }

    @Override
    @Transactional
    public Income changeIncome(Income changeIncome) {
        Income income = em.find(Income.class, changeIncome.getId());
        Double oldValue = income.getValue();
        Purse oldPurse = income.getPurse();
        if (!income.getDescription().equals(changeIncome.getDescription())) {
            income.setDescription(changeIncome.getDescription());
        }
        if (!Objects.equals(oldValue, changeIncome.getValue())) {
            income.setValue(changeIncome.getValue());
            if (Objects.equals(oldPurse.getId(), changeIncome.getPurse().getId())) {
                purseRepository.addPurseAmount(changeIncome.getPurse().getId(), changeIncome.getValue() - oldValue);
            } else {
                purseRepository.addPurseAmount(oldPurse.getId(), -oldValue);
                purseRepository.addPurseAmount(changeIncome.getPurse().getId(), changeIncome.getValue());
                income.setPurse(changeIncome.getPurse());
            }
        }else if (!Objects.equals(oldPurse.getId(), changeIncome.getPurse().getId())) {
            purseRepository.addPurseAmount(oldPurse.getId(), -oldValue);
            purseRepository.addPurseAmount(changeIncome.getPurse().getId(), changeIncome.getValue());
            income.setPurse(changeIncome.getPurse());
        }
        return em.merge(income);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        CriteriaBuilder cb =em.getCriteriaBuilder();
        CriteriaDelete<Income> cdIncome = cb.createCriteriaDelete(Income.class);
        Root<Income> rootIncome = cdIncome.from(Income.class);
        cdIncome.where(cb.equal(rootIncome.get("id"),id));
        purseRepository.addPurseAmount(getIncome(id).getPurse().getId(),
                -getIncome(id).getValue());
        this.em.createQuery(cdIncome).executeUpdate();
    }
}
