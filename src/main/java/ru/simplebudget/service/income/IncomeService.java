package ru.simplebudget.service.income;

import ru.simplebudget.model.in.Income;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface IncomeService {

    Income addIncome(Income income);
    List<Income> getIncomesPerAPeriod(LocalDate startDate, LocalDate endDate);
    List<Income> getAll();
    Income getById(Long id);
    Income changeIncome(Income income);
    void deleteIncome(Long id);
    //Income showIncomeDetails(Long incomeId);
}
