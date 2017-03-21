package ru.simplebudget.repository.income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.repository.purse.PurseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public class IncomeRepositoryImpl implements IncomeRepository {

    @PersistenceContext
    private
    EntityManager em;

    @Autowired
    PurseRepository purseRepository;

    @Transactional
    public Income addIncome(Income income) {
        if (income.getIncomeId() == null) {

            em.persist(income);
            em.flush();
            purseRepository.addPurseAmount(income.getPurse().getPurseId(),income.getValue());
            return income;
        } else {
            return em.merge(income);
        }
    }

    @Override
    public List<Income> getIncomesPerAPeriod(LocalDate startDate, LocalDate endTime) {
        return null;
    }

    @Override
    public Income showIncomeDetails(Long incomeId) {
        return null;
    }

    @Override
    public Income getIncome(Long incomeId) {
        return em.find(Income.class,incomeId);
    }
}
