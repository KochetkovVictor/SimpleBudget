package ru.simplebudget.repository.income;

import ru.simplebudget.model.in.Income;
import java.time.LocalDate;
import java.util.List;

public interface IncomeRepository {

    Income addIncome(Income income, Long userId);
    List<Income> getIncomesPerAPeriod(LocalDate startDate, LocalDate endDate, Long userId);
    Income getIncome(Long incomeId, Long userId);
    List<Income> getAll(Long userId);
    Income changeIncome(Income income, Long userId);
    void delete(Long id, Long userId);
}
