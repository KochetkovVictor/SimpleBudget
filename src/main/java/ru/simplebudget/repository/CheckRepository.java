package ru.simplebudget.repository;

import org.springframework.stereotype.Repository;
import ru.simplebudget.model.out.Receipt;


import java.time.LocalDateTime;
import java.util.List;

public interface CheckRepository{

    Receipt save(Receipt receipt);
    boolean delete(int id);
    Receipt get(int id);
    List<Receipt> getByPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime);

}
