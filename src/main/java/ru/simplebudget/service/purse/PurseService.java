package ru.simplebudget.service.purse;


import ru.simplebudget.exceptions.NotEnoughMoneyException;
import ru.simplebudget.model.common.Purse;

import java.util.List;

public interface PurseService {

    List<Purse> getAll(Long userId);

    Purse getById(Long id, Long userId);
    void transferAmount(Long fromPurseId, Long toPurseId, Double transferAmount,Long userId) throws NotEnoughMoneyException;
    Purse saveOrUpdate(Purse purse, Long userId);
    void delete(Long id, Long userId);
}
