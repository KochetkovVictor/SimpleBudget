package ru.simplebudget.service.shopNet;

import ru.simplebudget.model.common.ShopNet;

import java.util.List;

public interface ShopNetService {
    List<ShopNet> getAll();

    List<ShopNet> getByTemplate(String template);
}
