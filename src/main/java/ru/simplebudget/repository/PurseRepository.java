package ru.simplebudget.repository;



import ru.simplebudget.model.common.Purse;

import java.util.List;


public interface PurseRepository {

    Purse save(Purse purse);
    Purse get(Long id);
    Long getPurseAmount(Long id);
    void addPurseAmount(Long id, Long amount);
    void setPurseAmount(Long id, Long amount);
    Long getTotalAmount(List<Purse> purseList);
    boolean deletePurse(Long id);
    boolean changeName(Long id, String newDescription);
    List<Purse> getAll();
}
