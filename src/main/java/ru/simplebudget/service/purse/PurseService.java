package ru.simplebudget.service.purse;


import ru.simplebudget.model.common.Purse;

import java.util.List;

public interface PurseService {

    List<Purse> getAll();
    Long getTotalAmount();
}