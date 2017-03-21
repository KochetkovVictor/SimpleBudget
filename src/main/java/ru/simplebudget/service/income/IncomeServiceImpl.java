package ru.simplebudget.service.income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.repository.income.IncomeRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService{

    @Autowired
    @Qualifier(value="incomeRepositoryImpl")
    IncomeRepository repository;

    @Override
    public Income addIncome(Income income) {
        return income;
    }

    @Override
    public List<Income> getIncomesPerAPeriod(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public Income showIncomeDetails(Long incomeId) {
        return null;
    }
}
