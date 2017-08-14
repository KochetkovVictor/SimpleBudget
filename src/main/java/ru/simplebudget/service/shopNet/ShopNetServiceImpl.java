package ru.simplebudget.service.shopNet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simplebudget.model.common.ShopNet;
import ru.simplebudget.repository.shopNet.ShopNetRepository;

import java.util.List;

@Service
public class ShopNetServiceImpl implements ShopNetService{

    private final
    ShopNetRepository repository;

    @Autowired
    public ShopNetServiceImpl(ShopNetRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ShopNet> getAll() {
        return repository.getAll();
    }

    @Override
    public List<ShopNet> getByTemplate(String template) {
        return repository.getByTemplate(template);
    }
}
