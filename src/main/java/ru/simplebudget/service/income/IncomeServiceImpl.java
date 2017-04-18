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
    public Income addIncome(Income income) {
        return incomeRepository.addIncome(income);
    }

    @Override
    public List<Income> getIncomesPerAPeriod(LocalDate startDate, LocalDate endDate) {
        return incomeRepository.getIncomesPerAPeriod(startDate, endDate);
    }

    @Override
    public List<Income> getAll() {
        return incomeRepository.getAll();
    }

    @Override
    public Income getById(Long id) {
        return incomeRepository.getIncome(id);
    }

    @Override
    public Income changeIncome(Income income) {
        return incomeRepository.changeIncome(income);
    }

    @Override
    public void deleteIncome(Long id) {
        incomeRepository.delete(id);
    }

}