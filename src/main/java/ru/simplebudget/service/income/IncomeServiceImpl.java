package ru.simplebudget.service.income;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.repository.income.IncomeRepository;

import java.time.LocalDate;

import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {

    private final
    IncomeRepository incomeRepository;

    @Autowired
    public IncomeServiceImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }


    @Override
    @Transactional
    public Income addIncome(Income income, Long userId) {
        return incomeRepository.addIncome(income, userId, income.getPurse().getId());
    }

    @Override
    public List<Income> getIncomesPerAPeriod(Long userId, LocalDate startDate, LocalDate endDate) {
        return incomeRepository.getIncomesPerAPeriod(startDate, endDate, userId);
    }

    @Override
    public List<Income> getAll(Long userId) {
        return incomeRepository.getAll(userId);
    }

    @Override
    public Income getById(Long id, Long userId) {
        return incomeRepository.getIncome(id, userId);
    }

    @Override
    @Transactional
    public Income changeIncome(Income income, Long userId) {

        return incomeRepository.changeIncome(income, userId);
    }

    @Override
    @Transactional
    public void deleteIncome(Long id, Long userId) {
        incomeRepository.delete(id, userId);
    }

}