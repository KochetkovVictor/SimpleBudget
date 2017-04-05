package ru.simplebudget.service.purse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.repository.purse.PurseRepository;

import java.util.List;


@Service
public class PurseServiceImpl implements PurseService{

    @Autowired
    //@Qualifier(value="purseRepositoryImpl")
    private
    PurseRepository repository;

    public List<Purse> getAll()
    {
        return repository.getAll();
    }

    public Double getTotalAmount() {
        return repository.getTotalAmount(getAll());
    }

    @Override
    public Purse getById(Long id) {
        return repository.get(id);
    }

    @Override
    public void transferAmount(Long fromPurseId, Long toPurseId, Double transferAmount) {
        repository.transferAmount(fromPurseId,toPurseId, transferAmount);
    }

}
