package ru.simplebudget.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.simplebudget.model.out.Check;


import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CheckRepository{

    Check save(Check check);
    boolean delete(int id);
    Check get(int id);
    List<Check> getByPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime);

}
