package ru.simplebudget.service.income;

import ru.simplebudget.model.in.Income;

import java.time.LocalDate;
import java.util.List;


public interface IncomeService {

    Income addIncome(Income income);
    List<Income> getIncomesPerAPeriod(LocalDate startDate, LocalDate endDate);
    Income showIncomeDetails(Long incomeId);
}
