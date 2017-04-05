package ru.simplebudget.service.purse;


import ru.simplebudget.model.common.Purse;

import java.util.List;

public interface PurseService {

    List<Purse> getAll();
    Double getTotalAmount();
    Purse getById(Long id);
    void transferAmount(Long fromPurseId, Long toPurseId, Double transferAmount);
}
