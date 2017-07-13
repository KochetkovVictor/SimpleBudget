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
    public List<Income> getAll(Long userId) {
        return incomeRepository.getAllByUser(userId);
    }

    @Override
    public Income getUserIncomeById(Long id, Long userId) {
        return incomeRepository.getUserIncomeById(id, userId);
    }

    @Override
    public List<Income> getUserIncomesPerAPeriod(Long userId, LocalDate startDate, LocalDate endDate) {
        return incomeRepository.getUserIncomesPerAPeriod(startDate, endDate, userId);
    }

    @Override
    @Transactional
    public Income saveOrUpdate(Income income, Long userId, Long purseId) {
        return incomeRepository.saveOrUpdate(income, userId, purseId);
    }

    @Override
    @Transactional
    public void delete(Long id, Long userId) {
        incomeRepository.delete(id, userId);
    }
}