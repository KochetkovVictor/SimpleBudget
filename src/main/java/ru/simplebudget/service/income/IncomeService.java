package ru.simplebudget.service.income;

import ru.simplebudget.model.in.Income;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface IncomeService {

    Income addIncome(Income income);
    List<Income> getIncomesPerAPeriod(LocalDateTime startDate, LocalDateTime endDate);
    List<Income> getAll();
    Income showIncomeDetails(Long incomeId);
}
