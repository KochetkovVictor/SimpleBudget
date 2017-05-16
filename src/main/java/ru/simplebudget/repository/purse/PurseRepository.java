package ru.simplebudget.repository.purse;



import ru.simplebudget.model.common.Purse;

import java.util.List;


public interface PurseRepository {

    Purse save(Purse purse, Long userId);
    Purse get(Long id, Long userId);
    Double getPurseAmount(Long id, Long userId);
    void addPurseAmount(Long id,Long userId, Double amount);
    void setPurseAmount(Long id,Long userId, Double amount);
    Double getTotalAmount(List<Purse> purseList, Long userId);
    boolean deletePurse(Long id, Long userId);
    boolean changeName(Long id, Long userId, String newDescription, Double amount, boolean active);
    List<Purse> getAll(Long userId);
    void transferAmount(Long fromPurseId, Long toPurseId, Double transferAmount, Long userId);
}
