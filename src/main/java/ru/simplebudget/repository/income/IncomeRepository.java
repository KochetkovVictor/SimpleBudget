package ru.simplebudget.repository.income;

import ru.simplebudget.model.in.Income;

import java.time.LocalDate;
import java.util.List;

public interface IncomeRepository {

    List<Income> getUserIncomesPerAPeriod(LocalDate startDate, LocalDate endDate, Long userId);

    Income getUserIncomeById(Long incomeId, Long userId);

    List<Income> getAllByUser(Long userId);

    void delete(Long id, Long userId);

    Income saveOrUpdate(Income income, Long userId);
}
