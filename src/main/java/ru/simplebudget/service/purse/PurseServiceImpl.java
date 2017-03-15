package ru.simplebudget.service.purse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simplebudget.model.common.Purse;
import ru.simplebudget.repository.PurseRepository;

import java.util.List;

@Service
public class PurseServiceImpl {

    @Autowired
    private
    PurseRepository repository;

    public List<Purse> getAll()
    {
        return repository.getAll();
    }
}
