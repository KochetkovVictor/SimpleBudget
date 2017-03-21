package ru.simplebudget.repository.income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.repository.purse.PurseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
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
    public List<Income> getIncomesPerAPeriod(LocalDate startDate, LocalDate endTime) {
        return null;
    }

    @Override
    public Income showIncomeDetails(Long incomeId) {
        return null;
    }

    @Override
    public Income getIncome(Long incomeId) {
        return em.find(Income.class, incomeId);
    }

    @Override
    @Transactional
    public Income changeIncome(Long incomeId, String description, Double value, Purse purse) {
        Income income = em.find(Income.class, incomeId);
        Double oldValue = income.getValue();
        Purse oldPurse = income.getPurse();
        if (!income.getDescription().equals(description)) {
            income.setDescription(description);
        }
        if (!Objects.equals(oldValue, value)) {
            income.setValue(value);
            if (Objects.equals(oldPurse.getPurseId(), purse.getPurseId())) {
                System.out.println("OldPurse==purse");
                purseRepository.addPurseAmount(purse.getPurseId(), value - oldValue);
            } else {
                System.out.println("OldPurse!=purse");
                purseRepository.addPurseAmount(oldPurse.getPurseId(), -oldValue);
                purseRepository.addPurseAmount(purse.getPurseId(), value);
                income.setPurse(purse);
            }
        }else if (!Objects.equals(oldPurse.getPurseId(), purse.getPurseId())) {
            System.out.println("*************************  "+Objects.equals(oldPurse, purse));
            purseRepository.addPurseAmount(oldPurse.getPurseId(), -oldValue);
            purseRepository.addPurseAmount(purse.getPurseId(), value);
            income.setPurse(purse);
        }
        return em.merge(income);
    }
}
