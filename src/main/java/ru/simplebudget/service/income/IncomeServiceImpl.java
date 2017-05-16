package ru.simplebudget.service.income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.repository.income.IncomeRepository;
import ru.simplebudget.repository.purse.PurseRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private
    IncomeRepository incomeRepository;
    @Autowired
    private
    PurseRepository purseRepository;

    @Override
    public Income addIncome(Income income, Long userId) {
        return incomeRepository.addIncome(income, userId);
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
    public Income changeIncome(Income income, Long userId) {

        return incomeRepository.changeIncome(income, userId);
    }

    @Override
    public void deleteIncome(Long id, Long userId) {
        incomeRepository.delete(id, userId);
    }

}