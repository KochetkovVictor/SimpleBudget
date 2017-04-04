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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Repository
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
        if (income.getIncomeId() == null) {
            em.persist(income);
            em.flush();
            purseRepository.addPurseAmount(income.getPurse().getPurseId(), income.getValue());
            return income;
        } else {
            return em.merge(income);
        }
    }

    @Override
    public List<Income> getIncomesPerAPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        CriteriaBuilder cb =em.getCriteriaBuilder();
        CriteriaQuery<Income> cq = cb.createQuery(Income.class);
        Root<Income> root = cq.from(Income.class);
        Path<LocalDateTime> date=root.get(Income_.incomeDateTime);

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
        Path<LocalDateTime> date=root.get(Income_.incomeDateTime);
        cq.orderBy(cb.asc(date));
        TypedQuery<Income> query = em.createQuery(cq);

        return query.getResultList();
    }

    @Override
    @Transactional
    public Income changeIncome(Income changeIncome) {
        Income income = em.find(Income.class, changeIncome.getIncomeId());
        Double oldValue = income.getValue();
        Purse oldPurse = income.getPurse();
        if (!income.getDescription().equals(changeIncome.getDescription())) {
            income.setDescription(changeIncome.getDescription());
        }
        if (!Objects.equals(oldValue, changeIncome.getValue())) {
            income.setValue(changeIncome.getValue());
            if (Objects.equals(oldPurse.getPurseId(), changeIncome.getPurse().getPurseId())) {
                purseRepository.addPurseAmount(changeIncome.getPurse().getPurseId(), changeIncome.getValue() - oldValue);
            } else {
                purseRepository.addPurseAmount(oldPurse.getPurseId(), -oldValue);
                purseRepository.addPurseAmount(changeIncome.getPurse().getPurseId(), changeIncome.getValue());
                income.setPurse(changeIncome.getPurse());
            }
        }else if (!Objects.equals(oldPurse.getPurseId(), changeIncome.getPurse().getPurseId())) {
            purseRepository.addPurseAmount(oldPurse.getPurseId(), -oldValue);
            purseRepository.addPurseAmount(changeIncome.getPurse().getPurseId(), changeIncome.getValue());
            income.setPurse(changeIncome.getPurse());
        }
        return em.merge(income);
    }
}
