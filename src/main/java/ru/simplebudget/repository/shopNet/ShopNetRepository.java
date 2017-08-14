package ru.simplebudget.repository.shopNet;

import ru.simplebudget.model.common.ShopNet;

import java.util.List;

public interface ShopNetRepository {

    List<ShopNet> getAll();
    ShopNet getById(Long id);

    ShopNet saveOrUpdate(ShopNet shopNet);
    boolean delete(Long id);

    List<ShopNet> getByTemplate(String template);
}
