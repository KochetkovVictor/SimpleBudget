package ru.simplebudget.service.purse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.repository.purse.PurseRepository;

import java.util.List;


@Service("purseService")
public class PurseServiceImpl implements PurseService {

    private final
    PurseRepository repository;

    @Autowired
    public PurseServiceImpl(PurseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Purse> getAll(Long userId) {
        return repository.getAll(userId);
    }

    @Override
    public Purse getById(Long id, Long userId) {
        return repository.getById(id, userId);
    }

    @Override
    public void transferAmount(Long fromPurseId, Long toPurseId, Double transferAmount, Long userId) {
        Purse fromPurse = getById(fromPurseId, userId);
        Purse toPurse = getById(toPurseId, userId);
        fromPurse.setAmount(fromPurse.getAmount() - transferAmount);
        toPurse.setAmount(toPurse.getAmount() + transferAmount);
        saveOrUpdate(fromPurse, userId);
        saveOrUpdate(toPurse, userId);
    }

    @Override
    public Purse saveOrUpdate(Purse purse, Long userId) {
        return repository.save(purse, userId);
    }
    /*@Override
    public Double getTotalAmount(Long userId) {
        List<Purse> purses=getAll(userId);
        Double totalAmount=0.0;
        for (Purse p:purses
             ) {
            if (p.isActive()) totalAmount+=p.getAmount();
        }
        return totalAmount;
    }*/



    /*@Override
    public Purse addPurse(Purse purse, Long userId) {
        return repository.save(purse, userId);
    }

    @Override
    public void updatePurse(Purse purse, Long userId) {
        String description = purse.getDescription();
        Long id = purse.getId();
        boolean active = purse.isActive();
        Double amount = purse.getAmount();
        repository.changeName(id, userId, description, amount, active);
    }*/

    @Override
    public void delete(Long id, Long userId) {
        repository.deletePurse(id, userId);
    }
}
