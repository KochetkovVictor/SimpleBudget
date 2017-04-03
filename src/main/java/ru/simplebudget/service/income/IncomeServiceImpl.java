package ru.simplebudget.service.income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.repository.income.IncomeRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    @Qualifier(value = "incomeRepositoryImpl")
    private
    IncomeRepository repository;

    @Override
    public Income addIncome(Income income) {
        return repository.addIncome(income);
    }

    @Override
    public List<Income> getIncomesPerAPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.getIncomesPerAPeriod(startDate, endDate);
    }

    @Override
    public List<Income> getAll() {
        return repository.getAll();
    }

    @Override
    public Income getById(Long id) {
        return repository.getIncome(id);
    }

    @Override
    public Income changeIncome(Income income) {
        return repository.changeIncome(income);
    }

    /*@Override
    public Income showIncomeDetails(Long incomeId) {
        return repository.getIncome(incomeId);
    }*/
}