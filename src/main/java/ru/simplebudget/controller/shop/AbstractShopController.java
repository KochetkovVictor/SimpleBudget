package ru.simplebudget.controller.shop;


import org.springframework.beans.factory.annotation.Autowired;
import ru.simplebudget.model.common.Shop;
import ru.simplebudget.service.shop.ShopService;

import java.util.List;

public abstract class AbstractShopController {

    private final
    ShopService service;

    @Autowired
    public AbstractShopController(ShopService service) {
        this.service = service;
    }

    List<Shop> getAll() {
        return service.getAll();
    }

    List<Shop> getByTemplate(String template, Long shopNetId) {
        return service.getByTemplate(template, shopNetId);
    }
}
