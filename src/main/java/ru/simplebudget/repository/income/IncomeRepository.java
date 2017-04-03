package ru.simplebudget.repository.income;

import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.in.Income;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IncomeRepository {

    Income addIncome(Income income);
    List<Income> getIncomesPerAPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime);
    Income getIncome(Long incomeId);
    List<Income> getAll();
    Income changeIncome(Income income /*Long incomeId, String description, Double value, Purse purse*/);
}
