package ru.simplebudget.repository.purse;



import ru.simplebudget.model.common.Purse;

import java.util.List;


public interface PurseRepository {

    Purse save(Purse purse, Long userId);
    Purse getById(Long id, Long userId);
    boolean deletePurse(Long id, Long userId);
    List<Purse> getAll(Long userId);
}
