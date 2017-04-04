package ru.simplebudget.service.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simplebudget.model.common.Shop;
import ru.simplebudget.repository.shop.ShopRepository;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;
    @Override
    public List<Shop> getAll() {
        return shopRepository.getAll();
    }

    @Override
    public Shop getById(Long id) {
        return shopRepository.getById(id);
    }
}
