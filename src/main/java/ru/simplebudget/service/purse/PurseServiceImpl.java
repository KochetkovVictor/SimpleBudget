package ru.simplebudget.service.purse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.repository.purse.PurseRepository;

import java.util.List;


@Service
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
    public Double getTotalAmount(Long userId) {

        return repository.getTotalAmount(getAll(userId), userId);
    }

    @Override
    public Purse getById(Long id, Long userId) {
        return repository.get(id, userId);
    }

    @Override
    public void transferAmount(Long fromPurseId, Long toPurseId, Double transferAmount, Long userId) {
        repository.transferAmount(fromPurseId, toPurseId, transferAmount, userId);
    }

    @Override
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
    }

    @Override
    public void delete(Long id, Long userId) {
        repository.deletePurse(id, userId);
    }


}
