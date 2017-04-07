package ru.simplebudget.repository.purse;



import ru.simplebudget.model.common.Purse;

import java.util.List;


public interface PurseRepository {

    Purse save(Purse purse);
    Purse get(Long id);
    Double getPurseAmount(Long id);
    void addPurseAmount(Long id, Double amount);
    Double getTotalAmount(List<Purse> purseList);
    boolean deletePurse(Long id);
    boolean changeName(Long id, String newDescription);
    List<Purse> getAll();
    void transferAmount(Long fromPurseId, Long toPurseId, Double transferAmount);
}
