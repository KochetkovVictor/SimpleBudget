package ru.simplebudget.service.income;

import ru.simplebudget.model.in.Income;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface IncomeService {


    List<Income> getUserIncomesPerAPeriod(Long userId, LocalDate startDate, LocalDate endDate);

    List<Income> getAll(Long userId);

    void delete(Long id, Long userId);

    Income getUserIncomeById(Long id, Long userId);

    Income saveOrUpdate(Income income, Long userId);
}
