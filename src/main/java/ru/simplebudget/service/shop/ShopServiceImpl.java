package ru.simplebudget.service.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simplebudget.model.common.Shop;
import ru.simplebudget.repository.shop.ShopRepository;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository repository;

    @Autowired
    public ShopServiceImpl(ShopRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Shop> getAll() {
        return repository.getAll();
    }

    @Override
    public Shop getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public List<Shop> getByTemplate(String template, Long shopNetId) {
        return repository.getByTemplate(template, shopNetId);
    }
}
