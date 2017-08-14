package ru.simplebudget.repository.shop;


import ru.simplebudget.model.common.Shop;

import java.util.List;

public interface ShopRepository {
    List<Shop> getAll();

    Shop getById(Long id);

    List<Shop> getByTemplate(String template, Long shopNetId);

    List<Shop> getByShopNet(Long shopNetId);

    Shop saveOrUpdate(Shop shop);

    boolean delete(Long id);
}
