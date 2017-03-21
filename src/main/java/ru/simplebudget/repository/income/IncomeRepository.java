package ru.simplebudget.repository.income;

import ru.simplebudget.model.in.Income;

import java.time.LocalDate;
import java.util.List;

public interface IncomeRepository {

    Income addIncome(Income income);
    List<Income> getIncomesPerAPeriod(LocalDate startDate, LocalDate endTime);
    Income showIncomeDetails(Long incomeId);
    Income getIncome(Long incomeId);
}
