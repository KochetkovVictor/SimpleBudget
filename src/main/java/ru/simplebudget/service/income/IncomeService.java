package ru.simplebudget.service.income;

import ru.simplebudget.model.in.Income;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface IncomeService {

    Income addIncome(Income income, Long userId);
    List<Income> getIncomesPerAPeriod(Long userId, LocalDate startDate, LocalDate endDate);
    List<Income> getAll(Long userId);
    Income getById(Long id, Long userId);
    Income changeIncome(Income income, Long userId);
    void deleteIncome(Long id, Long userId);
}
