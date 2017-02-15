package ru.simplebudget.repository;



import org.springframework.stereotype.Repository;
import ru.simplebudget.model.Purse;

@Repository
public interface PurseRepository {

    Purse save(Purse purse);
    Long getPurseAmount(Long id);
    void setPurseAmount(Long id, Long amount);
}
